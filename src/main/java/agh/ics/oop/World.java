package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("Start");
        List<MoveDirection> directions = null;

        try {
            directions = OptionsParser.parseOptions(args);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(3, 4));
        ConsoleMapDisplay display = new ConsoleMapDisplay();
        GrassField map = new GrassField(10);
        map.addListener(display);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        System.out.println("Stop");
    }

    /* (Nieużywana, stara metoda run z kilku labów wstecz)
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

     */
}
