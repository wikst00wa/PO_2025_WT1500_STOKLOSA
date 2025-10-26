package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.OptionsParser.parseOptions;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    public void optionsParserHandlesOnlyValidInputs() {
        //given
        String[] input = {"b", "b", "r", "f", "l"};
        MoveDirection[] validator = {
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.LEFT};

        //when
        MoveDirection[] test1 = parseOptions(input);

        //then
        assertArrayEquals(validator, test1);
    }

    @Test
    public void optionsParserHandlesOnlyInvalidInputs() {
        //given
        String[] input = {"x", "d", "p", "h", "w"};
        MoveDirection[] validator = {};

        //when
        MoveDirection[] test2 = parseOptions(input);

        //then
        assertArrayEquals(validator, test2);
    }

    @Test
    public void optionsParserWorksForMixedInputs() {
        //given
        String[] input = {"x", "f", "z", "l", "b", "g"};
        MoveDirection[] validator = {
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD
        };

        //when
        MoveDirection[] test3 = parseOptions(input);

        //then
        assertArrayEquals(validator, test3);
    }

    @Test
    public void optionsParserWorksForEmptyInput() {
        //given
        String[] input = {};
        MoveDirection[] validator = {};

        //when
        MoveDirection[] test4 = parseOptions(input);

        //then
        assertArrayEquals(validator, test4);
    }
}