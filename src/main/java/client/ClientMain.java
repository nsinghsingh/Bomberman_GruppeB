package client;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread {
    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 9876;
    DataOutputStream dOut = null;
    DataInputStream dIn = null;

    public ClientMain() {
        System.out.println("Client created");
        try {
            socket = new Socket(ip, port);
            run();
        } catch (Exception ignored) {
        }
    }

    public void run() {
        while (true) {
        //Looping Thread
        sendMessage(socket.toString());
        }
    }

    public void sendMessage(String message) {

        //sends a message to the server
        try {
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeUTF(message);
            dOut.flush();

        } catch (Exception ignore) {
        }

    }

    public String receiveMessage() {

        //receives a message from the server
        try {
            dIn = new DataInputStream(socket.getInputStream());
            return dIn.readUTF();
        } catch (Exception ignore) {return null;}
    }
}
