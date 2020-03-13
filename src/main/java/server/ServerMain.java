package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public final class ServerMain {

    private static ServerMain INSTANCE;
    private static final int PORT = 21;
    private static int currPlayercount;
    private static int count;
    private static DataInputStream dIn = null;
    private static DataOutputStream dOut = null;
    private static ArrayList<Socket> clientSocketList = new ArrayList<>(4);

    public static void main(String[] args) {
        //Creates the server which Clients will join and play on
        ServerSocket serverSocket = null;
        currPlayercount = 0;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Loops all the Time and checks for any client requests
        while (true) {
            if (currPlayercount < 4) {
                try {
                    Socket newClient = serverSocket.accept();
                    clientSocketList.add(newClient);
                    sendIdMessage("clientID;" + currPlayercount, newClient);
                    currPlayercount++;
                    receiveMessage();
                    if (currPlayercount == 4) {
                        countDown();
                    }
                } catch (Exception ignore) {
                }
            }
            //after 4 players are connected. Always listens for Requests
            receiveMessage();
        }
    }

    //Singleton
    public static ServerMain getInstance() {
        if (ServerMain.INSTANCE == null) {
            ServerMain.INSTANCE = new ServerMain();
        }
        return ServerMain.INSTANCE;
    }

    /*
    Will send to all 4 Clients the same message.
    Sends a message to the Clients which contains a String code
    That will contain different types of information's this will be in a String and is sectioned by a semicolon ';'
    The syntax looks like this 'action;message;playerID'
    For example when a Player moves 'method;w;2' this means that we are calling method which is a Method a message 'w' which is the direction and 1 which is the player that moved.
     */
    static private void sendMessage(String message) {
        try {
            for (Socket clientSocket : clientSocketList
            ) {
                dOut = new DataOutputStream(clientSocket.getOutputStream());
                dOut.writeUTF(message);
                dOut.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sends a Integer to the client so that he has a unique ID to further distinguished the players
    static private void sendIdMessage(String message, Socket client) {
        try {
            System.out.println(message);
            dOut = new DataOutputStream(client.getOutputStream());
            dOut.writeUTF(message);
            dOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Always listens and when a client Sends a Message it will be send Back to sendMessage which will send it to all clients
    static public void receiveMessage() {
        for (Socket clientSocket : clientSocketList
        ) {
            try {
                dIn = new DataInputStream(clientSocket.getInputStream());
                while (dIn.available() > 0) {
                    String k = dIn.readUTF();
                    sendMessage(k);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //A counter that starts to count when 4 players are connected.
    static private void countDown() {
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