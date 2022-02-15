//source : https://www.baeldung.com/a-guide-to-java-so  ckets

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Serveur_TCP {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel socket = ServerSocketChannel.open();
        ServerSocket serverSocket = socket.socket();
        Socket c = serverSocket.accept();
        PrintWriter out = new PrintWriter(c.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        int x = 0;
        String y;
        while ((y = in.readLine()) != null) {
            if (y == null) {
                c.close();
                serverSocket.close();
                break;
            }
            System.out.println(y);
            out.println(y);
            x++;
        }
        System.out.println("le nombre de paquets reçu est : " + x);
    }
}