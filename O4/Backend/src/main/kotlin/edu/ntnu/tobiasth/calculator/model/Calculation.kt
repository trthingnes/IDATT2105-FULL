package edu.ntnu.tobiasth.calculator.model

@Suppress("MemberVisibilityCanBePrivate")
class Calculation(val firstNumber: Double, val secondNumber: Double, val operator: Operator) {
    val result get() = when (operator) {
        Operator.ADD -> firstNumber + secondNumber
        Operator.SUBTRACT -> firstNumber - secondNumber
        Operator.MULTIPLY -> firstNumber * secondNumber
        Operator.DIVIDE -> firstNumber / secondNumber
    }

    override fun toString(): String {
        return "$firstNumber $operator $secondNumber = $result"
    }
}