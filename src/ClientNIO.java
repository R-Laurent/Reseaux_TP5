import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientNIO {
    public static void main(String[] args) throws IOException {
        String str = "yo le s";
        InetSocketAddress adress = new InetSocketAddress("localhost",1234);
        SocketChannel client = SocketChannel.open(adress);
        byte[] msg = str.getBytes();
        int i =0;
        while (i<5){
            ByteBuffer buffer = ByteBuffer.wrap(msg);
            client.write(buffer);
            i++;
        }
    }
}
