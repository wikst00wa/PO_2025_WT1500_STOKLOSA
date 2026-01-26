package org.example.model

import java.util.Optional
import java.util.UUID

class BouncyMap(private val width: Int, private val height: Int) : WorldMap {
    private val worldMap: MutableMap<Vector2d, Animal> = mutableMapOf()

    override fun isOccupied(pos: Vector2d): Boolean {
        return worldMap.containsKey(pos) && pos.x in 0 until width && pos.y in 0 until height
    }

    override fun objectAt(pos: Vector2d): Optional<Animal> {
        return Optional.ofNullable(worldMap[pos])
    }

    override fun getCurrentBounds(): Boundary {
        return Boundary(Vector2d(0, 0), Vector2d(width - 1, height - 1))
    }

    override fun getId(): UUID {
        return UUID.randomUUID()
    }

    override fun getElements(): Collection<Animal> {
        return worldMap.values.toList()
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.x in 0 until width && position.y in 0 until height
    }

    override fun place(animal: Animal) {
        val pos = animal.getCurrPos()
        if (worldMap.containsValue(animal)) {
            return
        }

        if (!isOccupied(pos)) {
            worldMap[pos] = animal
        }

        else {
            val newPosition = worldMap.randomFreePosition(getCurrentBounds().upperRight)

            if (newPosition != null) {
                animal.setPos(newPosition)
                worldMap[newPosition] = animal
            }

            else {
                val randomAnimal = worldMap.values.random()
                val randomPosition = randomAnimal.getCurrPos()
                worldMap.remove(randomPosition)
                animal.setPos(randomPosition)
                worldMap[randomPosition] = animal
            }
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPos = animal.getCurrPos()

        val newPos = when (direction) {
            MoveDirection.FORWARD -> oldPos + animal.getCurrOri().toUnitVector()
            MoveDirection.BACKWARD -> oldPos - animal.getCurrOri().toUnitVector()
            MoveDirection.LEFT -> oldPos
            MoveDirection.RIGHT -> oldPos
        }

        if (canMoveTo(newPos)) {
            animal.move(direction, this)

            if (newPos != oldPos) {
                worldMap[newPos] = animal
                worldMap.remove(oldPos)
            }
        }
    }
}