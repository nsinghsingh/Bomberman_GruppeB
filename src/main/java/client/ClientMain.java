package client;

import forms.Lobby;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ClientMain extends Thread {

    protected Socket socket;
    String ip = "127.0.0.1"; // localhost
    int port = 4456;
    DataOutputStream dOut = null;
    DataInputStream dIn = null;
    public String username;
    public Lobby lobby;
    public int clientID;
    private boolean isConnected;

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

    public boolean IsconnectedToServer() {
        if (isConnected) {
            return true;
        } else {
            return false;
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
        } catch (Exception ignore) {
        }
    }

    //receives a message from the server for all clients the same Message
    public void receiveMessage() {
        try {
            dIn = new DataInputStream(socket.getInputStream());
            String k = dIn.readUTF();
            //0 index = method call | 1 index = message
            String[] methodAndMessage = k.split(";");
            methodAndMessages(methodAndMessage);

        } catch (Exception e) {
            System.out.println("failedToGet MSG");
        }

    }

    public void methodAndMessages(String[] methodAndMessage) {
        String playerId = "";
        String message = "";
        String method = methodAndMessage[0];
        if (methodAndMessage.length == 2) {
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
                playerAction(message, Integer.parseInt(playerId));
                break;

        }
    }

    public void playerAction(String action, int playerId) {
        lobby.labyrinth.updateLabyrinth(action, playerId);
    }
}
