package com.seekting.webserver;

import com.seekting.webserver.servlet.ImgServlet;
import com.seekting.webserver.servlet.StatusCodeServlet;
import com.seekting.webserver.servlet.TestServlet;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by seekting on 17-8-3.
 */

public class SimpleServer {

    public static final int PORT = 8080;
    public static Map<String, Class<? extends SimpleServlet>> SERVER_MAP = new HashMap<>();

    static {
        SERVER_MAP.put("/test", TestServlet.class);
        SERVER_MAP.put("/img", ImgServlet.class);
        SERVER_MAP.put("/statuscode", StatusCodeServlet.class);
    }


    public static void main(String args[]) {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.start();
    }

    private void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                final Socket socket = serverSocket.accept();
                System.out.println("accept:" + socket.getInetAddress());
                SimpleResponseTask simpleResponseTask = new SimpleResponseTask(socket);
                simpleResponseTask.start();


            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
