package com.seekting.webserver.servlet;

import com.seekting.webserver.DetailSimpleResponse;
import com.seekting.webserver.SimpleRequest;
import com.seekting.webserver.SimpleServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Created by seekting on 17-8-4.
 */

public class ImgServlet implements SimpleServlet {
    @Override
    public void doGet(SimpleRequest simpleRequest, final OutputStream outputStream) {
        String path = simpleRequest.mPath;
        final File f = new File("." + path);
        final boolean ok = f.exists() && !f.isDirectory();
        DetailSimpleResponse newSimpleResponse = new DetailSimpleResponse() {
            @Override
            public void onWriteBody(OutputStream out) {
                if (ok) {
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(f);
                        byte[] bytes = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bytes);
                            if (read <= 0) {
                                break;
                            }
                            outputStream.write(bytes, 0, read);
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onWriteStatus(OutputStream out) {
                if (ok) {
                    writeOkStatus(out);
                } else {
                    writeStatus(out, 404, "not found");
                }
            }

            @Override
            public void onWriteHeader(OutputStream out) {
                if (ok) {
                    writeContentLength(out, f.length());
                    writeContentType(out, "image/jpeg");
                }
            }
        };
        newSimpleResponse.write(outputStream);

    }

    @Override
    public void doPost(SimpleRequest simpleRequest, OutputStream outputStream) {

    }
}
