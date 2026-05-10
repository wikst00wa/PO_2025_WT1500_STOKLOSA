package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void animalPlacingWorksWhenItShould() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d validator = new Vector2d(3, 4);
        Animal animal = new Animal(validator);

        //when (nothing)

        //then
        assertTrue(map.place(animal));
    }

    @Test
    public void animalPlacingDoesNotWorkWhenPlacingPositionOutOfBounds() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d validator = new Vector2d(6, 2);
        Animal animal = new Animal(validator);

        //when (nothing)

        //then
        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal);
        });
    }

    @Test
    public void animalPlacingDoesNotWorkWhenAlreadyPlaced() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d validator = new Vector2d(3, 4);
        Animal animal = new Animal(validator);

        //when
        map.place(animal);

        //then
        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal);
        });
    }

    @Test
    public void cannotPlaceAnotherAnimalOnAnAlreadyOccupiedSpace() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d validator = new Vector2d(3, 4);
        Animal animal1 = new Animal(validator);
        Animal animal2 = new Animal(validator);

        //when
        map.place(animal1);

        //then
        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal2);
        });
    }

    @Test
    public void posReachableWhenAllIsFine() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d pos = new Vector2d(3, 2);
        Animal animal = new Animal(pos);
        Vector2d validator = new Vector2d(3, 3);

        //when
        map.place(animal);

        //then
        assertTrue(map.canMoveTo(validator));
    }

    @Test
    public void posUnreachableWhenPosOutOfBounds() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d pos = new Vector2d(3, 4);
        Animal animal = new Animal(pos);
        Vector2d validator = new Vector2d(3, 5);

        //when
        map.place(animal);

        //then
        assertFalse(map.canMoveTo(validator));
    }

    @Test
    public void posUnreachableWhenPosIsOccupied() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d pos = new Vector2d(2, 1);
        Animal animal = new Animal(pos);

        //when
        map.place(animal);

        //then
        assertFalse(map.canMoveTo(pos));
    }

    @Test
    public void animalMovesWhenItShould() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(3, 7);
        Vector2d pos = new Vector2d(1, 5);
        Animal animal = new Animal(pos);
        MoveDirection direction = MoveDirection.BACKWARD;
        Vector2d validator = new Vector2d(1, 4);

        //when
        map.place(animal);
        map.move(animal, direction);

        //then
        assertFalse(map.isOccupied(pos));       //na wszelki wypadek sprawdzam też, czy oryginalna pozycja jest już wolna
        assertTrue(map.isOccupied(validator));
    }

    @Test
    public void animalDoesNotMoveWhenAboutToLeaveMap() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(3, 7);
        Vector2d pos = new Vector2d(1, 6);
        Animal animal = new Animal(pos);
        MoveDirection direction = MoveDirection.FORWARD;
        Vector2d validator = new Vector2d(1, 6);

        //when
        map.place(animal);
        map.move(animal, direction);

        //then
        assertTrue(map.isOccupied(validator));
    }

    @Test
    public void animalDoesNotMoveWhenAnotherAnimalIsOnItsWay() throws IncorrectPositionException {
        //given
        RectangularMap map = new RectangularMap(4, 6);
        Vector2d pos1 = new Vector2d(0, 2);
        Vector2d pos2 = new Vector2d(0, 3);
        Animal animal1 = new Animal(pos1);
        Animal animal2 = new Animal(pos2);
        MoveDirection direction = MoveDirection.FORWARD;

        //when
        map.place(animal1);
        map.place(animal2);
        map.move(animal1, direction);

        //then
        assertTrue(map.isOccupied(pos1));
    }
}
