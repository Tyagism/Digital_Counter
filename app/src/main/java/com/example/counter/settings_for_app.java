package com.example.counter;

import static com.example.counter.R.id.buttoncount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.chip.Chip;

public class settings_for_app extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_for_app);


        final int[] NowCount = {0};
        final int[] todayCount = {0};
        final int[] lifetimeCount = {0};

        SharedPreferences preferences = getSharedPreferences("com.example.counter", Context.MODE_PRIVATE);
        boolean isSwitch1On = preferences.getBoolean("switch1", false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView currentCount = findViewById(R.id.current_chip);
        if (isSwitch1On) {
            currentCount.setVisibility(View.VISIBLE);
        }


        @SuppressLint({"CutPasteId", "MissingInflatedId", "LocalSuppress"})
        final Chip currentChip = findViewById(R.id.current_chip);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        final Chip todayChip = findViewById(R.id.today_chip);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        final Chip lifetimeChip = findViewById(R.id.lifetime_chip);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button = findViewById(buttoncount);


        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (isSwitch1On) {
                    NowCount[0]++;
                    if (NowCount[0] > 108) {
                        NowCount[0] = 1;
                        todayCount[0]++;
                        lifetimeCount[0]++;
                    }
                }
                currentChip.setText("Current Count: " + NowCount[0]);
                todayChip.setText("Today's Count: " + todayCount[0]);
                lifetimeChip.setText("Lifetime Count: " + lifetimeCount[0]);
            }
        });

        Button finishbtn = findViewById(R.id.finishbtn);



    }
}
