package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.OptionsParser.parseOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @Test
    public void optionsParserHandlesOnlyValidInputs() {
        //given
        String[] input = {"b", "b", "r", "f", "l"};
        List<MoveDirection> validator = new ArrayList<MoveDirection>(
             List.of(MoveDirection.BACKWARD,
                     MoveDirection.BACKWARD,
                     MoveDirection.RIGHT,
                     MoveDirection.FORWARD,
                     MoveDirection.LEFT)
        );

        //when
        List<MoveDirection> test1 = parseOptions(input);

        //then
        assertEquals(validator, test1);
    }

    @Test
    public void optionsParserHandlesOnlyInvalidInputs() {
        //given
        String[] input = {"x", "d", "p", "h", "w"};
        List<MoveDirection> validator = new ArrayList<MoveDirection>();

        //when
        List<MoveDirection> test2 = parseOptions(input);

        //then
        assertEquals(validator, test2);
    }

    @Test
    public void optionsParserWorksForMixedInputs() {
        //given
        String[] input = {"x", "f", "z", "l", "b", "g"};
        List<MoveDirection> validator = new ArrayList<MoveDirection>(
                List.of(MoveDirection.FORWARD,
                        MoveDirection.LEFT,
                        MoveDirection.BACKWARD)
        );

        //when
        List<MoveDirection> test3 = parseOptions(input);

        //then
        assertEquals(validator, test3);
    }

    @Test
    public void optionsParserWorksForEmptyInput() {
        //given
        String[] input = {};
        List<MoveDirection> validator = new ArrayList<MoveDirection>();

        //when
        List<MoveDirection> test4 = parseOptions(input);

        //then
        assertEquals(validator, test4);
    }
}