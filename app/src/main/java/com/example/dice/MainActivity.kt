package com.example.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var tvDie1: TextView
    lateinit var tvDie2: TextView
    lateinit var tvTotal: TextView
    lateinit var btnRoll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDie1 = findViewById(R.id.tvDie1)
        tvDie2 = findViewById(R.id.tvDie2)
        tvTotal = findViewById(R.id.diceTotal)
        btnRoll = findViewById(R.id.btnRoll)

        rollDice()

        btnRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val num1 = (1..6).random()
        val num2 = (1..6).random()
        tvDie1.text = num1.toString()
        tvDie2.text = num2.toString()

        val numTotal = num1 + num2
        tvTotal.text = numTotal.toString()
    }
}