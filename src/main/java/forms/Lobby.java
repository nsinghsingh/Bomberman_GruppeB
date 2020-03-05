package forms;

import client.ClientMain;

import javax.swing.*;
import java.awt.event.*;

public class Lobby extends JFrame implements ActionListener, MouseListener, KeyListener {
    private JPanel Lobby;
    private JTextField usernameTextField;
    private JButton joinButton;
    private JTextArea chatArea;
    private JTextField chatField;
    private JList list1;
    static JFrame frame;

    ClientMain client;
    Boolean isInChatField = false;

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            testStuff();
        }
    }

    public static void testStuff() {
        frame = new JFrame("Bomberman");
        Lobby lobby = new Lobby();
        frame.getContentPane().add(lobby.Lobby);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Lobby() {
        joinButton.addActionListener(this);
        usernameTextField.addMouseListener(this);
        chatField.addMouseListener(this);
        chatField.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == joinButton) {
            if (client == null) {
                //join button
                client = new ClientMain(client, usernameTextField.getText(), this);
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
        chatArea.append(message+"\n");

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
