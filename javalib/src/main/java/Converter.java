import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by seekting on 17-5-11.
 */

public class Converter {
    /**
     * 基堆选泡归希快插
     *
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("/home/seekting/work/git/testjava/src/main/java/com/kunhong/design");
        open(file);

    }

    public static void open(File dir) {
        File[] f = dir.listFiles();
        for (File file : f) {
            if (file.isDirectory()) {
                open(file);
            } else {
                convert(file);
            }

        }


    }

    private static void convert(File file) {
        try {
            FileInputStream f = new FileInputStream(file);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                int length = f.read(bytes);
                if (length > 0) {
                    byteArrayOutputStream.write(bytes, 0, length);
                } else {
                    break;
                }

            }
            f.close();
            String str = new String(byteArrayOutputStream.toByteArray(), "GBK");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes("UTF-8"));
            fileOutputStream.close();

            System.out.println("转码:" + file.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
