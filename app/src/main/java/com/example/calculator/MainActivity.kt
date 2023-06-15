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
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.service.Eval
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var prevResult: Double = 0.0
    private var operator: String = "+" //Default
    private val eval: Eval = Eval()

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

            btnPlus.setOnClickListener { updateExpressionTv(btnPlus) }
            btnMinus.setOnClickListener { updateExpressionTv(btnMinus) }
            btnDivide.setOnClickListener { updateExpressionTv(btnDivide) }
            btnProduct.setOnClickListener { updateExpressionTv(btnProduct) }
            btnModulo.setOnClickListener { updateExpressionTv(btnModulo) }

            btnAc.setOnClickListener { updateExpressionTv(btnAc) }
            btnBackspace.setOnClickListener { updateExpressionTv(btnBackspace) }
            btnEqualTO.setOnClickListener { updateExpressionTv(btnEqualTO) }
            btnDot.setOnClickListener { updateExpressionTv(btnDot) }
            btnExpand.setOnClickListener { updateExpressionTv(btnExpand) }
        }
    }

    //Clear screen
    private fun clearScreen(textView: TextView) {
        textView.text = ""
    }


    //Update Expression Tv onClick
    private fun updateExpressionTv(btn: Button) {
        val btnText = btn.text.toString()
        val expression = binding.textExpression.text.toString()
        //case-1
        if (btnText == "AC") {
            clearScreen(binding.textExpression)
        }
        //case-2
        else if (btnText == "C" && binding.textExpression.text.toString().isNotEmpty()) {
            binding.textExpression.text = binding.textExpression.text.substring(0,
                binding.textExpression.text.toString().length - 1 )
        }
        //case-3
        else if (btnText == "=") {
            clearScreen(binding.textExpression)
            binding.textResult.text = eval.evaluate(expression)
            clearScreen(binding.textExpression)
        }
        //case-4
        else if (btnText == "EXP") {

        }
        //case-5 onClick digit and Operator
        else if(btn.text.toString() != "C") {
            //Set on exp Tv
            binding.textExpression.text = binding.textExpression.text.toString() + btnText
            //Set result on Result TV
            doCalculate(prevResult, btnText, operator)
        }


    }

    private fun doCalculate(result: Double, newOperand: String, operator: String): Double {
        setResultTv(result)
        return result
    }

    //Check if token of expression is operator
    private fun isOperator(ch: Char): Boolean {
        return (ch == '+' || ch == '-' || ch == 'x' || ch == '/' || ch == '%')
    }

    //Set result Tv
    private fun setResultTv(result: Double) {

    }

//    fun onOperatorClick(view: View) {
//        operator = (view as Button).text.toString() //Update latest operator
//
//    }

}

