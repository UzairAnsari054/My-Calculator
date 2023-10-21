package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvCalc: TextView? = null
    var isLastNumeric = false
    var isDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvCalc = findViewById(R.id.tvCalc)
    }

    fun onDigit(view: View) {
        tvCalc?.append((view as Button).text)
        isLastNumeric = true
        isDot = false
    }


    fun onDot(view: View) {
        if (isLastNumeric && !isDot) {
            tvCalc?.append(".")
        }
        isLastNumeric = false
        isDot = true
    }

    fun onOperator(view: View) {
        tvCalc?.text?.let {
            if (isLastNumeric && !isOperatorAdded(it.toString())) {
                tvCalc?.append((view as Button).text)
                isLastNumeric = false
                isDot = false
            }
        }
    }

    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false // (means operator not added)
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if (isLastNumeric) {
            var tvValue = tvCalc?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                     tvCalc?.text = (one.toDouble() - two.toDouble()).toString()
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun onClear(view: View) {
        tvCalc?.text = ""
    }

}