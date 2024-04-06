package com.example.counter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class SetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        SharedPreferences preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("firstRun", true)) {
            startActivity(new Intent(SetupActivity.this, MainActivity.class));
            finishAffinity();
            return;
        }

        setContentView(R.layout.activity_setup);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        Button finishbtn = findViewById(R.id.finishbtn);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch1 = findViewById(R.id.switch1);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch2 = findViewById(R.id.switch2);


        preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        SharedPreferences finalPreferences1 = preferences;
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = finalPreferences1.edit();
            editor.putBoolean("switch1", isChecked);

            editor.apply();
        });


        boolean isDarkModeOn = preferences.getBoolean("darkMode", false);
        switch2.setChecked(isDarkModeOn);

        SharedPreferences finalPreferences2 = preferences;
        switch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = finalPreferences2.edit();
            editor.putBoolean("darkMode", isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


        SharedPreferences finalPreferences = preferences;
        finishbtn.setOnClickListener(v -> {
            progressBar.setProgress(100);

            SharedPreferences.Editor editor = finalPreferences.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();

            Intent intent = new Intent(SetupActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }
}