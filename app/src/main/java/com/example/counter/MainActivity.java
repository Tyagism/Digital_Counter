package com.example.counter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity {

    private int currentCount = 0;
    private int todayCount = 0;
    private int lifetimeCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        final Chip currentChip = findViewById(R.id.current_chip);
        final Chip todayChip = findViewById(R.id.today_chip);
        final Chip lifetimeChip = findViewById(R.id.lifetime_chip);
        Button button = findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    currentCount++;
                    if (currentCount > 108) {
                        currentCount = 1;
                        todayCount++;
                        lifetimeCount++;
                    }
                    currentChip.setText("Current Count: " + currentCount);
                    todayChip.setText("Today's Count: " + todayCount);
                    lifetimeChip.setText("Lifetime Count: " + lifetimeCount);
                }



        });
    }

}
