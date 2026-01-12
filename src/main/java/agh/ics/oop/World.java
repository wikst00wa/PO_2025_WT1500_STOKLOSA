package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.ArrayList;
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

        ArrayList<Simulation> simulations = new ArrayList<Simulation>();
        for (int i = 0; i < 2; i++) {
            GrassField map = new GrassField(10);
            map.addListener(new ConsoleMapDisplay());

            simulations.add(new Simulation(positions, directions, map));
        }

        SimulationEngine engine = new SimulationEngine(simulations);

        engine.runAsyncInThreadPool();
        try {
            engine.awaitSimulationsEnd();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop");
    }
}
