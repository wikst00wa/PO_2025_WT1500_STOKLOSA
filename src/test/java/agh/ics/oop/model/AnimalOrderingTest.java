package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalOrderingTest {
    @Test
    void animalsAreOrderedProperly() throws IncorrectPositionException {
        //given
        WorldMap map = new GrassField(10);

        Animal a1 = new Animal(new Vector2d(2, 5));
        Animal a2 = new Animal(new Vector2d(1, 3));
        Animal a3 = new Animal(new Vector2d(2, 1));
        Animal a4 = new Animal(new Vector2d(1, 1));

        map.place(a1);
        map.place(a2);
        map.place(a3);
        map.place(a4);

        //when
        Collection<Animal> orderedAnimals = map.getOrderedAnimals();
        Iterator<Animal> iter = orderedAnimals.iterator();

        //then
        assertEquals(a4, iter.next());
        assertEquals(a2, iter.next());
        assertEquals(a3, iter.next());
        assertEquals(a1, iter.next());
        assertFalse(iter.hasNext());
    }
}
