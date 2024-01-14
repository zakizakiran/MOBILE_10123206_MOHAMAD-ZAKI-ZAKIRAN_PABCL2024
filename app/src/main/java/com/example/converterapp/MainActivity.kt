package com.example.converterapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize element from layout
        val temperatureSpinner = findViewById<Spinner>(R.id.temperatureSpinner)
        val convertTempSpinner = findViewById<Spinner>(R.id.convertTempSpinner)
        val inputDegrees = findViewById<EditText>(R.id.editTextDegrees)
        val resultValue = findViewById<EditText>(R.id.editTextResult)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        //Function to convert temperature
        fun convertTemp() {
            val inputValue = inputDegrees.text.toString().toDoubleOrNull()

            if (inputValue != null) {
                val fromUnit =  temperatureSpinner.selectedItem.toString()
                val toUnit = convertTempSpinner.selectedItem.toString()

                val result = when {
                    fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (inputValue * 9 / 5) + 32
                    fromUnit == "Celsius" && toUnit == "Kelvin" -> inputValue + 273.15
                    fromUnit == "Celsius" && toUnit == "Reaumur" -> inputValue * 4 / 5
                    fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (inputValue - 32) * 5 / 9
                    fromUnit == "Fahrenheit" && toUnit == "Kelvin" -> (inputValue - 32) * 5 / 9 + 273.15
                    fromUnit == "Fahrenheit" && toUnit == "Reaumur" -> (inputValue - 32) * 4 / 9
                    fromUnit == "Kelvin" && toUnit == "Celsius" -> inputValue - 273.15
                    fromUnit == "Kelvin" && toUnit == "Fahrenheit" -> (inputValue - 273.15) * 9 / 5 + 32
                    fromUnit == "Kelvin" && toUnit == "Reaumur" -> (inputValue - 273.15) * 4 / 5
                    fromUnit == "Reaumur" && toUnit == "Celsius" -> inputValue * 5 / 4
                    fromUnit == "Reaumur" && toUnit == "Fahrenheit" -> (inputValue * 9 / 4) + 32
                    fromUnit == "Reaumur" && toUnit == "Kelvin" -> (inputValue * 5 / 4) + 273.15
                    else -> inputValue
                }

                resultValue.setText(getString(R.string.result_value_text, result.toString(), toUnit))

            } else {
                Toast.makeText(this, "Please fill the field correctly!", Toast.LENGTH_SHORT).show()
            }
        }

        //Convert button action
        convertButton.setOnClickListener{
            convertTemp()
        }

        //Clear button action
        clearButton.setOnClickListener {
            inputDegrees.setText("")
            resultValue.setText("")
        }

    }
}

