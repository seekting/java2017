import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 13分3个线程：perlength=4
 * 0123
 * 4567
 * 8,9,10,11 * 12
 * Created by seekting on 17-5-11.
 */

public class ImageDownloader {


    public ImageDownloader(String url) {
        mUrl = url;
    }

    private String mUrl;

    public List<Thread> mainThreaddownload(final int number,final String path) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        create(path);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            final int j = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    download(number, j, path);
                }
            });
            t.start();
            list.add(t);

        }
        return list;


    }

    public void create(String path) {
        try {
            URL url = new URL(mUrl);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                int length = httpURLConnection.getContentLength();
                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
                randomAccessFile.setLength(length);
                randomAccessFile.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void download(int number, int index, String path) {
        try {
            URL url = new URL(mUrl);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                int length = httpURLConnection.getContentLength();
                int perLength = length / number;
                System.out.println("perLength=" + perLength);
                InputStream inputStream = httpURLConnection.getInputStream();


                int begin = perLength * index;
                int end = (index + 1) * perLength - 1;
                if (index == number - 1) {
                    end = length - 1;
                }


                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
                inputStream.skip(begin);
                randomAccessFile.skipBytes(begin);
                int maxLength = end - begin + 1;

                byte[] read = new byte[1024];
                int sum = 0;
                while (true) {
                    int readLength = inputStream.read(read);
//                    System.out.println("readLength=" + readLength);
                    sum = sum + readLength;
                    //l=1024,sum=2000,maxlength=1500    newLength=(maxLength-(sum-l))
                    if (sum >= maxLength) {
                        int newLength = (maxLength - (sum - readLength));
                        randomAccessFile.write(read, 0, newLength);
                        break;
                    } else {
                        randomAccessFile.write(read, 0, readLength);
                    }

                }


                randomAccessFile.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "a.jpg";
        String url = "https://imgsa.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=a9977ecf66380cd7f213aabfc02dc651/9345d688d43f8794bd2bc951d11b0ef41ad53afc.jpg";
        ImageDownloader imageDownloader = new ImageDownloader(url);
        List<Thread> list = imageDownloader.mainThreaddownload(4, path);

        for (int i = 0; i < list.size(); i++) {
            Thread t = list.get(i);
            try {
                System.out.println("joinbegin" + i);
                t.join();
                System.out.println("joinend" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("asset!!!");
        asset();


    }

    public static void asset() {
        File file = new File("a.jpg");
        File file1 = new File("a1.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            byte[] bytes = new byte[1];
            byte[] bytes1 = new byte[1];
            int i = 0;
            while (true) {
                int length = fileInputStream.read(bytes);
                int length1 = fileInputStream1.read(bytes1);
                if (bytes1[0] != bytes[0]) {

                    System.out.println("i=" + i + "left=" + bytes[0] + "right=" + bytes1[0]);
                }
                if (length <= 0) {
                    break;
                }
                i++;
            }
//            System.out.println("i=" + i);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
