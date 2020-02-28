import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabyrinthTest {

    public Labyrinth labyrinth;

    @Before
    public void setUp() throws Exception {
        labyrinth = new Labyrinth();
    }

    @Test
    public void checkMap() {
        int[][] map = { {2, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}};

    }

    @Test
    public void loadMap() {

    }
}