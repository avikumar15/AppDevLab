package com.example.addtwonumbers

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var textView: TextView

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.et1)
        editText2 = findViewById(R.id.et2)

        button = findViewById(R.id.btn)

        textView = findViewById(R.id.tvAns)
        // Hack to match textview color to the background color
        textView.setBackgroundColor(Color.parseColor("#BEE0EC"))

        button.setOnClickListener { addNumbers() }
    }

    private fun addNumbers() {

        val text1 = editText1.text.toString()
        val text2 = editText2.text.toString()

        try {
            textView.setBackgroundColor(Color.parseColor("#90ee90"))
            textView.text = (text1.toInt()+text2.toInt()).toString() + " is the sum"
            Log.i(TAG, (text1.toInt()+text2.toInt()).toString())
        } catch (e: Exception) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
        }

    }

    // Static members
    companion object {
        const val TAG = "Main Activity"
    }

}
