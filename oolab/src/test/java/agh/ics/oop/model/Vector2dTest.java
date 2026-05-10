package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    public void equalsIsTrueForIdenticalVectors() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        //then
        assertTrue(v1.equals(v2));
    }

    @Test
    public void equalsIsFalseForDifferentX() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 2);
        //then
        assertFalse(v1.equals(v2));
    }

    @Test
    public void equalsIsFalseForDifferentY() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 3);
        //then
        assertFalse(v1.equals(v2));
    }

    @Test
    public void equalsIsFalseForDifferentVectors() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(5, 7);
        //then
        assertFalse(v1.equals(v2));
    }

    @Test
    public void equalsIsFalseForNullComparison() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        //then
        assertFalse(v1.equals(null));
    }

    @Test
    public void equalsIsFalseForDifferentTypeComparison() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        String distractor = "Not a vector";
        //then
        assertFalse(v1.equals(distractor));
    }

    @Test
    public void equalsIsTrueWhenComparingVectorToItself() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        //then
        assertTrue(v1.equals(v1));
    }

    @Test
    public void toStringDoesItsJobOnAllNumberTypes() {
        //given
        Vector2d v1 = new Vector2d(-3, 2);
        Vector2d v2 = new Vector2d(0, 0);

        //when
        String test1 = v1.toString();
        String test2 = v2.toString();

        //then
        assertEquals("(-3,2)", test1);
        assertEquals("(0,0)", test2);
    }

    @Test
    public void precedesWorksWhenBothCoordsMakeItObvious() {
        //given - when
        Vector2d v1 = new Vector2d(-1, 2);
        Vector2d v2 = new Vector2d(3, 4);
        //then
        assertTrue(v1.precedes(v2));
    }

    @Test
    public void precedesIsFalseWhenOneCoordDoesNotSatisfy() {
        //given - when
        Vector2d v1 = new Vector2d(-1, 2);
        Vector2d v2 = new Vector2d(-3, 4);
        //then
        assertFalse(v1.precedes(v2));
    }

    @Test
    public void precedesIsFalseWhenTheOtherVectorActuallyPrecedes() {
        //given - when
        Vector2d v1 = new Vector2d(3, 4);
        Vector2d v2 = new Vector2d(-1, 2);
        //then
        assertFalse(v1.precedes(v2));
    }

    @Test
    public void precedesWorksOnTwoIdenticalVectors() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        //then
        assertTrue(v1.precedes(v2));
    }

    @Test
    public void precedesWorksOnTheSameVector() {
        //given - when
        Vector2d v1 = new Vector2d(2, 4);
        //then
        assertTrue(v1.precedes(v1));
    }

    @Test
    public void followsWorksWhenBothCoordsMakeItObvious() {
        //given - when
        Vector2d v1 = new Vector2d(3, 4);
        Vector2d v2 = new Vector2d(-1, 2);
        //then
        assertTrue(v1.follows(v2));
    }

    @Test
    public void followsIsFalseWhenOneCoordDoesNotSatisfy() {
        //given - when
        Vector2d v1 = new Vector2d(-3, 4);
        Vector2d v2 = new Vector2d(-1, 2);
        //then
        assertFalse(v1.follows(v2));
    }

    @Test
    public void followsIsFalseWhenTheOtherVectorActuallyFollows() {
        //given - when
        Vector2d v1 = new Vector2d(-1, 2);
        Vector2d v2 = new Vector2d(3, 4);
        //then
        assertFalse(v1.follows(v2));
    }

    @Test
    public void followsWorksOnTwoIdenticalVectors() {
        //given - when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        //then
        assertTrue(v1.follows(v2));
    }

    @Test
    public void followsWorksOnTheSameVector() {
        //given - when
        Vector2d v1 = new Vector2d(2, 4);
        //then
        assertTrue(v1.follows(v1));
    }

    @Test
    public void upperRightWorksWhenOneVectorDecidesItAll() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d validator = new Vector2d(2, 3);
        //when
        Vector2d test = v1.upperRight(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void upperRightWorksWhenBothVectorsDecide() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-2, 3);
        Vector2d validator = new Vector2d(1, 3);
        //when
        Vector2d test = v1.upperRight(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void upperRightWorksWhenXIsIdentical() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 3);
        Vector2d validator = new Vector2d(1, 3);
        //when
        Vector2d test = v1.upperRight(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void upperRightWorksWhenYIsIdentical() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(4, 2);
        Vector2d validator = new Vector2d(4, 2);
        //when
        Vector2d test = v1.upperRight(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void upperRightWorksWithIdenticalVectors() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.upperRight(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void upperRightWorksOnOneAndTheSameVector() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.upperRight(v1);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksWhenOneVectorDecidesItAll() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.lowerLeft(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksWhenBothVectorsDecide() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(-2, 3);
        Vector2d validator = new Vector2d(-2, 2);
        //when
        Vector2d test = v1.lowerLeft(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksWhenXIsIdentical() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 3);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.lowerLeft(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksWhenYIsIdentical() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(4, 2);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.lowerLeft(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksWithIdenticalVectors() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.lowerLeft(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void lowerLeftWorksOnOneAndTheSameVector() {
        //given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d validator = new Vector2d(1, 2);
        //when
        Vector2d test = v1.lowerLeft(v1);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void addHandlesNumericalCases() {
        //given
        Vector2d v1 = new Vector2d(-1, 0);
        Vector2d v2 = new Vector2d(3, -2);
        Vector2d validator = new Vector2d(2, -2);
        //when
        Vector2d test = v1.add(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void addHandlesSelfAddition() {
        //given
        Vector2d v1 = new Vector2d(-3, 5);
        Vector2d validator = new Vector2d(-6, 10);
        //when
        Vector2d test = v1.add(v1);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void subtractHandlesNumericalCases() {
        //given
        Vector2d v1 = new Vector2d(-1, 0);
        Vector2d v2 = new Vector2d(3, -2);
        Vector2d validator = new Vector2d(-4, 2);
        //when
        Vector2d test = v1.subtract(v2);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void subtractHandlesSelfSubtraction() {
        //given
        Vector2d v1 = new Vector2d(-3, 5);
        Vector2d validator = new Vector2d(0, 0);
        //when
        Vector2d test = v1.subtract(v1);
        //then
        assertEquals(validator, test);
    }

    @Test
    public void oppositeDoesItsJob() {
        //given
        Vector2d v1 = new Vector2d(0, -5);
        Vector2d validator = new Vector2d(0, 5);
        //when
        Vector2d test = v1.opposite();
        //then
        assertEquals(validator, test);
    }
}