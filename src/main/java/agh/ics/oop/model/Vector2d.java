package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ("(" + x + "," + y + ")");
    }

    public boolean precedes(Vector2d other) {
        return (this.x <= other.x) && (this.y <= other.y);
    }

    public boolean follows(Vector2d other) {
        return (this.x >= other.x) && (this.y >= other.y);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        if (this.x > other.x) {
            if (this.y > other.y) {
                return new Vector2d(this.x, this.y);
            }
            else {
                return new Vector2d(this.x, other.y);
            }
        }
        else {
            if (this.y > other.y) {
                return new Vector2d(other.x, this.y);
            }
            else {
                return new Vector2d(other.x, other.y);
            }
        }
    }

    public Vector2d lowerLeft(Vector2d other) {
        if (this.x < other.x) {
            if (this.y < other.y) {
                return new Vector2d(this.x, this.y);
            }
            else {
                return new Vector2d(this.x, other.y);
            }
        }
        else {
            if (this.y < other.y) {
                return new Vector2d(other.x, this.y);
            }
            else {
                return new Vector2d(other.x, other.y);
            }
        }
    }

    public Vector2d opposite() {
        return new Vector2d(this.x * (-1), this.y * (-1));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector2d vector2d)) return false;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
