package com.casio.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = "0"
    private var previousInput = ""
    private var operator = ""
    private var isNewInput = true
    private var memory = 0.0
    
    // Constants for special operations
    private val MRC = "MRC"
    private val M_PLUS = "M+"
    private val M_MINUS = "M-"
    
    private val df = DecimalFormat("0.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        display = findViewById(R.id.display)
        setupButtons()
    }
    
    private fun setupButtons() {
        // Number buttons
        val numberButtons = mapOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8", R.id.btn9 to "9",
            R.id.btnDot to "."
        )
        
        numberButtons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener { onNumberClick(value) }
        }
        
        // Operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperatorClick("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperatorClick("÷") }
        
        // Function buttons
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEquals() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClear() }
        findViewById<Button>(R.id.btnAllClear).setOnClickListener { onAllClear() }
        findViewById<Button>(R.id.btnSqrt).setOnClickListener { onSqrt() }
        findViewById<Button>(R.id.btnPercent).setOnClickListener { onPercent() }
        
        // Memory buttons
        findViewById<Button>(R.id.btnMRC).setOnClickListener { onMRC() }
        findViewById<Button>(R.id.btnMPlus).setOnClickListener { onMPlus() }
        findViewById<Button>(R.id.btnMMinus).setOnClickListener { onMMinus() }
        
        // Advanced operations
        findViewById<Button>(R.id.btnSign).setOnClickListener { onSign() }
    }
    
    private fun onNumberClick(value: String) {
        if (isNewInput) {
            currentInput = value
            isNewInput = false
        } else {
            if (value == "." && currentInput.contains(".")) return
            if (currentInput == "0" && value != ".") {
                currentInput = value
            } else {
                currentInput += value
            }
        }
        updateDisplay()
    }
    
    private fun onOperatorClick(op: String) {
        if (operator.isNotEmpty() && !isNewInput) {
            calculate()
        }
        operator = op
        previousInput = currentInput
        isNewInput = true
    }
    
    private fun onEquals() {
        calculate()
        operator = ""
        isNewInput = true
    }
    
    private fun calculate() {
        if (previousInput.isEmpty() || operator.isEmpty()) return
        
        val prev = previousInput.toDoubleOrNull() ?: return
        val curr = currentInput.toDoubleOrNull() ?: return
        
        val result = when (operator) {
            "+" -> prev + curr
            "-" -> prev - curr
            "×" -> prev * curr
            "÷" -> if (curr != 0.0) prev / curr else Double.POSITIVE_INFINITY
            else -> curr
        }
        
        currentInput = formatResult(result)
        updateDisplay()
    }
    
    private fun onClear() {
        currentInput = "0"
        updateDisplay()
    }
    
    private fun onAllClear() {
        currentInput = "0"
        previousInput = ""
        operator = ""
        isNewInput = true
        updateDisplay()
    }
    
    private fun onSqrt() {
        val value = currentInput.toDoubleOrNull() ?: return
        if (value >= 0) {
            currentInput = formatResult(sqrt(value))
            updateDisplay()
            isNewInput = true
        }
    }
    
    private fun onPercent() {
        val value = currentInput.toDoubleOrNull() ?: return
        currentInput = formatResult(value / 100)
        updateDisplay()
        isNewInput = true
    }
    
    private fun onSign() {
        val value = currentInput.toDoubleOrNull() ?: return
        currentInput = formatResult(-value)
        updateDisplay()
    }
    
    private fun onMRC() {
        // Memory Recall / Memory Clear
        if (isNewInput) {
            currentInput = formatResult(memory)
            isNewInput = false
        } else {
            memory = 0.0
        }
        updateDisplay()
    }
    
    private fun onMPlus() {
        val value = currentInput.toDoubleOrNull() ?: return
        memory += value
        isNewInput = true
    }
    
    private fun onMMinus() {
        val value = currentInput.toDoubleOrNull() ?: return
        memory -= value
        isNewInput = true
    }
    
    private fun formatResult(value: Double): String {
        return if (value.isInfinite() || value.isNaN()) {
            "Error"
        } else {
            df.format(value)
        }
    }
    
    private fun updateDisplay() {
        display.text = currentInput
    }
}
