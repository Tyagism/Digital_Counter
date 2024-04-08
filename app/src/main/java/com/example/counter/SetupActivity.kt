package com.example.counter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        var preferences = getSharedPreferences("com.example.counter", MODE_PRIVATE)
        if (!preferences.getBoolean("firstRun", true)) {
            startActivity(Intent(this@SetupActivity, MainActivity::class.java))
            finishAffinity()
            return
        }
        @SuppressLint("MissingInflatedId", "LocalSuppress") val finishbtn =
            findViewById<Button>(R.id.finishbtn)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        @SuppressLint("UseSwitchCompatOrMaterialCode") val switch1 =
            findViewById<Switch>(R.id.switch1)
        @SuppressLint("UseSwitchCompatOrMaterialCode") val switch2 =
            findViewById<Switch>(R.id.switch2)
        preferences = getSharedPreferences("com.example.counter", MODE_PRIVATE)
        val finalPreferences1 = preferences
        switch1.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            val editor = finalPreferences1.edit()
            editor.putBoolean("switch1", isChecked)
            editor.apply()
        }
        val isDarkModeOn = preferences.getBoolean("darkMode", false)
        switch2.setChecked(isDarkModeOn)
        val finalPreferences2 = preferences
        switch2.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            val editor = finalPreferences2.edit()
            editor.putBoolean("darkMode", isChecked)
            editor.apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val finalPreferences = preferences
        finishbtn.setOnClickListener { v: View? ->
            progressBar.progress = 100
            val editor = finalPreferences.edit()
            editor.putBoolean("firstRun", false)
            editor.apply()
            val intent = Intent(this@SetupActivity, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}