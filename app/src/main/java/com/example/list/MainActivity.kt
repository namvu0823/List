package com.example.list


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() < 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương hợp lệ!"
                listView.adapter = null
                return@setOnClickListener
            }

            val n = input.toInt()
            val numbers = when {
                radioEven.isChecked -> (0..n).filter { it % 2 == 0 }
                radioOdd.isChecked -> (1..n).filter { it % 2 != 0 }
                radioSquare.isChecked -> (0..n).filter { Math.sqrt(it.toDouble()).toInt().toDouble() == Math.sqrt(it.toDouble()) }
                else -> listOf()
            }

            if (numbers.isEmpty()) {
                textViewError.text = "Không có số nào thỏa mãn điều kiện."
            } else {
                textViewError.text = ""
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }
}
