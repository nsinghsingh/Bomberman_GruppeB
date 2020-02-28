package server;

import client.clientMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class serverMain {

    static final int PORT = 1111;
    static public int currPlayercount;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        ArrayList<Socket> clientSocket = new ArrayList<Socket>();
        currPlayercount = 0;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            if (currPlayercount < 4) {
                try {
                    Socket newClient = serverSocket.accept();
                    clientSocket.add(newClient);
                    currPlayercount += 1;
                    System.out.println(clientSocket.size());
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
            }
            //TODO: Share data here!

        }
    }
}
