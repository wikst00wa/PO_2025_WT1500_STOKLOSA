package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Arrays;

import static agh.ics.oop.OptionsParser.parseOptions;

public class World {

    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] modified_args = Arrays.copyOfRange(parseOptions(args), 0, parseOptions(args).length);
        run(modified_args);
        System.out.println("Stop");

        //Polecenia sprawdzające poprawność działania klasy Vector2d
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(position1.getX());
        System.out.println(position2.getY());
        //j.w., dla MapDirection
        MapDirection dir1 = MapDirection.SOUTH;
        MapDirection dir2 = MapDirection.EAST;
        System.out.println(dir1);
        System.out.println(dir1.previous());
        System.out.println(dir1.toUnitVector().add(dir2.toUnitVector()));

    }

    /* (stara metoda przyjmująca tablice łańcuchów znaków)
    public static void run(String[] args) {

        for (String arg : args) {
            switch (arg) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tylu");
                case "l" -> System.out.println("Zwierzak skreca w lewo");
                case "r" -> System.out.println("Zwierzak skreca w prawo");
            }

        }
    } */

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
}
