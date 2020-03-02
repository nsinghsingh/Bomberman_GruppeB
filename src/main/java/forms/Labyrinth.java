package forms;

import lombok.Getter;
import lombok.Setter;
import tiles.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Labyrinth {
    private JPanel Labyrinth;
    @Getter private JPanel GameRender; //Getter is needed for a test
    private JPanel GameInfo;
    private JLabel Time;
    private JPanel ClockIcon;
    private JLabel P1Score;
    private JLabel P2Score;
    private JLabel P3Score;
    private JLabel P4Score;
    private JLabel TieScore;
    private JPanel TieIcon;
    private JPanel P4Icon;
    private JPanel P3Icon;
    private JPanel P2Icon;
    private JPanel P1Icon;
    private JLabel Clock;
    private JLabel P1;
    private JLabel Tie;
    private JLabel P4;
    private JLabel P3;
    private JLabel P2;

    final static int Y_LENGTH = 12;
    final static int X_LENGTH = 22;
    @Getter @Setter private BasicTile[][] tiles = new BasicTile[Y_LENGTH][X_LENGTH];
    @Getter @Setter private HashMap<Integer, BasicTile> tileTypes = new HashMap<>();

    public Labyrinth(){
        GridLayout playingField = new GridLayout(Y_LENGTH, X_LENGTH, 5, 5);
        GameRender.setLayout(playingField);
        tileTypes.put(0, new EmptyTile());
        tileTypes.put(1, new UndestroyableBlock());
        tileTypes.put(2, new DestroyableBlock());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FrameDemo");
        //javax.swing.SwingUtilities.invokeLater(Labyrinth::new);
        Labyrinth labyrinth = new Labyrinth();
        int[][] mapValues = new int[12][22];
        mapValues[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        mapValues[11] = mapValues[0];
        for (int i = 0; i < mapValues.length; i++) {
            mapValues[i][0] = 1;
            mapValues[i][21] = 1;
        }
        labyrinth.loadMap(mapValues);
        frame.getContentPane().add(labyrinth.Labyrinth);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void loadMap(int[][] mapValues){
        if(loadArray(mapValues)){
            for (BasicTile[] column : getTiles()) {
                for (BasicTile tile : column) {
                    GameRender.add(tile);
                }
            }
        }
        else{
            //TODO Add error message
        }
    }

    public boolean loadArray(int[][] mapValues){
        if (getTiles().length == mapValues.length && getTiles()[0].length == mapValues[0].length){
            for (int i = 0; i < mapValues.length; i++) {
                for (int j = 0; j < mapValues[i].length; j++) {
                    BasicTile copy = getTileTypes().get(mapValues[i][j]);
                    getTiles()[i][j] = copy.clone();
                }
            }
            return checkMap();
        }
        else {
            return false;
        }
    }

    public boolean checkMap(){
        if (getTiles()[1][1].isSolid()){ return false; }
        if (getTiles()[1][X_LENGTH-2].isSolid()){ return false; }
        if (getTiles()[Y_LENGTH-2][1].isSolid()){ return false; }
        if (getTiles()[Y_LENGTH-2][X_LENGTH-2].isSolid()){ return false; }
        return true;
    }

}