import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.HashMap;

public class Labyrinth {
    private JPanel Labyrinth;
    private JPanel GameRender;
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

    @Getter @Setter private Tile[][] tiles;
    @Getter @Setter private HashMap<Integer, Tile> tileTypes = new HashMap<Integer, Tile>();

    public Labyrinth(int xLength, int yLength){
        tiles = new Tile[xLength][yLength];
        tileTypes.put(0, new Tile());
        tileTypes.put(1, new Block(false));
        tileTypes.put(2, new Block(true));
    }

    public Labyrinth(){
        this(22, 12);
    }

    public boolean checkMap(){
        int xLength = getTiles().length;
        int yLength = getTiles()[0].length;
        if (getTiles()[0][0].isSolid()){ return false; }
        if (getTiles()[0][yLength-1].isSolid()){ return false; }
        if (getTiles()[xLength-1][0].isSolid()){ return false; }
        if (getTiles()[xLength-1][yLength-1].isSolid()){ return false; }
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                int counter = 0;
                for (int k = 0; k < 9; k++) {
                    try {
                        Tile currentTile = getTiles()[i-1 + k / 3][j-1 + k % 3];
                        if(!currentTile.isDestroyable() && currentTile.isSolid()){
                            counter++;
                        }
                    }
                    catch(Exception ignored) {}
                    if(counter > 4){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean loadMap(int[][] map){
        if (getTiles().length == map.length && getTiles()[0].length == map[0].length){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    getTiles()[i][j] = getTileTypes().get(map[i][j]);
                }
            }
            //TODO Add tiles to labyrinth
            return true;
        }
        else {
            return false;
        }
    }
}
