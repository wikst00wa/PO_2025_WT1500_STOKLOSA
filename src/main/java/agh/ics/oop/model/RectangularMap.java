package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) && position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height);
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
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer mapVis = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(this.width - 1, this.height - 1);
        return mapVis.draw(lowerLeft, upperRight);
    }
}
