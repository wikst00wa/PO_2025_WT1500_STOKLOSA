package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class World {

    public static void main(String[] args) {
        /*
        System.out.println("Start");
        List<MoveDirection> modified_args = parseOptions(args);
        run(modified_args);
        System.out.println("Stop");
        Vector2d v1 = new Vector2d(3, 1);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(v1);
        System.out.println(animal1);
        System.out.println(animal2);
        */

        List<MoveDirection> directions = OptionsParser.parseOptions(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

/*  Stara metoda przyjmująca tablicę MoveDirection[]
    public static void run(MoveDirection[] directions) {
        for (MoveDirection direction : directions) {
            String result = switch (direction) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case LEFT -> "Zwierzak skreca w lewo";
                case RIGHT -> "Zwierzak skreca w prawo";
            };
            System.out.println(result);
        }
    }

 */

    public static void run(List<MoveDirection> directions) {

        for (MoveDirection direction : directions) {
            String result = switch (direction) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case LEFT -> "Zwierzak skreca w lewo";
                case RIGHT -> "Zwierzak skreca w prawo";
            };
            System.out.println(result);
        }
    }
}
