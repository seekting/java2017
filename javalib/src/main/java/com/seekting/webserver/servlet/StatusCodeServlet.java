package com.seekting.webserver.servlet;

import com.seekting.webserver.BodySimpleResponse;
import com.seekting.webserver.DetailSimpleResponse;
import com.seekting.webserver.SimpleRequest;
import com.seekting.webserver.SimpleServlet;

import java.io.OutputStream;

import static java.lang.System.out;

/**
 * Created by seekting on 17-8-4.
 */

public class StatusCodeServlet implements SimpleServlet {
    @Override
    public void doGet(SimpleRequest simpleRequest, OutputStream outputStream) {
        new BodySimpleResponse("401get ok").write(outputStream);
    }

    @Override
    public void doPost(SimpleRequest simpleRequest, OutputStream outputStream) {
        out.println(simpleRequest.mBody);
        final String[] status = simpleRequest.mBody.split("\\|");
        System.out.println("body="+simpleRequest.mBody);
        System.out.println("status:"+status[0]);
        System.out.println("msg:"+status[1]);
        DetailSimpleResponse detailSimpleResponse = new DetailSimpleResponse() {
            @Override
            public void onWriteStatus(OutputStream out) {
                writeStatus(out, status[0], status[1]);

            }

            @Override
            public void onWriteHeader(OutputStream out) {
                    writeContentLength(out, status[1].getBytes().length);
                    writeContentType(out, "text");
            }

            @Override
            public void onWriteBody(OutputStream out) {
                    writeBody(out, status[1].getBytes());

            }
        };
        detailSimpleResponse.write(outputStream);


    }
}
