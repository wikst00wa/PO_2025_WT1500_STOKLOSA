package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> simulatedAnimals;
    private final List<MoveDirection> simulatedDirections;
    private final WorldMap simulatedMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map) {
        this.simulatedAnimals = new ArrayList<Animal>();
        this.simulatedMap = map;

        for (Vector2d pos : positions) {
            Animal newAnimal = new Animal(pos);
            this.simulatedAnimals.add(newAnimal);
            this.simulatedMap.place(newAnimal);
        }
        this.simulatedDirections = new ArrayList<MoveDirection>(directions);
    }

    public void run() {
        int population = this.simulatedAnimals.size();
        int moves = this.simulatedDirections.size();

        for (int i = 0; i < moves; i++) {
            int animal_id = i % population;
            this.simulatedMap.move(this.simulatedAnimals.get(animal_id), this.simulatedDirections.get(i));
            System.out.println(this.simulatedMap);
        }
    }

    public List<Vector2d> getAnimalsPositions() {
        List<Vector2d> new_positions = new ArrayList<Vector2d>();

        for (Animal animal : simulatedAnimals) {
            new_positions.add(animal.getPosition());
        }

        return new_positions;
    }

    public List<MapDirection> getAnimalsOrientations() {
        List<MapDirection> new_orientations = new ArrayList<MapDirection>();

        for (Animal animal : simulatedAnimals) {
            new_orientations.add(animal.getOrientation());
        }

        return new_orientations;
    }
}
