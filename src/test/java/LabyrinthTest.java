import forms.Labyrinth;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabyrinthTest {

    public Labyrinth labyrinth;
    public int[][] map = new int[12][22];

    @Before
    public void setUp() {
        labyrinth = new Labyrinth(null);
        map[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        map[11] = map[0];
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][21] = 1;
        }
    }

    @Test
    public void checkMap() {
        labyrinth.loadArray(map);
        assertTrue(labyrinth.checkMap());
        map[1][1] = 1;
        labyrinth.loadArray(map);
        assertFalse(labyrinth.checkMap());
        map[1][1] = 0;
        map[3][5] = 1;
        labyrinth.loadArray(map);
        assertTrue(labyrinth.checkMap());
    }

    @Test
    public void loadMap() {
        labyrinth.loadMap(map);
        assertEquals(264, labyrinth.getGameRender().getComponentCount());
    }

    @Test
    public void loadArray() {
        assertTrue(labyrinth.loadArray(map));
        map[1][1] = 1;
        assertFalse(labyrinth.loadArray(map));
        map[1][1] = 0;
        map[3][5] = 1;
        assertTrue(labyrinth.loadArray(map));
    }
}