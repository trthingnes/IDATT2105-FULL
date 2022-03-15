package edu.ntnu.tobiasth.backend.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Equation(var first: Double, var second: Double, var operator: Operator) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    val result get() = when (operator) {
        Operator.ADD -> first + second
        Operator.SUBTRACT -> first - second
        Operator.MULTIPLY -> first * second
        Operator.DIVIDE -> first / second
    }

    override fun toString(): String {
        return "$first $operator $second = $result"
    }

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
}