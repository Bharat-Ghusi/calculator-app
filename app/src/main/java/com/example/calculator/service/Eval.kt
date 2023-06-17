package com.example.calculator.service

import java.lang.ArithmeticException

class Eval {
    private var exp: String = ""
    private var result = 0.0


    private fun findPrecedence(char: Char): Pair<Int, Boolean> {
        return if (char.lowercaseChar() == 'x' || char == '/' || char == '%') Pair(
            3,
            true
        ) else if (char == '+' || char == '-') Pair(4, true) else Pair(20, false) //If digit
    }

    private fun findLowestPrecedenceIndex(): Pair<Int, Boolean> {
        // step-1 Find Lowest precedence
        var lowPrecedenceIndex = 0
        var isOperatorThere = false
        var lowPrecedence = 1000
        var i = 0
        while (i < exp.length) {
            if (exp[0] == '-' && i == 0) {
                i++
                continue
            }
            val (lowP, isTrue) = findPrecedence(exp[i])
            isOperatorThere = (isOperatorThere || isTrue)
            if (lowP < lowPrecedence) {
                lowPrecedence = lowP
                lowPrecedenceIndex = i
            }
            i++
        }

        return Pair(lowPrecedenceIndex, isOperatorThere)
    }

    private fun findLeftDigit(operatorIndex: Int): Pair<String, Int> {
        var i = operatorIndex - 1
        var str = ""
        var start = 0
//        while (i >= 0 && (exp[i] >= '0' || exp[i] <= '9' || exp[0] == '.' || exp[0] == '-')) {
//            str = exp[i] + str
//            start = i
//            i--
//        }
        while (i >= 0 && (exp[i].isDigit() || exp[i] == '.' || exp[0] == '-')) {
            str = exp[i] + str
            start = i
            i--
        }
        return Pair(str, start)
    }

    private fun findRightDigit(operatorIndex: Int): Pair<String, Int> {
        var i = operatorIndex + 1
        var str = ""
        var end = 0
        while (i < exp.length && (exp[i].isDigit() || exp[i] == '.')) {
            str += exp[i]
            end = i
            i++
        }
        return Pair(str, end)
    }

    //Main function to evaluate expression
    fun evaluate(expression: String): String {
        exp = expression
        var flag = true
        while (true) {
            val (operatorIndex, isOperatorThere) = findLowestPrecedenceIndex()
            if (!isOperatorThere) break //Base condition
            val (num1, start) = findLeftDigit(operatorIndex)
            val (num2, end) = findRightDigit(operatorIndex)
            result = doCalculate(num1, num2, operatorIndex)
            //Update the result in expression String[eg: 2+result-6*6]
            exp = exp.substring(0, start) + result + exp.substring(end + 1, exp.length)
        }
        return exp
    }

    private fun doCalculate(num1: String, num2: String, operatorIndex: Int): Double {
        return when (exp[operatorIndex]) {
            '+' -> num1.toDouble() + num2.toDouble()
            '-' -> num1.toDouble() - num2.toDouble()
            '÷' -> if (num2.toDouble() != 0.0) num1.toDouble() / num2.toDouble() else throw ArithmeticException(
                "Cannot divide by zero."
            )

            else -> num1.toDouble() * num2.toDouble()
        }

    }

}