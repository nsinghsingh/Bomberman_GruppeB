import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabyrinthTest {

    public Labyrinth labyrinth;

    @Before
    public void setUp() throws Exception {
        labyrinth = new Labyrinth(5,6);
    }

    @Test
    public void checkMap() {
        int[][] map = { {2, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}};
        labyrinth.loadMap(map);
        assertFalse(labyrinth.checkMap());
    }

    @Test
    public void loadMap() {
        int[][] map = { {2, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}};
        assertFalse(labyrinth.loadMap(map));
    }
}