package agh.ics.oop;


import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {

    @Test
    public void animalsHaveCorrectOrientation() {
        //given
        List<Vector2d> positions = List.of(
                new Vector2d(2, 3),
                new Vector2d(1, 1),
                new Vector2d(0, 4)
        );

        List<MoveDirection> directions = List.of(
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.RIGHT
        );

        List<MapDirection> validator = List.of(
                MapDirection.SOUTH,
                MapDirection.WEST,
                MapDirection.EAST
        );

        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();
        List<MapDirection> finalOrientations = simulation.getAnimalsOrientations();

        //then
        assertEquals(validator, finalOrientations);
    }

    @Test
    public void animalsHaveCorrectPosition() {
        //given
        List<Vector2d> positions = List.of(
                new Vector2d(2, 3),
                new Vector2d(1, 1),
                new Vector2d(0, 4)
        );

        List<MoveDirection> directions = List.of(
                MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD
        );

        List<Vector2d> validator = List.of(
                new Vector2d(2, 3),
                new Vector2d(1, 3),
                new Vector2d(0, 2)
        );

        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();
        List<Vector2d> finalPositions = simulation.getAnimalsPositions();

        //then
        assertEquals(validator, finalPositions);
    }

    @Test
    public void allTogetherNowPositionAndOrientation() {
        //given
        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4)
        );

        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(2, 3),
                new Vector2d(3, 3)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.WEST,
                MapDirection.EAST
        );

        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }

    @Test
    public void animalsDoNotLeaveMap() {
        //given
        List<Vector2d> positions = List.of(
                new Vector2d(0,0),
                new Vector2d(4,4)
        );

        List<MoveDirection> directions = List.of(
                MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(0, 0),
                new Vector2d(4, 4)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.WEST,
                MapDirection.EAST
        );

        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }

    @Test
    public void simulationAcceptsOnlyValidInputs() {
        //given
        String[] input = {"b", "f", "r", "l"};

        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(1,3)
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(2, 1),
                new Vector2d(1, 4)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.EAST,
                MapDirection.WEST
        );

        List<MoveDirection> directions = OptionsParser.parseOptions(input);
        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }

    @Test
    public void simulationAcceptsOnlyInvalidInputs() {
        //given
        String[] input = {"x", "d", "p", "h", "w"};

        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(1,3)
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(2, 2),
                new Vector2d(1, 3)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.NORTH,
                MapDirection.NORTH
        );

        List<MoveDirection> directions = OptionsParser.parseOptions(input);
        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }

    @Test
    public void simulationAcceptsMixedInputs() {
        //given
        String[] input = {"x", "f", "z", "l", "f", "g", "h", "b"};

        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(1,3)
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(2, 4),
                new Vector2d(2, 3)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.NORTH,
                MapDirection.WEST
        );

        List<MoveDirection> directions = OptionsParser.parseOptions(input);
        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }

    @Test
    public void simulationWorksForNoInputsAtAll() {
        String[] input = {};

        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(1,3)
        );

        List<Vector2d> pos_validator = List.of(
                new Vector2d(2, 2),
                new Vector2d(1, 3)
        );

        List<MapDirection> ori_validator = List.of(
                MapDirection.NORTH,
                MapDirection.NORTH
        );

        List<MoveDirection> directions = OptionsParser.parseOptions(input);
        RectangularMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);

        //when
        simulation.run();

        //then
        assertEquals(pos_validator, simulation.getAnimalsPositions());
        assertEquals(ori_validator, simulation.getAnimalsOrientations());
    }
}
