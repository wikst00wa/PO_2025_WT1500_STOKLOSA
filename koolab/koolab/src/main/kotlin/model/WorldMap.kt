package org.example.model

import java.util.Optional
import java.util.UUID

interface WorldMap : MoveValidator {

    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(pos: Vector2d): Boolean

    fun objectAt(pos: Vector2d): Optional<Animal>

    fun getElements(): Collection<Animal>

    fun getCurrentBounds(): Boundary

    fun getId(): UUID
}