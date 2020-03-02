package server;

import client.ClientMain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ServerMain {

    static final int PORT = 986;
    static public int currPlayercount;
    static private DataInputStream dIn = null;
    static private DataOutputStream dOut = null;
    static private ArrayList<Socket> clientSocketList = new ArrayList<Socket>(4);

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
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
                    clientSocketList.add(newClient);
                    currPlayercount++;
                } catch (Exception ignore) {
                    System.out.println("failed To connet");
                }
            }

            //loops after 4 players are connected.
            receiveMessage();
        }
    }

    //sends a Message to all clients
    static public void sendMessage(String message) {

        try {
            for (Socket clientSocket : clientSocketList
            ) {
                dOut = new DataOutputStream(clientSocket.getOutputStream());
                dOut.writeUTF(message);
                dOut.flush();
            }
        } catch (Exception ignore) {
        }
    }

    //receives Client messages
    static public void receiveMessage() {
        for (Socket clientSocket : clientSocketList
        ) {
            //System.out.println(clientSocket);
            try {
                dIn = new DataInputStream(clientSocket.getInputStream());
                while (dIn.available() > 0) {
                    String k = dIn.readUTF();
                    System.out.println(k + clientSocket);
                    //send back to the client (Here would be a Method call to Validate stuff)
                    sendMessage(k + clientSocket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}