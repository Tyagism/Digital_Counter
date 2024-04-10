package com.digital_counter.counter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.digital_counter.R
import com.google.android.material.chip.Chip
import java.util.Date
import java.util.Locale



@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private var nowCount = 0
    private var todayCount = 0
    private var lifetimeCount = 0

    @SuppressLint("SetTextI18n", "CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val txtview7 = findViewById<TextView>(R.id.textView7)
        val togglebtn = findViewById<ToggleButton>(R.id.toggleButton)
        val currentCount = findViewById<TextView>(R.id.current_chip)

        val preferenc = getSharedPreferences("com.example.digital", MODE_PRIVATE)
        val isSwitched = preferenc.getBoolean("switch", true)
        togglebtn.isChecked = isSwitched
        currentCount.visibility = if (isSwitched) View.GONE else  View.VISIBLE


        togglebtn.setOnCheckedChangeListener { _, isChecked ->
            Log.d("MainActivity", "Toggle button checked: $isChecked")
            currentCount.visibility = if (isChecked) View.GONE else  View.VISIBLE

            val editor = preferenc.edit()
            editor.putBoolean("switch", isChecked)
            editor.apply() }

        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        lifetimeCount = sharedPref.getInt("count", 0)

        txtview7.setOnClickListener {
            val url = "https://github.com/Tyagism/Digital_Counter/blob/master/Privacy_Policy.md"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val preferences = getSharedPreferences("com.example.digital", MODE_PRIVATE)
        val isSwitch1On = preferences.getBoolean("switch1", false)
        if (isSwitch1On) {
            currentCount.visibility = View.GONE }

        val preference = getSharedPreferences("com.example.digital", MODE_PRIVATE)
        if (preference.getBoolean("firstRun", true)) {
            startActivity(Intent(this, SetupActivity::class.java)) }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val todayChip = findViewById<Chip>(R.id.today_chip)
        val lifetimeChip = findViewById<Chip>(R.id.lifetime_chip)
        @SuppressLint("MissingInflatedId", "LocalSuppress")

        val buttn = findViewById<Button>(R.id.buttoncount)
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        val linearLayout = findViewById<LinearLayout>(R.id.linear_layoutv)
        val histry = findViewById<TextView>(R.id.textView6)
        val scrollView = findViewById<ScrollView>(R.id.scrol)

        var isHistoryVisible = false

        buttn.setOnClickListener {
            if (toggleButton.isChecked) {
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
            currentCount.text = "Current Count : $nowCount"
            todayChip.text = "Today's Count : $todayCount"
            lifetimeChip.text = "Lifetime Count : $lifetimeCount"

            val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("count", lifetimeCount)
            editor.apply()

            val currentDateTime = SimpleDateFormat("yy-MM-dd  hh:mm", Locale.getDefault()).format(Date())

            val newEntry = TextView(this)
            newEntry.text = "Lifetime Count : $lifetimeCount   at  $currentDateTime"

            linearLayout.addView(newEntry)
        }
        scrollView.visibility = View.GONE

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            currentCount.visibility = if (isChecked) View.GONE else View.VISIBLE }

        histry.setOnClickListener {
            isHistoryVisible = !isHistoryVisible
            scrollView.visibility = if (isHistoryVisible) View.VISIBLE else View.GONE
        }















    } }

