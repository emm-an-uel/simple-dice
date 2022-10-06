package com.example.dice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isNotEmpty
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    lateinit var btnConfirm: Button
    lateinit var numberPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnConfirm = findViewById(R.id.btnConfirm)
        numberPicker = findViewById(R.id.numberPicker)

        numberPicker.minValue = 1
        numberPicker.maxValue = 6

        btnConfirm.setOnClickListener {
            startDiceActivity()
        }
    }

    private fun startDiceActivity() {
        val numDice = numberPicker.value

        val bundle = Bundle()
        bundle.putInt("numDice", numDice)

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }
}