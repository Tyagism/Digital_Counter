package com.example.counter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

// SetupActivity.java
public class SetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Your setup logic here

        // Example: Button to finish setup and go to MainActivity
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        Button finishSetup = findViewById(R.id.finishbtn);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        finishSetup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar.setProgress(100);

                Intent intent = new Intent(SetupActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish SetupActivity so it's removed from the back stack
            }
        });
    }
}
