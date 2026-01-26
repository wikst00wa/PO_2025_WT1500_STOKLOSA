package org.example.model

class Animal(private var currPos: Vector2d = Vector2d(1, 2), private var currOri: MapDirection = MapDirection.NORTH)  {

    fun getCurrPos(): Vector2d = currPos

    fun getCurrOri(): MapDirection = currOri

    fun isAt(pos: Vector2d): Boolean = currPos == pos

    fun setPos(pos: Vector2d) {
        this.currPos = pos
    }

    override fun toString(): String = when (currOri) {
        MapDirection.NORTH -> "N"
        MapDirection.EAST -> "E"
        MapDirection.SOUTH -> "S"
        MapDirection.WEST -> "W"
    }

    fun move(direction: MoveDirection?, validator: MoveValidator) {
        if (direction == null) return

        val newPosition = when (direction) {
            MoveDirection.LEFT -> {
                currOri = currOri.previous()
                currPos
            }

            MoveDirection.RIGHT -> {
                currOri = currOri.next()
                currPos
            }

            MoveDirection.FORWARD -> currPos + currOri.toUnitVector()
            MoveDirection.BACKWARD -> currPos - currOri.toUnitVector()
        }

        if (validator.canMoveTo(newPosition)) {
            currPos = newPosition
        }
    }
}