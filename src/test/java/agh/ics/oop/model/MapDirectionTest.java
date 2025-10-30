package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    public void nextPointsAtNextDirectionClockwise() {
        //given
        MapDirection dir1 = MapDirection.NORTH;
        MapDirection dir2 = MapDirection.EAST;
        MapDirection dir3 = MapDirection.SOUTH;
        MapDirection dir4 = MapDirection.WEST;

        //when
        MapDirection test1 = dir1.next();
        MapDirection test2 = dir2.next();
        MapDirection test3 = dir3.next();
        MapDirection test4 = dir4.next();

        //then
        assertEquals(MapDirection.EAST, test1);
        assertEquals(MapDirection.SOUTH, test2);
        assertEquals(MapDirection.WEST, test3);
        assertEquals(MapDirection.NORTH, test4);
    }

    @Test
    public void previousPointsAtPreviousDirectionCounterClockwise() {
        //given
        MapDirection dir1 = MapDirection.NORTH;
        MapDirection dir2 = MapDirection.WEST;
        MapDirection dir3 = MapDirection.SOUTH;
        MapDirection dir4 = MapDirection.EAST;

        //when
        MapDirection test1 = dir1.previous();
        MapDirection test2 = dir2.previous();
        MapDirection test3 = dir3.previous();
        MapDirection test4 = dir4.previous();

        //then
        assertEquals(MapDirection.WEST, test1);
        assertEquals(MapDirection.SOUTH, test2);
        assertEquals(MapDirection.EAST, test3);
        assertEquals(MapDirection.NORTH, test4);
    }

}