package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recordOnClick()

    }

    //Record onClick
    private fun recordOnClick() {
        binding.run {
            btn0.setOnClickListener { updateExpressionTv(btn0) }
            btn1.setOnClickListener { updateExpressionTv(btn1) }
            btn2.setOnClickListener { updateExpressionTv(btn2) }
            btn3.setOnClickListener { updateExpressionTv(btn3) }
            btn4.setOnClickListener { updateExpressionTv(btn4) }
            btn5.setOnClickListener { updateExpressionTv(btn5) }
            btn6.setOnClickListener { updateExpressionTv(btn6) }
            btn7.setOnClickListener { updateExpressionTv(btn7) }
            btn8.setOnClickListener { updateExpressionTv(btn8) }
            btn9.setOnClickListener { updateExpressionTv(btn9) }
            btnAc.setOnClickListener { updateExpressionTv(btnAc) }
            btnBackspace.setOnClickListener { updateExpressionTv(btnBackspace) }
            btnModulo.setOnClickListener { updateExpressionTv(btnModulo) }
            btnDivide.setOnClickListener { updateExpressionTv(btnDivide) }
            btnProduct.setOnClickListener { updateExpressionTv(btnProduct) }
            btnMinus.setOnClickListener { updateExpressionTv(btnMinus) }
            btnPlus.setOnClickListener { updateExpressionTv(btnPlus) }
            btnEqualTO.setOnClickListener { updateExpressionTv(btnEqualTO) }
            btnDot.setOnClickListener { updateExpressionTv(btnDot) }
            btnExpand.setOnClickListener { updateExpressionTv(btnExpand) }
        }
    }

    //Clear screen
    private fun clearScreen(textView: TextView){
        textView.text = ""
    }


    //Update Expression Tv onClick
    private fun updateExpressionTv(btn: Button) {
        val btnText = btn.text.toString()
        //case-1
        if (btnText == "AC") {
            clearScreen(binding.textExpression)
        }
        //case-2
        else if (btnText == "C") {
            binding.textExpression.text = binding.textExpression.text.substring(
                0,
                binding.textExpression.text.toString().length - 1
            )
        }
        //case-3
        else if (btnText == "=") {
            clearScreen(binding.textExpression)
            doCalculate( binding.textExpression.text.toString())
        }
        //case-4
        else if (btnText == "EXP") {

        }
        //case 5
        else {
            binding.textExpression.text = binding.textExpression.text.toString() + btnText
        }

    }

    private fun doCalculate(exp: String){
        var operand1: String
        var operand2:String
        var operator: String
//        for (ch in exp){
//            if(isOperator(ch)) operator = operator + ch
//        }
    }

    //Check if token of expression is operator
    private fun isOperator(ch: Char):Boolean{
       return (ch == '+' || ch == '-' || ch == 'x' || ch == '/' || ch == '%')
    }

}

