package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrassTest {

    @Test
    void getPositionDoesItsJob() {
        Vector2d pos = new Vector2d(3, 5);
        Grass grass = new Grass(pos);

        assertEquals(pos, grass.getPosition());
    }

    @Test
    void toStringDoesIndeedReturnAnAsterisk() {
        Grass grass = new Grass(new Vector2d(0, 0));

        assertEquals("*", grass.toString());
    }
}
