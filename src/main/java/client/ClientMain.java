package client;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread {
    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 986;
    DataOutputStream dOut = null;
    DataInputStream dIn = null;

    public ClientMain() {
        System.out.println("Client created");
        try {
            socket = new Socket(ip, port);
        } catch (Exception ignored) {
            System.out.println("failed created");
        }
    }

    public void run() {
        while (true) {
            //Looping Thread
            receiveMessage();
        }
    }


    //sends a message to the server
    public void sendMessage(String message) {
        try {

            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF(message);
            dOut.flush();
            System.out.println("Sendet from: " + socket);

        } catch (Exception ignore) {
            System.out.println("error Sending");
        }

    }

    //receives a message from the server for all clients the same Message
    public void receiveMessage() {
        try {
            dIn = new DataInputStream(socket.getInputStream());
            String k = dIn.readUTF();
            System.out.println(k);
        } catch (Exception ignore) {
        }
    }
}
