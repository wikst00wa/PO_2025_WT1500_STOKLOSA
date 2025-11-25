package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!animals.containsKey(position));
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }

        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPos = animal.getPosition();

        animal.move(direction, this);

        Vector2d newPos = animal.getPosition();

        animals.remove(oldPos);
        animals.put(newPos, animal);

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> allElements = new ArrayList<WorldElement>();

        allElements.addAll(animals.values());

        return allElements;
    }
}
