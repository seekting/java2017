package com.seekting.webserver.ssl.webserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SSLClient {
    private static String CLIENT_KEY_STORE = "./ca/client/client.p12";
    private static String CLIENT_KEY_STORE_PASSWORD = "client";

//    public static final String SERVER_KEY_STORE = "./ca/server/server.p12";
//    private static String SERVER_KEY_STORE_PASSWORD = "server";

    public static void main(String[] args) throws Exception {
//        System.setProperty("javax.net.ssl.trustStorePassword",CLIENT_KEY_STORE_PASSWORD);

//        System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);
        SSLClient client = new SSLClient();
        Socket s = client.clientWithCert();

        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        writer.println("hello 我是java");
        writer.flush();
        while (true) {
            String line = reader.readLine();
            if (line == null || "".equals(line)) {
                break;
            }
            System.out.println(reader.readLine());
        }
        writer.close();
        s.close();
    }

    private Socket clientWithoutCert() throws Exception {
        SocketFactory sf = SSLSocketFactory.getDefault();
        Socket s = sf.createSocket("localhost", 8443);
        return s;
    }

    private Socket clientWithCert() throws Exception {


        SSLContext context = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("PKCS12");

        ks.load(new FileInputStream(CLIENT_KEY_STORE), CLIENT_KEY_STORE_PASSWORD.toCharArray());
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());

        KeyStore tks = KeyStore.getInstance("PKCS12");
        tks.load(new FileInputStream(CLIENT_KEY_STORE), CLIENT_KEY_STORE_PASSWORD.toCharArray());
        TrustManagerFactory tkf = TrustManagerFactory.getInstance("SunX509");
        tkf.init(tks);

        TrustManager[] trustManagers=tkf.getTrustManagers();
        context.init(kf.getKeyManagers(), trustManagers, null);


        SocketFactory factory = context.getSocketFactory();
        Socket s = factory.createSocket("localhost", 8443);
        return s;
    }
}
