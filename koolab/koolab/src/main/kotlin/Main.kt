package org.example

import org.example.model.*

fun main() {
    val worldMap = BouncyMap(10, 10)
    val animal = Animal(Vector2d(5, 7))
    worldMap.place(animal)

    worldMap.move(animal, MoveDirection.LEFT)
    println(animal.getCurrPos())
    println(animal.getCurrOri())
    worldMap.move(animal, MoveDirection.FORWARD)
    worldMap.move(animal, MoveDirection.LEFT)
    println(animal.getCurrPos())
    println(animal.getCurrOri())
}