package client;

import forms.Lobby;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread {

    private Socket socket;
    private String ip = "127.0.0.1"; // localhost
    private int port = 4456;
    private DataOutputStream dOut = null;
    private DataInputStream dIn = null;
    public String username;
    public Lobby lobby;
    public int clientID;
    private boolean isConnected;

    //Tries to connect to The server with the port and Ip
    public ClientMain(String username, Lobby lobby) {
        this.lobby = lobby;
        this.username = username;
        System.out.println("Client created");
        try {
            socket = new Socket(ip, port);
            isConnected = true;
        } catch (Exception ignored) {
            System.out.println("failed created");
            isConnected = false;
        }
    }

    //Checks if client is Connected to a Server
    public boolean IsconnectedToServer() {
        if (isConnected) {
            return true;
        } else {
            return false;
        }
    }

    //Always listens to the Server messages
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
        } catch (Exception ignore) {
        }
    }

    //receives a message from the server for all clients the same Message
    public void receiveMessage() {
        try {
            dIn = new DataInputStream(socket.getInputStream());
            String k = dIn.readUTF();
            //Since we only get a String and no Objects or any values we have to extract them with a Split.
            //0 index = method call | 1 index = message | 2 index = playerID
            String[] methodAndMessage = k.split(";");
            methodAndMessages(methodAndMessage);

        } catch (Exception e) {
            System.out.println("failedToGet MSG");
        }

    }

    /*
    gets the Extracted strings and it then will first check the first index which is the Method
    and will choose which method will be called
     */
    public void methodAndMessages(String[] methodAndMessage) {
        String playerId = "";
        String message = "";
        String method = methodAndMessage[0];
        if (methodAndMessage.length >= 2) {
            message = methodAndMessage[1];
        }
        if (methodAndMessage.length >= 3) {
            playerId = methodAndMessage[2];
        }

        switch (method) {
            case "chat":
                //chat stuff here:
                lobby.writeInChat(message);
                break;
            case "gamestart":
                //Init GameStart create Labyrinth within the Lobby
                lobby.startGame(this);
                break;
            case "clientID":
                clientID = Integer.parseInt(message);
                break;
            case "method":
                //is the actual playermovement
                playerAction(message, Integer.parseInt(playerId));
                break;
            default:
                break;

        }
    }

    //will send back the action like: w,a,s,d,n,k,b and the playerID which did the action.
    public void playerAction(String action, int playerId) {
        lobby.labyrinth.updateLabyrinth(action, playerId);
    }
}
