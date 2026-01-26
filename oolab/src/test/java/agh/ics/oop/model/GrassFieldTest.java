package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void animalPlacedOnEmptyFieldWorks() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(0,0));

        //when
        boolean placed = map.place(animal);

        //then
        assertTrue(placed);
        assertEquals(animal, map.objectAt(new Vector2d(0,0)).orElseThrow());
        assertTrue(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    void cannotPlaceAnimalOnOccupiedPosition() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(10);
        Animal a1 = new Animal(new Vector2d(0,0));
        Animal a2 = new Animal(new Vector2d(0,0));

        //when (nothing)

        //then
        assertTrue(map.place(a1));
        assertThrows(IncorrectPositionException.class, () -> {
            map.place(a2);
        });
    }

    @Test
    void mapUpdatesAfterAnimalHasMoved() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2,2));

        //when
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);

        //then
        Vector2d newPos = animal.getPosition();
        assertEquals(animal, map.objectAt(newPos).orElseThrow());
        assertFalse(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void testAnimalCannotMoveToPositionOccupiedByAnimal() throws IncorrectPositionException {
        //given
        GrassField map = new GrassField(10);
        Animal a1 = new Animal(new Vector2d(2,2));
        Animal a2 = new Animal(new Vector2d(2,3));

        //when
        map.place(a1);
        map.place(a2);

        //then
        map.move(a1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), a1.getPosition());
    }

    @Test
    void grassGenerationWithinBounds() {
        //given
        int n = 5;
        GrassField map = new GrassField(n);
        int limit = (int) Math.sqrt(10 * n);

        //when-then
        for (int x = 0; x <= limit; x++) {
            for (int y = 0; y <= limit; y++) {
                Vector2d pos = new Vector2d(x, y);
                if (map.isOccupiedStrictlyByGrass(pos)) {
                    assertTrue(pos.getX() >= 0 && pos.getX() <= limit);
                    assertTrue(pos.getY() >= 0 && pos.getY() <= limit);
                }
            }
        }
    }

    @Test
    void grassGenerationProducesNoDuplicates() {
        //given
        int n = 20;
        GrassField map = new GrassField(n);
        int grassCount = 0;
        int limit = (int) Math.sqrt(10 * n);

        //when
        for (int x = 0; x <= limit; x++) {
            for (int y = 0; y <= limit; y++) {
                if (map.isOccupiedStrictlyByGrass(new Vector2d(x, y))) {
                    grassCount++;
                }
            }
        }

        //then
        assertEquals(n, grassCount);
    }

    @Test
    void AnimalCanMoveOntoGrass() throws IncorrectPositionException {
        //given
        int n = 5;
        GrassField map = new GrassField(n);
        int limit = (int) Math.sqrt(10 * n);
        int grass_x = 0;
        int grass_y = 0;

        //when
        outer:
        for (int x = 0; x <= limit; x++) {
            for (int y = 0; y <= limit; y++) {
                Vector2d pos = new Vector2d(x, y);
                if (map.isOccupiedStrictlyByGrass(pos)) {
                    grass_x = x;
                    grass_y = y;
                    break outer;
                }
            }
        }

        Vector2d grassPos = new Vector2d(grass_x, grass_y);
        Animal animal = new Animal(grassPos);
        boolean placed = map.place(animal);

        //then
        assertTrue(placed);
        assertTrue(map.objectAt(grassPos).map(o -> o instanceof Animal).orElse(false));
        assertTrue(map.isOccupiedStrictlyByGrass(grassPos));
    }

}
