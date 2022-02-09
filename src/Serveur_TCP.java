import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_TCP {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(1234);
        Socket c = s.accept();
        InputStream is = c.getInputStream();
        int x = 0;
        while(true) {
            BufferedReader text = new BufferedReader(new InputStreamReader(is));

            String y = text.readLine();
            System.out.println(y);
            if(y == null){
                c.close();
                s.close();
                break;
            }
            x++;
            System.out.println(y);
        }

    }
}