import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * Created by seekting on 17-7-4.
 */

public class SocketHttp {
    public static void main(String[] args) throws Throwable {

//        http();
//        socket_request_get();
//        udpServer();
        udpClient();

    }

    private static void udpServer() throws Throwable {
        DatagramSocket server = new DatagramSocket(5050);
        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket
                = new DatagramPacket(recvBuf, recvBuf.length);
        server.receive(recvPacket);
        String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
        System.out.println("Hello World!" + recvStr);
        int port = recvPacket.getPort();
        InetAddress addr = recvPacket.getAddress();
        String sendStr = "Hello ! I'm Server";
        byte[] sendBuf;
        sendBuf = sendStr.getBytes();
        DatagramPacket sendPacket
                = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
        server.send(sendPacket);
        server.close();
    }

    private static void udpClient() throws Throwable{
        DatagramSocket client = new DatagramSocket();

        String sendStr = "Hello! I'm SSLClient";
        byte[] sendBuf;
        sendBuf = sendStr.getBytes();
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        int port = 5050;
        DatagramPacket sendPacket
                = new DatagramPacket(sendBuf ,sendBuf.length , addr , port);
        client.send(sendPacket);
        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket
                = new DatagramPacket(recvBuf , recvBuf.length);
        client.receive(recvPacket);
        String recvStr = new String(recvPacket.getData() , 0 ,recvPacket.getLength());
        System.out.println("收到:" + recvStr);
        client.close();
    }
    private static void http() {
        try {
            URL url = new URL("http://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            String str = body(in);
            System.out.println(str);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void socket_request_get() throws Throwable {
        int port = 80;
        String host = "www.baidu.com";
//        String path = "/s?wd=lofter";
        String path = "/";
        String charset = "utf-8";
        String method = "GET";
        Socket s = new Socket(host, port);
//        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), charset));
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        StringBuffer sb = new StringBuffer(method + " " + path + " HTTP/1.1\r\n");
        sb.append("User-Agent: Java/1.6.0_20\r\n");
        sb.append("Host:" + host + ":" + port + "\r\n");
        sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
        sb.append("Connection: Close\r\n");
        sb.append("charset: " + charset + "\r\n");
        sb.append("\r\n");
        out.write(sb.toString().getBytes(charset));
        String str = body(in);
        System.out.println("str" + str);
        out.close();

    }

    private static String body(InputStream in) {
        byte[] bytes = new byte[1024];
        String result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while (true) {
                int length = in.read(bytes);
                if (length > 0) {
                    byteArrayOutputStream.write(bytes, 0, length);
                } else {
                    break;
                }

                byte[] b = byteArrayOutputStream.toByteArray();

                result = new String(b);
            }
        } catch (IOException e) {


        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;

    }
}
