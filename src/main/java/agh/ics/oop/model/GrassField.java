package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

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
    public List<WorldElement> getElements() {
        List<WorldElement> allElements = super.getElements();
        allElements.addAll(grasses.values());

        return allElements;
    }

    public boolean isOccupiedStrictlyByGrass(Vector2d position) {
        return grasses.containsKey(position);
    }
    //getter pozycji trawy - do testów integracyjnych

    @Override
    public Boundary getCurrentBounds() {

        Collection<WorldElement> mapElements = this.getElements();

        if(mapElements == null || mapElements.isEmpty()) {
            return new Boundary(new Vector2d(0,0), new Vector2d(0,0));
        }

        Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (WorldElement element : mapElements) {
            Vector2d position = element.getPosition();

            lowerLeftCorner = lowerLeftCorner.lowerLeft(position);
            upperRightCorner = upperRightCorner.upperRight(position);
        }

        return new Boundary(lowerLeftCorner, upperRightCorner);
    }
}
