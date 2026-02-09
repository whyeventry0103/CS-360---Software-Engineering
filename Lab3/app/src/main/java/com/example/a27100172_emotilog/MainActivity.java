package com.example.a27100172_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        Log.d("DATABASE", "MainActivity - DatabaseHelper hash: " + dbHelper.hashCode());
        Log.d("DATABASE", "MainActivity - Database path: " + dbHelper.getReadableDatabase().getPath());

        setupEmotionButtons();

        // Navigation buttons
        Button btnViewLogs = findViewById(R.id.btnViewLogs);
        Button btnViewSummary = findViewById(R.id.btnViewSummary);

        btnViewLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });

        btnViewSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupEmotionButtons() {
        // Arrays of button IDs and corresponding emotions
        int[] buttonIds = {
                R.id.btnHappy, R.id.btnSad, R.id.btnAngry,
                R.id.btnExcited, R.id.btnGrateful, R.id.btnAnxious,
                R.id.btnTired, R.id.btnSurprised, R.id.btnLove
        };

        String[] emotions = {
                "Happy", "Sad", "Angry",
                "Excited", "Grateful", "Anxious",
                "Tired", "Surprised", "Love"
        };

        for (int i = 0; i < buttonIds.length; i++) {
            Button btn = findViewById(buttonIds[i]);
            final String emotion = emotions[i];

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean inserted = dbHelper.addLog(emotion);
                    if (inserted) {
                        Toast.makeText(MainActivity.this,
                                emotion + " logged!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Failed to log emotion",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}