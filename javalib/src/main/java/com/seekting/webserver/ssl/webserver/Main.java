package com.seekting.webserver.ssl.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Administrator on 2017/8/26.
 */

public class Main {


    public static final String SERVER_KEY_STORE = "javalib/ca/server/server.p12";
    private static String SERVER_KEY_STORE_PASSWORD = "server";


//    private static String CLIENT_KEY_STORE = "./ca/client/client.p12";
//    private static String CLIENT_KEY_STORE_PASSWORD = "client";


    public static void main(String args[]) throws Throwable {
        File f=new File(SERVER_KEY_STORE);
        System.out.println(""+f.getAbsolutePath());
        SSLContext context = SSLContext.getInstance("TLS");

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(SERVER_KEY_STORE), SERVER_KEY_STORE_PASSWORD.toCharArray());
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());


        //如果server端import过客户端的证书，就可以直接用server端的
        KeyStore tks = KeyStore.getInstance("PKCS12");
//        tks.load(new FileInputStream(CLIENT_KEY_STORE), CLIENT_KEY_STORE_PASSWORD.toCharArray());
        tks.load(new FileInputStream(SERVER_KEY_STORE), SERVER_KEY_STORE_PASSWORD.toCharArray());
        TrustManagerFactory tkf = TrustManagerFactory.getInstance("SunX509");
        tkf.init(tks);

        context.init(kf.getKeyManagers(), tkf.getTrustManagers(), null);


        ServerSocketFactory factory = context.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) factory
                .createServerSocket(8443);
        // 要求客户端身份验证
        serverSocket.setNeedClientAuth(true);

        while (true) {
            SSLSocket socket = (SSLSocket) serverSocket.accept();
            socket.setSoTimeout(50);
            Accepter accepter = new Accepter(socket);
            accepter.service();
        }
    }

    static class Accepter implements Runnable {
        private SSLSocket socket;

        public Accepter(SSLSocket socket) {
            this.socket = socket;
        }

        public void service() {
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());

                while (true) {
                    String data = reader.readLine();


                    if (data == null || "".equals(data)) {
                        break;
                    }
                    writer.println(data);
                    writer.flush();
                    System.out.println(data);
                }


            } catch (Exception e) {
            } finally {
                long time = System.currentTimeMillis();
                writer.println("hello !!!" + time);
                writer.close();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("over" + time);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
