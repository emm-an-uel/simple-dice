package com.example.dice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    lateinit var btnConfirm: Button
    lateinit var etNumDice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnConfirm = findViewById(R.id.btnConfirm)
        etNumDice = findViewById(R.id.etNumDice)

        btnConfirm.setOnClickListener {
            startDiceActivity()
        }
    }

    private fun startDiceActivity() {
        var numDice = 1
        if (etNumDice.text.isNotEmpty()) {
            numDice = etNumDice.text.toString().toInt()
        }

        if (numDice < 6) { // max of 5 dice
            val bundle = Bundle()
            bundle.putInt("numDice", numDice)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        } else {
            val view = findViewById<ConstraintLayout>(R.id.layout)
            val snackBar = Snackbar.make(view, "Max 5 dice", Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }
}