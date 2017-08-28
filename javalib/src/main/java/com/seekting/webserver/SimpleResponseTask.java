package com.seekting.webserver;

import com.seekting.webserver.servlet.ImgServlet;
import com.seekting.webserver.servlet.VideoServlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.seekting.webserver.SimpleServer.SERVER_MAP;

/**
 * Created by seekting on 17-8-4.
 */

public class SimpleResponseTask extends Thread {
    private Socket mClientSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private String[] mList;

    public SimpleResponseTask(Socket socket) {
        mClientSocket = socket;
        try {
            //读取数据超过10ms没收到就超时
            mClientSocket.setSoTimeout(10);
//            mClientSocket.setKeepAlive(true);
            mOutputStream = mClientSocket.getOutputStream();
            mInputStream = mClientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        byte[] read = read(mInputStream);
        SimpleRequest request = new SimpleRequest(read);
        System.out.println("path=" + request.mPath + "=end");
        Class<? extends SimpleServlet> targetServlet = SERVER_MAP.get(request.mPath);
        if (targetServlet == null) {
//            String[] key = request.mPath.split("/");
//            targetServlet = SERVER_MAP.get("/" + key[1]);
            if (request.mPath.endsWith(".jpg")) {
                targetServlet = ImgServlet.class;
            } else if (request.mPath.endsWith(".mp4")) {
                targetServlet = VideoServlet.class;
            }
        }

        if (targetServlet != null) {
            try {
                SimpleServlet simpleServlet = targetServlet.newInstance();
                if (request.mMethod != null) {
                    if (request.mMethod.equalsIgnoreCase("get")) {
                        simpleServlet.doGet(request, mOutputStream);
                    } else if ("post".equalsIgnoreCase(request.mMethod)) {
                        simpleServlet.doPost(request, mOutputStream);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }


        try {
            mOutputStream.flush();
            mOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mClientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }

    private byte[] read(InputStream inputStream) {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while (true) {
            try {
                int length = inputStream.read(bytes);
                out.write(bytes, 0, length);
                if (length <= 0) {
                    break;
                }

            } catch (IOException e) {
                break;
            }
        }


        return out.toByteArray();
    }


}
