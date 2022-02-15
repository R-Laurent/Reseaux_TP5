import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
//je ne comprends absolument pas comment faire la deuxième partie de la question 2
public class MultiClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress adresse = new InetSocketAddress("localhost", 1234);
        Selector selector = Selector.open();
        ServerSocketChannel serveurSocketChannel = ServerSocketChannel.open();
        serveurSocketChannel.configureBlocking(false);
        serveurSocketChannel.bind(adresse);
        ServerSocket serverSocket = serveurSocketChannel.socket();
        serveurSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        //PrintWriter out = new PrintWriter(c.getOutputStream(), true);
        int x = 0;
        String y;
        while (true) {
            selector.selectedKeys();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            // Pour chaque key sélectionnée ...
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
                System.out.println("accept");
                SocketChannel client = serverSocket.accept().getChannel();
            // Enregistrer le nouvel client
                client.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                System.out.println("ok");
                int compteur = 0;
                SocketChannel client = (SocketChannel) key.channel();
                key.attach(compteur);
                ByteBuffer bf = ByteBuffer.allocate(1000);
                client.read(bf);
                String result = new String(bf.array()).trim();
                System.out.println("result : " + result);
            }
            if (key.isWritable()) {
                SocketChannel client = (SocketChannel) key.channel();
            // Traitement de données du client ...
            }

        System.out.println("le nombre de paquets reçu est : " + x);
}
}
}
}