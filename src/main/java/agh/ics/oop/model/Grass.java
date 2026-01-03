package agh.ics.oop.model;

public class Grass implements WorldElement {
    private final Vector2d position;

    public Grass(Vector2d pos) {
        this.position = pos;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String getImageName() {
        return "grass.png";
    }
}
