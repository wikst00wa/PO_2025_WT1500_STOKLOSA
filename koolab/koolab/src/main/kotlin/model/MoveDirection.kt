package org.example.model

enum class MoveDirection(val displayName: String) {
    FORWARD("Forward"),
    BACKWARD("Backward"),
    LEFT("Left"),
    RIGHT("Right");

    override fun toString() = displayName
}
