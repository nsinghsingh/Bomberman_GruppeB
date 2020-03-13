package forms;

import client.ClientMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is the beginning entry of the Game. The Server needs to be started to play the game!
 * Initialises the first Window
 * On join creates a new Client, is the connection between the client and server not valid then the client will be set to null
 */

public class Lobby extends JFrame implements ActionListener, MouseListener, KeyListener {
    private JPanel Lobby;
    private JTextField usernameTextField;
    private JButton joinButton;
    private JTextArea chatArea;
    private JTextField chatField;
    private JList list1;
    private JFrame frame;
    public Labyrinth labyrinth;

    ClientMain client;
    Boolean isInChatField = false;

    public static void main(String[] args) {
        /*
        Lobby lobby = new Lobby();
        lobby.initJFrame();
         */

        //Currently Creating 4 User to simulate The game can be replaced with the Code above
        for (int i = 0; i < 4; i++) {
            Lobby lobby = new Lobby();
            lobby.initJFrame();
        }
    }

    public Lobby() {
        joinButton.addActionListener(this);
        usernameTextField.addMouseListener(this);
        chatField.addMouseListener(this);
        chatField.addKeyListener(this);
    }

    public void initJFrame() {
        Dimension maxSize = new Dimension(3840, 2160);
        Dimension prefSize = new Dimension(1280, 720);
        Dimension minSize = new Dimension(1070, 720);

        frame = new JFrame("Bomberman");
        frame.getContentPane().add(Lobby);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(minSize);
        frame.setPreferredSize(prefSize);
        frame.setMaximumSize(maxSize);
    }

    public void startGame(ClientMain client) {
        frame.setVisible(false);
        labyrinth = new Labyrinth(client);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(labyrinth.getLabyrinth());
        labyrinth.makeMap();
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(labyrinth);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //When the player clicks on the Join button to join a Server
        if (e.getSource() == joinButton) {
            if (client == null) {
                //join button creates the client
                client = new ClientMain(usernameTextField.getText(), this);
                if (client.IsconnectedToServer()) {
                    //starts the Clients Thread
                    client.start();
                    //is Connected to server/game
                    writeToServer("chat;", usernameTextField.getText() + " Joined!");
                } else {
                    //isn't connected to server set the client to null to remove any Pointers and will be collected by the garbage collector.
                    client = null;
                }
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == usernameTextField) {
            if (usernameTextField.getText().equalsIgnoreCase("Enter your username...")) {
                usernameTextField.setText("");
            }
        }
        if (e.getSource() == chatField) {
            if (chatField.getText().equalsIgnoreCase("Write something in the chat...")) {
                chatField.setText("");
            }
        }
        if (e.getSource() == chatField) {
            isInChatField = true;
        } else {
            isInChatField = false;
        }
    }

    //Write in the chatbox appends the message
    public void writeInChat(String message) {
        System.out.println(message);
        chatArea.append(message + "\n");

    }

    //requests a message to the Server
    public void writeToServer(String type, String message) {
        if (client != null) {
            client.sendMessage(type + message);
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isInChatField) {
                if (client != null) {
                    writeToServer("chat;", client.username + ": " + chatField.getText());
                }
            }
            chatField.setText("");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
