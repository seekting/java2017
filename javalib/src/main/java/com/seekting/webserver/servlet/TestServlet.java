package com.seekting.webserver.servlet;

import com.seekting.webserver.SimpleRequest;
import com.seekting.webserver.BodySimpleResponse;
import com.seekting.webserver.SimpleServlet;

import java.io.OutputStream;

/**
 * Created by seekting on 17-8-4.
 */

public class TestServlet implements SimpleServlet {
    @Override
    public void doGet(SimpleRequest simpleRequest, OutputStream outputStream) {
        BodySimpleResponse simpleResponse = new BodySimpleResponse();
        simpleResponse.mBody = this.getClass().getSimpleName();
        simpleResponse.write(outputStream);
    }


    @Override
    public void doPost(SimpleRequest simpleRequest, OutputStream outputStream) {

    }

    public static void returnMessage(OutputStream out, String str) {
        try {

            String errorMessage = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n"
                    + "Content-Length: " + str.getBytes().length + "\r\n" + "\r\n" + str;
            out.write(errorMessage.getBytes());
            System.out.println("write over!");


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
