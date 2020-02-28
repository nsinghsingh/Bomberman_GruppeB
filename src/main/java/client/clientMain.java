package client;

import java.io.*;
import java.net.Socket;

public class clientMain extends Thread {
    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 1111;

    public clientMain() {
        System.out.println("Client created");
        try {
            socket = new Socket(ip, port);
            start();
        } catch (Exception ignored) {
        }
    }

    public void run() {
        try {
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF("This is the first type of message.");
            dOut.flush();
        } catch (Exception ignore) {
        }


    }

}
