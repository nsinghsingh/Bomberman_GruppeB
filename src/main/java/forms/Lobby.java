package forms;

import client.ClientMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        //Currently Creating 4 User to test The game
        for (int i = 0; i < 4; i++) {
            Lobby lobby = new Lobby();
            lobby.testStuff();
        }
    }

    public Lobby() {
        joinButton.addActionListener(this);
        usernameTextField.addMouseListener(this);
        chatField.addMouseListener(this);
        chatField.addKeyListener(this);
    }

    public void testStuff() {
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
        System.out.println("Gamestart");
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
        if (e.getSource() == joinButton) {
            if (client == null) {
                //join button
                client = new ClientMain(usernameTextField.getText(), this);
                client.start();
                if (client.IsconnectedToServer()) {
                    //is Connected to server/game --> do something
                    writeToServer("chat;", usernameTextField.getText() + " Joined!");
                } else {
                    //isnt connected to server --> do something
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

    public void writeInChat(String message) {
        System.out.println(message);
        chatArea.append(message + "\n");

    }

    public void writeToServer(String type, String message) {
        if (client != null) {
            System.out.println("wroteToServer");
            client.sendMessage(type + message);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
    public void keyReleased(KeyEvent e) {

    }
}
