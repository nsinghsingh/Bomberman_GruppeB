package forms;import client.ClientMain;import lombok.Getter;import lombok.Setter;import java.util.Date;import tiles.*;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.util.ArrayList;import java.util.HashMap;public class Labyrinth implements KeyListener {    @Getter private JPanel Labyrinth;    @Getter private JPanel GameRender; //Getter is needed for a test    private JPanel GameInfo;    private JLabel Time;    private JPanel ClockIcon;    private JLabel P1Score;    private JLabel P2Score;    private JLabel P3Score;    private JLabel P4Score;    private JLabel TieScore;    private JPanel TieIcon;    private JPanel P4Icon;    private JPanel P3Icon;    private JPanel P2Icon;    private JPanel P1Icon;    private JLabel Clock;    private JLabel P1;    private JLabel Tie;    private JLabel P4;    private JLabel P3;    private JLabel P2;    int count = 900;    int ties = 0;    final static int Y_LENGTH = 12;    final static int X_LENGTH = 22;    final int CLIENT_ID;    final ClientMain CLIENT;    final int DELAY = 300;    public long lastRequest;    @Getter @Setter private BasicTile[][] tiles = new BasicTile[Y_LENGTH][X_LENGTH];    @Getter @Setter private HashMap<Integer, BasicTile> tileTypes = new HashMap<>();    @Getter @Setter private ArrayList<Player> players = new ArrayList<>();    public Labyrinth(ClientMain client){        CLIENT_ID = client.clientID;        CLIENT = client;        tileTypes.put(0, new EmptyTile());        tileTypes.put(1, new UndestroyableBlock());        tileTypes.put(2, new DestroyableBlock());        tileTypes.put(3, new Player(GameRender));    }    public void makeMap(){        int[][] mapValues = new int[12][22];        mapValues[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};        mapValues[11] = mapValues[0];        mapValues[1][1] = 3;        mapValues[10][1] = 3;        mapValues[1][20] = 3;        mapValues[10][20] = 3;        for (int i = 0; i < mapValues[0].length-4; i++) {            mapValues[6][i+2] = 2;            mapValues[3][i+2] = 2;            mapValues[9][i+2] = 2;        }        for (int i = 0; i < mapValues.length; i++) {            mapValues[i][0] = 1;            mapValues[i][21] = 1;        }        loadMap(mapValues);    }    public void loadMap(int[][] mapValues){        if(loadArray(mapValues)){            GridLayout playingField = new GridLayout(Y_LENGTH, X_LENGTH, 0, 0);            GameRender.setLayout(playingField);            for (int i = 0; i < getTiles().length; i++) {                for (int j = 0; j < getTiles()[i].length; j++) {                    GameRender.add(getTiles()[i][j]);                }            }        }        else{            //TODO Add error message        }    }    public boolean loadArray(int[][] mapValues){        if (getTiles().length == mapValues.length && getTiles()[0].length == mapValues[0].length){            int playerIndex = 0;            for (int i = 0; i < mapValues.length; i++) {                for (int j = 0; j < mapValues[i].length; j++) {                    getTiles()[i][j] = getTileTypes().get(mapValues[i][j]).clone();                    if(mapValues[i][j] == 3){                        players.add((Player) getTiles()[i][j]);                        players.get(players.size() -1).setPlayer(playerIndex);                        playerIndex++;                    }                }            }            return checkMap();        }        else return false;    }    public boolean checkMap(){        if (!(getTiles()[1][1] instanceof Player)){ return false; }        if (!(getTiles()[1][X_LENGTH-2] instanceof Player)){ return false; }        if (!(getTiles()[Y_LENGTH-2][1] instanceof Player)){ return false; }        return getTiles()[Y_LENGTH - 2][X_LENGTH - 2] instanceof Player;    }    public void updateLabyrinth(String action, int playerId){        switch (action){            case "b":                break;            case "k":                break;            default:                players.get(playerId).setDirection(action);        }    }    @Override    public void keyReleased(KeyEvent e) {    }    @Override    public void keyTyped(KeyEvent e) {    }    @Override    public void keyPressed(KeyEvent e) {        Date date = new Date();        if(date.getTime() - lastRequest > DELAY){            lastRequest = date.getTime();            if (e.getKeyCode() == KeyEvent.VK_W) {                CLIENT.sendMessage("method;w;" + CLIENT_ID);            }            if(e.getKeyCode() == KeyEvent.VK_S){                CLIENT.sendMessage("method;s;" + CLIENT_ID);            }            if(e.getKeyCode() == KeyEvent.VK_A){                CLIENT.sendMessage("method;a;" + CLIENT_ID);            }            if(e.getKeyCode() == KeyEvent.VK_D){                CLIENT.sendMessage("method;d;" + CLIENT_ID);            }        }    }    private void updateScoreboard(){    }    private void createUIComponents(){        Timer timer = new Timer(1000, null);        Time = new JLabel("...");        timer.addActionListener(e -> {        count--;        String countdown = String.format("%02d:%02d", count / 60, count % 60);            if (count >= 0) {                Time.setText(countdown);            } else {                ties++;                TieScore.setText(Integer.toString(ties));                P1Score.setText(Integer.toString(ties));                P2Score.setText(Integer.toString(ties));                P3Score.setText(Integer.toString(ties));                P4Score.setText(Integer.toString(ties));                ((Timer) (e.getSource())).stop();            }        });        timer.setInitialDelay(0);        timer.start();    }}