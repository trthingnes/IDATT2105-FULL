package edu.ntnu.tobiasth.calculator.model

import java.lang.IllegalStateException

enum class Operator {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    companion object {
        fun of(string: String): Operator {
            return when (string.lowercase()) {
                "add", "+" -> ADD
                "subtract", "−" -> SUBTRACT
                "multiply", "×" -> MULTIPLY
                "divide", "÷" -> DIVIDE
                else -> throw IllegalStateException("Unknown operator '$string'")
            }
        }
    }
}