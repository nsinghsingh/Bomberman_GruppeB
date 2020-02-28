package server;

import client.ClientMain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

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
                    currPlayercount++;
                    System.out.println(clientSocket.size());
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
            }
            //Loops trough all the Clients !! NOTE !! Currently not working ! only prints out the last object that created
            for (Socket clientsSocket : clientSocket
            ) {
                try {
                    DataInputStream dIn = new DataInputStream(clientsSocket.getInputStream());
                    System.out.println(dIn.readUTF() + " " + clientsSocket);
                } catch (Exception ignore) {
                }

            }

        }
    }

}
