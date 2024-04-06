package com.example.counter;

import static android.view.View.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

// SetupActivity.java
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
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the state of the switch
                SharedPreferences.Editor editor = finalPreferences1.edit();
                editor.putBoolean("switch1", isChecked);

                editor.apply();
            }
        });


        boolean isDarkModeOn = preferences.getBoolean("darkMode", false);
        switch2.setChecked(isDarkModeOn);

        SharedPreferences finalPreferences2 = preferences;
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the state of the switch and set the night mode
                SharedPreferences.Editor editor = finalPreferences2.edit();
                editor.putBoolean("darkMode", isChecked);
                editor.apply();

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        SharedPreferences finalPreferences = preferences;
        finishbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(100);

                // Save first run preference
                SharedPreferences.Editor editor = finalPreferences.edit();
                editor.putBoolean("firstRun", false);
                editor.apply();

                Intent intent = new Intent(SetupActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity(); // Finish SetupActivity so it's removed from the back stack
            }
        });
    }
}