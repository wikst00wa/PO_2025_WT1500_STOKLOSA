package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
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
}
