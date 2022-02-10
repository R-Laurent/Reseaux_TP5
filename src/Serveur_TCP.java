//source : https://www.baeldung.com/a-guide-to-java-sockets

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_TCP {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(1234);
        Socket c = s.accept();
        PrintWriter out = new PrintWriter(c.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        int x = 0;
        while(in.readLine() != null) {
            String y = in.readLine();
            System.out.println(y);
            x++;
            if(y == null){
                c.close();
                s.close();
                break;
            }
        }
    }
}