package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parseOptions(String[] args) {
        int n = args.length;
        int valid_args = 0;

        for (String arg : args) {
            switch (arg) {
                case "f", "b", "l", "r" -> valid_args++;
            }
        }

        MoveDirection[] res = new MoveDirection[valid_args];
        int valid_arg_index = 0;

        for (String arg : args) {
            switch (arg) {
                case "f" -> res[valid_arg_index++] = MoveDirection.FORWARD;
                case "b" -> res[valid_arg_index++] = MoveDirection.BACKWARD;
                case "l" -> res[valid_arg_index++] = MoveDirection.LEFT;
                case "r" -> res[valid_arg_index++] = MoveDirection.RIGHT;
            }
        }
        return res;
    }
}
