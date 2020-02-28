package client;

import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread{
    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 1111;

    public ClientMain() {
        System.out.println("Client created");
        try{
            socket = new Socket(ip,port);
            start();
        }
        catch (Exception ignored){}
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        while (true) {
           //TODO: Share data here!

        }
    }
}
