package agh.ics.oop.model;

public class Animal implements WorldElement {
    private Vector2d position;
    private MapDirection orientation;
    public static final Vector2d DEFAULT_POSITION = new Vector2d(2, 2);
    public static final MapDirection DEFAULT_ORIENTATION = MapDirection.NORTH;

    public Animal() {
        this.position = DEFAULT_POSITION;
        this.orientation = DEFAULT_ORIENTATION;
    }

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = DEFAULT_ORIENTATION;
    }

    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    //gettery - przydadzą się w testach integracyjnych
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case LEFT: {
                this.orientation = this.orientation.previous();
                break;
            }

            case RIGHT: {
                this.orientation = this.orientation.next();
                break;
            }

            case FORWARD: {
                Vector2d newPos = this.position.add(this.orientation.toUnitVector());

                if (validator.canMoveTo(newPos)) {
                    this.position = newPos;
                }
                break;
            }

            case BACKWARD: {
                Vector2d newPos = this.position.add(this.orientation.toUnitVector().opposite());

                if (validator.canMoveTo(newPos)) {
                    this.position = newPos;
                }
                break;
            }
        }
    }

    @Override
    public String getImageName() {
        return switch (this.orientation) {
            case NORTH -> "up.png";
            case EAST -> "right.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
        };
    }
}