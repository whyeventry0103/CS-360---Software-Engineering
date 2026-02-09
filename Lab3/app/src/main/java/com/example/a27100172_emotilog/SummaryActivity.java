package com.example.a27100172_emotilog;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {  // MUST extend AppCompatActivity

    private DatabaseHelper dbHelper;
    private ListView listView;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewSummary);
        tvEmpty = findViewById(R.id.tvEmptySummary);

        loadSummary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSummary();
    }

    private void loadSummary() {
        Cursor cursor = dbHelper.getDailySummary();
        ArrayList<String> summary = new ArrayList<>();

        if (cursor.getCount() == 0) {
            tvEmpty.setText("No data for summary yet.");
            listView.setVisibility(ListView.GONE);
            tvEmpty.setVisibility(TextView.VISIBLE);
        } else {
            String currentDate = "";
            while (cursor.moveToNext()) {
                int dateIndex = cursor.getColumnIndex("log_date");
                int emotionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMOTION);
                int countIndex = cursor.getColumnIndex("count");

                String date = cursor.getString(dateIndex);
                String emotion = cursor.getString(emotionIndex);
                int count = cursor.getInt(countIndex);

                // Add date header if new date
                if (!date.equals(currentDate)) {
                    summary.add("\n📅 " + date + ":");
                    currentDate = date;
                }

                summary.add("   " + emotion + ": " + count + " time(s)");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, summary);
            listView.setAdapter(adapter);

            listView.setVisibility(ListView.VISIBLE);
            tvEmpty.setVisibility(TextView.GONE);
        }
        cursor.close();
    }
}