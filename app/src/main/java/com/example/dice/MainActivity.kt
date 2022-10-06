package com.example.dice

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var numDice = 1
    lateinit var tableLayout: TableLayout
    lateinit var tvTotal: TextView
    lateinit var btnRoll: Button
    lateinit var btnHome: Button
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
        btnHome = findViewById(R.id.btnHome)
        idList = arrayListOf()

        createDice()
        rollDice()

        btnRoll.setOnClickListener {
            rollDice()
        }

        btnHome.setOnClickListener {
            goHome()
        }
    }

    private fun createDice() {
        val tableRow = TableRow(this)

        if (numDice == 1) { // single die - make invisible
            val tvDie = TextView(this)

            tvDie.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )

            tvDie.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvDie.textSize = 30F

            tvDie.id = View.generateViewId()
            tvDie.text = "die1"
            tvDie.visibility = View.INVISIBLE
            idList.add(tvDie.id) // adds id to list of tvDie id's

            tableRow.addView(tvDie)

        } else { // multiple dice
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
        }

        tableLayout.addView(tableRow)
    }

    private fun rollDice() {
        val diceList: ArrayList<Int> = ArrayList()

        for (id in idList) {
            val tvDie = findViewById<TextView>(id)
            val num = (1..6).random()

            tvDie.text = num.toString()
            diceList.add(num)
        }

        val diceSum = diceList.sum()
        tvTotal.text = diceSum.toString()

        // check if all dice have same number
        var isEqual = true
        for (num in diceList) {
            if (num != diceList[0]) {
                isEqual = false
            }
        }

        if (isEqual) {
            for (id in idList) {
                val tvDie = findViewById<TextView>(id)

                val colorGreen = getColor(this, com.google.android.material.R.attr.colorOnTertiary)

                tvDie.setTextColor(colorGreen)
            }
        } else {
            for (id in idList) {
                val tvDie = findViewById<TextView>(id)

                val colorDefault = getColor(this, com.google.android.material.R.attr.colorTertiary)

                tvDie.setTextColor(colorDefault)
            }
        }
    }

    private fun goHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getColor(context: Context, colorResId: Int): Int {

        val typedValue = TypedValue()
        val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(colorResId))
        val color = typedArray.getColor(0, 0)
        typedArray.recycle()
        return color
    }
}