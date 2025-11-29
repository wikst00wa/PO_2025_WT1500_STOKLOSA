package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer mapvis;
    private final List<MapChangeListener> listeners = new ArrayList<MapChangeListener>();
    private final UUID uuid = UUID.randomUUID();

    public AbstractWorldMap() {
        this.mapvis = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!animals.containsKey(position));
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position)) {
            animals.put(position, animal);
            mapHasBeenChanged("Animal has been placed on position " + position + ".");
            return true;
        }

        throw new IncorrectPositionException(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPos = animal.getPosition();
        MapDirection oldOri = animal.getOrientation();

        animal.move(direction, this);

        Vector2d newPos = animal.getPosition();
        MapDirection newOri = animal.getOrientation();

        if (!oldPos.equals(newPos)) {
            animals.remove(oldPos);
            animals.put(newPos, animal);
            mapHasBeenChanged("Animal on position " + oldPos + " has moved to " + newPos + ".");
            return;
        }

        if (!oldOri.equals(newOri)) {
            mapHasBeenChanged("Animal on position " + oldPos +
                    ", previously facing " + oldOri +
                    ", has turned to face " + newOri + ".");
        }
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

    @Override
    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return (mapvis.draw(currentBounds.lowerLeftCorner(), currentBounds.upperRightCorner()));
    }

    public void addListener(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MapChangeListener listener) {
        listeners.remove(listener);
    }

    public void mapHasBeenChanged(String message) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this, message);
        }
    }

    public UUID getId() {
        return uuid;
    }
}
