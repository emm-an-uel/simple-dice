package com.example.dice

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var numDice = 1
    lateinit var tableLayout: TableLayout
    lateinit var tvTotal: TextView
    lateinit var btnRoll: Button
    lateinit var idList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
        if (bundle != null) {
            numDice = bundle.getInt("numDice")
        }

        tableLayout = findViewById(R.id.tableLayout)
        tvTotal = findViewById(R.id.diceTotal)
        btnRoll = findViewById(R.id.btnRoll)
        idList = arrayListOf()

        createDice()
        rollDice()

        btnRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun createDice() {
        val tableRow = TableRow(this)

        var i = 0
        while (i < numDice) {
            val tvDie = TextView(this)

            tvDie.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )

            tvDie.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvDie.textSize = 30F

            tvDie.id = View.generateViewId()
            tvDie.text = "die${i+1}"
            idList.add(tvDie.id) // adds id to list of tvDie id's

            tableRow.addView(tvDie)
            i++
        }

        tableLayout.addView(tableRow)
    }

    private fun rollDice() {
        var diceSum = 0

        for (id in idList) {
            val tvDie = findViewById<TextView>(id)
            val num = (1..6).random()

            tvDie.text = num.toString()
            diceSum += num
        }

        tvTotal.text = diceSum.toString()
    }
}