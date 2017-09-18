package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Administrator on 2017/8/30.
 */

public class TurnSocket {

    public static void main(String args[]) {

        System.out.println("hello");
        String ip = "35.160.246.46";
        int port = 54486;
        InputStream in = null;
        BufferedReader bufferedReader = null;
        try {
            Socket socket = new Socket(ip, port);
            in = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            while (true) {
                String line = bufferedReader.readLine();
                System.out.println("line=" + line);

            }


        } catch (IOException e) {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
