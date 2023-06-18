package com.example.calculator.service

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.calculator.databinding.ActivityMainBinding

class Evaluation {
    //Checks for operator
    fun setOperator(view: View, binding: ActivityMainBinding) {
        val exp = binding.textExpression.text
        val operator = (view as AppCompatImageButton).contentDescription

        //Case-2 If expression console is empty than set expression console to zero
        if (exp.isEmpty()) {
            binding.textExpression.text =  binding.textExpression.text.toString() + "0" + (view as AppCompatImageButton).contentDescription
        }

        //Case-1 One operator allowed after a digit
       else if (! isOperator(exp[exp.length - 1])) binding.textExpression.text =
            binding.textExpression.text.toString() + (view as AppCompatImageButton).contentDescription
    }

    private fun isOperator(ch: Char): Boolean {
        return when (ch) {
            '+' -> true
            '-' -> true
            '÷' -> true
            'x' -> true
            '%' -> true
            else -> {
                false
            }
        }
    }

    //Dot check
    fun dotEvaluate(view: View, binding: ActivityMainBinding):Boolean {
        val text = (view as AppCompatButton).text.toString()
        // Check if already a dot is present within a digit
        return checkLeft(text, binding)
    }

    private fun checkLeft(text: String, binding: ActivityMainBinding):Boolean {
        val exp: String = binding.textExpression.text.toString()
        for (i in exp.length-1 downTo 0) {
            //Case-1 Before dot there is a operator than digit is just started
            if (isOperator(exp[i])) return true
            //Case-2 if already a dot is present
            if(exp[i] == '.') return false

        }
        return true
    }

    fun evaluateEqualTo(expression:String):Boolean{
        //Case-1 if last character is operator
       return if(isOperator(expression[expression.length-1])) false else expression[expression.length-1] != '.'
    }

}

