package com.example.dice.Classes

class Dice {
    val sides = 6
    var state: Int = 1
    private set
    fun spin(): Int{
        state = (1..sides).random()
        return state
    }
}