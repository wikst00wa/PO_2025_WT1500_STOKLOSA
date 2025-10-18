package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

import static agh.ics.oop.OptionsParser.parseOptions;

public class World {

    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] modified_args = Arrays.copyOfRange(parseOptions(args), 0, parseOptions(args).length);
        run(modified_args);
        System.out.println("Stop");
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
            switch (direction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            }
        }
    }
}
