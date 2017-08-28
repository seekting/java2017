package com.seekting.webserver;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by seekting on 17-8-4.
 */

public abstract class DetailSimpleResponse implements SimpleResponse {
    public static final String SPACE = " ";
    public static final String RN = "\r\n";
    public static final String HEAD_SPLIT = ":";
    public static final String HttpVersion = "HTTP/1.1";

    public static void writeContentLength(OutputStream out, long length) {
        writeHead(out, "Content-Length", length + "");

    }

    public static void writeContentType(OutputStream out, String type) {
        writeHead(out, "Content-Type", type);

    }

    public static void writeOkStatus(OutputStream out) {
        writeStatus(out, 200, "OK");
    }

    public static void writeStatus(OutputStream out, String statusCode, String msg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HttpVersion);
        stringBuilder.append(SPACE);
        stringBuilder.append(statusCode);
        stringBuilder.append(SPACE);
        stringBuilder.append(msg);
        stringBuilder.append(RN);
        try {
            out.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStatus(OutputStream out, int statusCode, String msg) {
        writeStatus(out, String.valueOf(statusCode), msg);

    }

    public static void writeHead(OutputStream out, String key, String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key);
        stringBuilder.append(HEAD_SPLIT);
        stringBuilder.append(value);
        stringBuilder.append(RN);
        try {
            out.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBody(OutputStream out, byte[] body) {
        try {
            out.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void onWriteStatus(OutputStream out);

    public abstract void onWriteHeader(OutputStream out);

    public abstract void onWriteBody(OutputStream out);

    public void write(OutputStream out) {
        onWriteStatus(out);
        onWriteHeader(out);
        try {
            out.write(RN.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        onWriteBody(out);
    }

}
