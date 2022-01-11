package com.example.pruebagit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operation = null

        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnSuma.setOnClickListener(this)
        btnResta.setOnClickListener(this)
        btnmul.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
        btnReset.setOnClickListener(this)
        btnComa.setOnClickListener(this)
        btnIgual.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            btn0 -> onNumberPressed("0")
            btn1 -> onNumberPressed("1")
            btn2 -> onNumberPressed("2")
            btn3 -> onNumberPressed("3")
            btn4 -> onNumberPressed("4")
            btn5 -> onNumberPressed("5")
            btn6 -> onNumberPressed("6")
            btn7 -> onNumberPressed("7")
            btn8 -> onNumberPressed("8")
            btn9 -> onNumberPressed("9")
            btnSuma -> onOperationPressed("+")
            btnResta -> onOperationPressed("-")
            btnmul -> onOperationPressed("x")
            btnDiv -> onOperationPressed("/")
            btnReset -> onAcPressed()
            btnComa -> onNumberPressed(".")
            btnIgual -> onEqualPressed()
        }

    }

    private fun onNumberPressed(number: String) {
        renderScreen(number)
        checkOperation()

    }

    private fun renderScreen(number: String) {

        val result = if (screen.text == "0" && number != ".") {
            number
        } else {
            "${screen.text}$number"
        }

        screen.text = result

    }

    private fun checkOperation() {
        if (operation == null) {
            firstNumber = screen.text.toString().toDouble()
        } else {
            secondNumber = screen.text.toString().toDouble()
        }

    }

    private fun onOperationPressed(operator: String) {
        this.operation = operator
        firstNumber = screen.text.toString().toDouble()

        screen.text = "0"
    }

    private fun onEqualPressed() {

       try{
        if (operation != null) {
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> 0
            }

            operation = null
            firstNumber = result.toDouble()

            screen.text = if (result.toString().endsWith(".0")) {
                result.toString().replace(".0", "")
            } else {
                "%.2f".format(Locale.US, result)
            }
        }
           }catch (e : Exception){
               e.printStackTrace()
           }
    }

    private fun onAcPressed() {
        screen.text = "0"

        firstNumber = 0.0
        secondNumber = 0.0
    }
}