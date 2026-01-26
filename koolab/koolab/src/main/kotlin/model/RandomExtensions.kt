package org.example.model

fun <V> Map<Vector2d, V>.randomPosition(): Vector2d? = keys.randomOrNull()

fun <V> Map<Vector2d, V>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val allPositions =
        (0 until mapSize.x).flatMap { x ->
            (0 until mapSize.y).map { y ->
                Vector2d(x, y)
            }
        }

    val freePositions = allPositions - keys

    return freePositions.randomOrNull()
}