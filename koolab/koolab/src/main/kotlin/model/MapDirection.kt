package org.example.model

enum class MapDirection(val unitVector: Vector2d, val displayName: String) {
    NORTH(Vector2d(0, 1), "North"),
    EAST(Vector2d(1, 0), "East"),
    SOUTH(Vector2d(0, -1), "South"),
    WEST(Vector2d(-1, 0), "West");


    fun next() = values()[(ordinal + 1) % values().size]
    fun previous() = values()[(ordinal - 1 + values().size) % values().size]

    override fun toString() = displayName
}
