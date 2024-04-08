package com.example.counter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    private var nowCount = 0
    private var todayCount = 0
    private var lifetimeCount = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val scrollView = findViewById<ScrollView>(R.id.scrollview)


        val preferences = getSharedPreferences("com.example.counter", MODE_PRIVATE)
        val isSwitch1On = preferences.getBoolean("switch1", false)
        val currentCount = findViewById<TextView>(R.id.current_chip)
        if (isSwitch1On) {
            currentCount.visibility = View.GONE
        }

        val preference = getSharedPreferences("com.example.counter", MODE_PRIVATE)
        if (preference.getBoolean("firstRun", true)) {
            startActivity(Intent(this, SetupActivity::class.java))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        @SuppressLint("CutPasteId") val currentChip = findViewById<Chip>(R.id.current_chip)
        val todayChip = findViewById<Chip>(R.id.today_chip)
        val lifetimeChip = findViewById<Chip>(R.id.lifetime_chip)
        @SuppressLint("MissingInflatedId", "LocalSuppress") val button =
            findViewById<Button>(R.id.buttoncount)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        button.setOnClickListener {
            if (isSwitch1On) {
                todayCount++
                lifetimeCount++
            } else {
                nowCount++
                if (nowCount > 108) {
                    nowCount = 1
                    todayCount++
                    lifetimeCount++
                }
            }
            currentChip.text = "Current Count: $nowCount"
            todayChip.text = "Today's Count: $todayCount"
            lifetimeChip.text = "Lifetime Count: $lifetimeCount"
        }
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentChip.visibility = View.GONE
                button.setOnClickListener {
                    if (isChecked) {
                        todayCount++
                        lifetimeCount++
                    }
                    todayChip.text = "Today's Count: $todayCount"
                    lifetimeChip.text = "Lifetime Count: $lifetimeCount"
                }
            } else {
                currentChip.visibility = View.VISIBLE
                button.setOnClickListener {
                    if (!isChecked) {
                        nowCount++
                        if (nowCount > 108) {
                            nowCount = 1
                            todayCount++
                            lifetimeCount++
                        }
                    }
                    currentChip.text = "Current Count: $nowCount"
                    todayChip.text = "Today's Count: $todayCount"
                    lifetimeChip.text = "Lifetime Count: $lifetimeCount"
                } } }








    } }
