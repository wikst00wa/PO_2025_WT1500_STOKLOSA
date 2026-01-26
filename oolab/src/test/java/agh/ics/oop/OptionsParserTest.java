package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.OptionsParser.parseOptions;
import static org.junit.jupiter.api.Assertions.*;

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
    public void optionsParserRejectsOnlyInvalidInputs() {
        //given
        String[] input = {"x", "d", "p", "h", "w"};

        //when (nothing)

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.parseOptions(input);
        });
    }

    @Test
    public void optionsParserRejectsMixedInputs() {
        //given
        String[] input = {"x", "f", "z", "l", "b", "g"};

        //when (nothing)

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            OptionsParser.parseOptions(input);
        });
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