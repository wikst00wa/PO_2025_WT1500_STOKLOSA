package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

import java.util.Collection;

public class RectangularMap extends AbstractWorldMap implements WorldMap {

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (super.canMoveTo(position) && position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height);
    }

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
