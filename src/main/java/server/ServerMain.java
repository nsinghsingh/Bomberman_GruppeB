package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServerMain {

    static final int PORT = 4456;
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
                    sendIdMessage("clientID;" + currPlayercount,newClient);

                    currPlayercount++;
                    receiveMessage();
                    if (currPlayercount == 4) {
                        countDown();
                    }
                } catch (Exception ignore) {
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

    static public void sendIdMessage(String message,Socket client) {
        try {
            System.out.println(message);
            dOut = new DataOutputStream(client.getOutputStream());
            dOut.writeUTF(message);
            dOut.flush();

        } catch (Exception ignore) {
        }
    }

    //receives Client messages
    static public void receiveMessage() {
        for (Socket clientSocket : clientSocketList
        ) {
            try {
                dIn = new DataInputStream(clientSocket.getInputStream());
                while (dIn.available() > 0) {
                    String k = dIn.readUTF();
                    //sends Back to all the client
                    sendMessage(k);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static int count;

    public static void countDown() {
        count = 5;
        Timer timer = new Timer();
        sendMessage("chat;Server: Starting GAME !");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (count > 0)
                    sendMessage("chat;Server: " + count);
                count--;

                if (count == 0)
                    sendMessage("gamestart;");
            }
        };
        timer.schedule(task, 0, 1000);
    }

}