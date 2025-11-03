package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> simulatedAnimals;
    private final List<MoveDirection> simulatedDirections;

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
        this.simulatedAnimals = new ArrayList<Animal>();
        for (Vector2d pos : positions) {
            this.simulatedAnimals.add(new Animal(pos));
        }
        this.simulatedDirections = new ArrayList<MoveDirection>(directions);
    }

    public void run() {
        int population = this.simulatedAnimals.size();
        int moves = this.simulatedDirections.size();

        for (int i = 0; i < moves; i++) {
            int animal_id = i % population;
            this.simulatedAnimals.get(animal_id).move(this.simulatedDirections.get(i));
            System.out.println("Zwierze " + (animal_id + 1) + " : " + this.simulatedAnimals.get(animal_id).toString());
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
