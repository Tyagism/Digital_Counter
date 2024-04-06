package com.example.counter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    private int NowCount = 0;
    private int todayCount = 0;
    private int lifetimeCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        boolean isSwitch1On = preferences.getBoolean("switch1", false);
        TextView currentCount = findViewById(R.id.current_chip);
        if  (isSwitch1On) {
            currentCount.setVisibility(View.GONE);

        }

        SharedPreferences preference = this.getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        if (preference.getBoolean("firstRun", true)) {
            startActivity(new Intent(this, SetupActivity.class));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        @SuppressLint("CutPasteId")

        final Chip currentChip = findViewById(R.id.current_chip);
        final Chip todayChip = findViewById(R.id.today_chip);
        final Chip lifetimeChip = findViewById(R.id.lifetime_chip);
        Button button = findViewById(R.id.button);
        Button setting = findViewById(R.id.cardvieww);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (isSwitch1On) {
                    todayCount++;
                    lifetimeCount++;
                } else {
                    NowCount++;
                    if (NowCount > 108) {
                        NowCount = 1;
                        todayCount++;
                        lifetimeCount++;
                    }
                }
                currentChip.setText("Current Count: " + NowCount);
                todayChip.setText("Today's Count: " + todayCount);
                lifetimeChip.setText("Lifetime Count: " + lifetimeCount);
            }
        });


    setting.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupActivity.class));
        }
    });
    }

}

