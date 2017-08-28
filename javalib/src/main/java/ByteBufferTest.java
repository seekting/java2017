import java.nio.ByteBuffer;

/**
 * Created by seekting on 17-7-5.
 */

public class ByteBufferTest {

    public static void main(String args[]) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        System.out.println("position" + byteBuffer.position());
        System.out.println("limit" + byteBuffer.limit());
        byteBuffer.flip();
        System.out.println("flip limit" + byteBuffer.limit());
        byteBuffer.position(1);
        byte a = 24;
        byteBuffer.put(a);
        System.out.println(byteBuffer.get(1));
        System.out.println("position" + byteBuffer.position());
        byteBuffer.position(0);
        System.out.println("position" + byteBuffer.position());
        byte b = 1;
        byteBuffer.put(b);
        System.out.println(byteBuffer.get(0));
        System.out.println("position" + byteBuffer.position());
        System.out.println("limit" + byteBuffer.limit());

    }
}
