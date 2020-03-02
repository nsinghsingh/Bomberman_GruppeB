package client;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread {
    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 1111;

    public ClientMain() {
        System.out.println("Client created");
        try {
            socket = new Socket(ip, port);
            run();
        } catch (Exception ignored) {
        }
    }

    public void run() {
        DataOutputStream dOut = null;
        DataInputStream dIn = null;
        try {
             dOut = new DataOutputStream(socket.getOutputStream());
             dIn = new DataInputStream(socket.getInputStream());
        }catch (Exception ignore){}

        while (true) {
            //Sendet Die Nachricht

            try {

                dOut.writeUTF("I'm the Client");
                dOut.flush();

            } catch (Exception ignore) {
                System.out.println("lol");
            }

            //Empfängt Nachricht
            try {

                System.out.println(dIn.readUTF() + " ");

            } catch (Exception ignore) {

            }
        }
    }
}
