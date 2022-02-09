// sources : https://crunchify.com/java-nio-non-blocking-io-with-server-client-example-java-nio-bytebuffer-and-channels-selector-java-nio-vs-io/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServeurMultiCo {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serveurChannel = ServerSocketChannel.open();
        InetSocketAddress adresse = new InetSocketAddress("localhost", 1234);
        serveurChannel.bind(adresse);
        serveurChannel.configureBlocking(false);
        int op = serveurChannel.validOps();
        SelectionKey selectionKey = serveurChannel.register(selector,SelectionKey.OP_ACCEPT);
        int x = 0;
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    SocketChannel clientChannel = serveurChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                    ByteBuffer bf = ByteBuffer.allocate(1000);
                    if (key.isReadable()){
                        clientChannel.read(bf);
                        //bf.flip();
                        System.out.println(bf);
                        String result = new String(bf.array()).trim();
                        System.out.println(result);
                    }
                    String result = new String(bf.array()).trim();
                    System.out.println(x);
                    x++;
                    System.out.println("x : " + x);
                iterator.remove();
            }
        }
    }
}
}
