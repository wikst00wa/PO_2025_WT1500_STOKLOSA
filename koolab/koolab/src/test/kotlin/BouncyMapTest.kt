package org.example.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldNotBeIn


class BouncyMapTest : FunSpec({

    test("animal does not leave map bounds") {
        val map = BouncyMap(5, 5)
        val animal = Animal(Vector2d(0, 0))

        map.place(animal)
        animal.move(MoveDirection.BACKWARD, map)

        animal.getCurrPos() shouldBe Vector2d(0, 0)
    }

    test("randomFreePosition never returns occupied") {
        val map = mutableMapOf(
            Vector2d(0,0) to "a",
            Vector2d(1,1) to "b"
        )

        repeat(20) {
            map.randomFreePosition(Vector2d(3,3)) shouldNotBeIn map.keys
        }
    }

    test("randomFreePosition returns null on full map") {
        val map = mutableMapOf<Vector2d,String>()

        for (x in 0..1)
            for (y in 0..1)
                map[Vector2d(x,y)] = "x"

        map.randomFreePosition(Vector2d(2,2)) shouldBe null
    }

    test("upper right bound is respected") {
        val map = BouncyMap(10,10)

        map.getCurrentBounds().upperRight shouldBe Vector2d(9,9)
    }

})