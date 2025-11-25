package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

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
    public String toString() {
        MapVisualizer mapVis = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(this.width - 1, this.height - 1);
        return mapVis.draw(lowerLeft, upperRight);
    }
}
