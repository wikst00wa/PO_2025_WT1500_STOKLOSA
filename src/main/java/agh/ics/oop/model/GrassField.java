package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int n) {
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            while (true) {
                int x = rand.nextInt((int) Math.sqrt((10 * n)));
                int y = rand.nextInt((int) Math.sqrt((10 * n)));
                Vector2d pos_candidate = new Vector2d(x, y);

                if (!isOccupiedStrictlyByGrass(pos_candidate)) {
                    Grass grass = new Grass(pos_candidate);
                    grasses.put(pos_candidate, grass);
                    break;
                }
            }
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return super.objectAt(position);
        }
        else {
            return grasses.get(position);
        }
    }

    @Override
    public String toString() {
        MapVisualizer mapVis = new MapVisualizer(this);

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Vector2d pos : animals.keySet()) {
            int x = pos.getX();
            int y = pos.getY();

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        for (Vector2d pos : grasses.keySet()) {
            int x = pos.getX();
            int y = pos.getY();

            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        Vector2d lowerLeft = new Vector2d(minX, minY);
        Vector2d upperRight = new Vector2d(maxX, maxY);
        return mapVis.draw(lowerLeft, upperRight);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> allElements = super.getElements();
        allElements.addAll(grasses.values());

        return allElements;
    }

    public boolean isOccupiedStrictlyByGrass(Vector2d position) {
        return grasses.containsKey(position);
    }
    //getter pozycji trawy - do testów integracyjnych
}
