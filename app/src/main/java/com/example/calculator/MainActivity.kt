package com.example.calculator

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
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
import com.example.calculator.service.Evaluation
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var prevResult: Double = 0.0
    private var operator: String = "+" //Default
    private val eval: Eval = Eval()
    private val evaluation: Evaluation = Evaluation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recordOnClick()

    }

    //Record onClick
    private fun recordOnClick() {
        binding.run {
            btn0.setOnClickListener { onDigitClick(btn0) }
            btn1.setOnClickListener { onDigitClick(btn1) }
            btn2.setOnClickListener { onDigitClick(btn2) }
            btn3.setOnClickListener { onDigitClick(btn3) }
            btn4.setOnClickListener { onDigitClick(btn4) }
            btn5.setOnClickListener { onDigitClick(btn5) }
            btn6.setOnClickListener { onDigitClick(btn6) }
            btn7.setOnClickListener { onDigitClick(btn7) }
            btn8.setOnClickListener { onDigitClick(btn8) }
            btn9.setOnClickListener { onDigitClick(btn9) }

            btnPlus.setOnClickListener { onOperatorClick(btnPlus) }
            btnMinus.setOnClickListener { onOperatorClick(btnMinus) }
            btnDivide.setOnClickListener { onOperatorClick(btnDivide) }
            btnProduct.setOnClickListener { onOperatorClick(btnProduct) }
            btnModulo.setOnClickListener { onOperatorClick(btnModulo) }

            btnAc.setOnClickListener { onClearScreen(btnAc) }
            btnEqualTo.setOnClickListener { onEqualToClick(btnEqualTo) }
            btnBackspace.setOnClickListener { onBackSpaceClick(btnBackspace) }
            btnDot.setOnClickListener { onDigitClick(btnDot) }
            btnExpand.setOnClickListener { onExpandClick(btnExpand) }
        }
    }

    fun onDigitClick(view: View) {
        val btn = (view as AppCompatButton)
        //        set textExpression tv visibility

        binding.textExpression.visibility = View.VISIBLE // [BUG everytime setting visibility]
        //Dot Evaluation(frequency) if all set than concatenate
        if (btn.text.toString() == "." && ! evaluation.dotEvaluate(view, binding)) {
            return
        } else

        //Set on exp Tv (Concatenating digit & dot)
            binding.textExpression.text =
                binding.textExpression.text.toString() + (view as Button).text

    }

    fun onOperatorClick(view: View) {
//        set textExpression tv visibility
        binding.textExpression.visibility = View.VISIBLE
        evaluation.setOperator(view, binding)
//        binding.textExpression.text =
//            binding.textExpression.text.toString() + (view as AppCompatImageButton).contentDescription
    }


    fun onEqualToClick(view: View) {
        //case-1 remove dot or any operator if it is suffix of expression.
        val exp = binding.textExpression.text.toString()
        if(! evaluation.evaluateEqualTo(binding.textExpression.text.toString())){
            binding.textExpression.text = exp.substring(0,exp.length-1)
        }

            binding.textResult.text = "= " + eval.evaluate(binding.textExpression.text.toString())
            binding.textExpression.text = binding.textResult.text.removePrefix("= ")
            //Make expression TV invisible
            binding.textExpression.visibility = View.INVISIBLE


    }

    fun onClearScreen(view: View) {
        //        set textExpression tv visibility
        binding.textExpression.visibility = View.VISIBLE
        //case-1
        binding.textExpression.text = ""
        binding.textResult.text = "0"
    }

    fun onBackSpaceClick(view: View) {
        //case-2
        if (binding.textExpression.text.toString().isNotEmpty()) {
            binding.textExpression.text = binding.textExpression.text.substring(
                0,
                binding.textExpression.text.toString().length - 1
            )
        }
    }

    fun onExpandClick(view: View) {
        //case-4

    }


}

