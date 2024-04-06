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

// SetupActivity.java
public class SetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        SharedPreferences preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        if (!preferences.getBoolean("firstRun", true)) {
            // If it's not the first run, start MainActivity and finish SetupActivity
            startActivity(new Intent(SetupActivity.this, MainActivity.class));
            finishAffinity();
            return;
        }

        setContentView(R.layout.activity_setup);

        // Example: Button to finish setup and go to MainActivity
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        Button finishSetup = findViewById(R.id.finishbtn);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch1 = findViewById(R.id.switch1);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch2 = findViewById(R.id.switch2);


        preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the state of the switch
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch1", isChecked);
                editor.apply();
            }

        finishSetup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(100);

                // Save first run preference
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("firstRun", false);
                editor.apply();

                Intent intent = new Intent(SetupActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity(); // Finish SetupActivity so it's removed from the back stack
            }
        });
    }
}
