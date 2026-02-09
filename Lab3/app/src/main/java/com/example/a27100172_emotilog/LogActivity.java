package com.example.a27100172_emotilog;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class LogActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView listView;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        dbHelper = new DatabaseHelper(this);
        Log.d("DATABASE", "LogActivity - DatabaseHelper hash: " + dbHelper.hashCode());
        Log.d("DATABASE", "LogActivity - Database path: " + dbHelper.getReadableDatabase().getPath());
//        LogActivity - Database path: /data/user/0/com.example.a27100172_emotilog/databases/EmotiLog.db

        listView = findViewById(R.id.listViewLogs);
        tvEmpty = findViewById(R.id.tvEmpty);

        loadLogs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLogs(); // Refresh when returning to this activity
    }

    private void loadLogs() {
        Cursor cursor = dbHelper.getAllLogs();
        ArrayList<String> logs = new ArrayList<>();

        if (cursor.getCount() == 0) {
            tvEmpty.setText("No logs yet. Go back and log some emotions!");
            listView.setVisibility(ListView.GONE);
            tvEmpty.setVisibility(TextView.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int emotionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EMOTION);
                int timeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP);

                int id = cursor.getInt(idIndex);
                String emotion = cursor.getString(emotionIndex);
                String timestamp = cursor.getString(timeIndex);

                logs.add(emotion + " - " + timestamp);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, logs);
            listView.setAdapter(adapter);

            listView.setVisibility(ListView.VISIBLE);
            tvEmpty.setVisibility(TextView.GONE);
        }
        cursor.close();
    }
}