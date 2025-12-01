package agh.ics.oop;


import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    /* Stara metoda zwracająca tablicę MoveDirection[]
    public static MoveDirection[] parseOptions(String[] args) {
        int valid_args = 0;

        for (String arg : args) {
            switch (arg) {
                case "f", "b", "l", "r" -> valid_args++;
            }
        }

        MoveDirection[] res = new MoveDirection[valid_args];
        int valid_arg_index = 0;

        for (String arg : args) {
            MoveDirection dir = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };

            if (dir != null) {
                res[valid_arg_index++] = dir;
            }
        }

        return res;
    }
*/

    public static List<MoveDirection> parseOptions(String[] args) throws IllegalArgumentException {
        List<MoveDirection> res = new ArrayList<MoveDirection>();

        for (String arg : args) {
                switch (arg) {
                    case "f" -> res.add(MoveDirection.FORWARD);
                    case "b" -> res.add(MoveDirection.BACKWARD);
                    case "l" -> res.add(MoveDirection.LEFT);
                    case "r" -> res.add(MoveDirection.RIGHT);
                    default -> throw new IllegalArgumentException(arg + " is not a correct direction specification");
                }
        }

        return res;
    }
}


