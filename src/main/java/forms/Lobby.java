package forms;

import client.ClientMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Lobby extends JFrame implements ActionListener, MouseListener {
    private JPanel Lobby;
    private JTextField usernameTextField;
    private JButton joinButton;
    private JTextArea ChatArea;
    private JTextField ChatField;
    private JList list1;
    static public JFrame frame = new JFrame("Bomberman");
    ClientMain client;

    public static void main(String[] args) {
        Lobby lobby = new Lobby();
        frame.getContentPane().add(lobby.Lobby);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    public Lobby() {
        joinButton.addActionListener(this);
        usernameTextField.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == joinButton) {
            if (client == null) {
                //join button
                client = new ClientMain(client, usernameTextField.getText());
                if (client.IsconnectedToServer()) {
                    //is Connected to server/game --> do something
                    ChatArea.append(usernameTextField.getText() + " Joined!");
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
            //usernameField
            usernameTextField.setText("");
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
}
