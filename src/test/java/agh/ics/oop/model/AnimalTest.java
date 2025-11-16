package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    void defaultConstructorWorks() {
        //given
        Animal animal = new Animal();

        //when (nothing)

        //then
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void constructorWithDeclaredPositionWorks() {
        //given
        Vector2d pos = new Vector2d(5, 7);
        Animal animal = new Animal(pos);

        //when
        assertEquals(pos, animal.getPosition());

        //then
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void toStringReturnsTheCorrectLetter() {
        //given
        Animal animal = new Animal();
        MoveValidator validator = pos -> true;

        //when-then (testuję wszystkie możliwości)
        assertEquals("N", animal.toString());

        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("E", animal.toString());

        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("S", animal.toString());

        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("W", animal.toString());

        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("N", animal.toString());
    }

    @Test
    void isAtRecognisesAnimalPosition() {
        //given
        Vector2d pos = new Vector2d(3, 4);
        Animal animal = new Animal(pos);

        //when (nothing)

        //then
        assertTrue(animal.isAt(new Vector2d(3, 4)));
        assertFalse(animal.isAt(new Vector2d(0, 0)));
    }

    @Test
    void animalMovesCorrectly() {
        //given
        Animal animal = new Animal(new Vector2d(0, 0));
        MoveValidator validator = pos -> true;

        //when-then (idę w przód i w tył)
        animal.move(MoveDirection.FORWARD, validator);
        assertEquals(new Vector2d(0, 1), animal.getPosition());
        animal.move(MoveDirection.BACKWARD, validator);
        assertEquals(new Vector2d(0, 0), animal.getPosition());
    }

    @Test
    void animalRotatesCorrectly() {
        //given
        Animal animal = new Animal();
        MoveValidator validator = pos -> true;

        //when-then (obracam się w lewo i w prawo)
        animal.move(MoveDirection.LEFT, validator);
        assertEquals(MapDirection.WEST, animal.getOrientation());
        animal.move(MoveDirection.RIGHT, validator);
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void animalCannotMoveDueBecauseCanMoveToIsFalse() {
        //given
        Animal animal = new Animal(new Vector2d(0, 0));
        MoveValidator validator = pos -> false;

        //when
        animal.move(MoveDirection.FORWARD, validator);

        //then
        assertEquals(new Vector2d(0, 0), animal.getPosition());
    }
}
