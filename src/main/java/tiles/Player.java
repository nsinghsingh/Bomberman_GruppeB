package tiles;

import forms.Labyrinth;

import javax.swing.*;

public class Player extends BasicTile{

    int xPosition;
    int yPosition;
    BasicTile[][] tiles;

    public Player(){
        setSolid(true);
        setDestroyable(true);
        setImage("");
    }

    public void move(){}

    public void placeBomb(){}

    public void die(){}
}
