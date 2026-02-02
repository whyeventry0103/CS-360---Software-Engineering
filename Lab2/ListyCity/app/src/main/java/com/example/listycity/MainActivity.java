package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        Button addCityBtn = findViewById(R.id.add_city_btn);
        Button deleteCityBtn = findViewById(R.id.delete_city_btn);

        String[] cities = {"lahore", "karachi", "hyderabad", "faisalabad"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        cityInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                cityInput.setBackgroundResource(R.drawable.edit_text_background_focused);
            } else {
                cityInput.setBackgroundResource(R.drawable.edit_text_background);
            }
        });

        AtomicInteger selectedPosition = new AtomicInteger(-1);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition.set(position);
            Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });

        addCityBtn.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                hideKeyboard(); // ADD THIS: Hide keyboard after adding city
                Toast.makeText(MainActivity.this, "Added: " + cityName, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        deleteCityBtn.setOnClickListener(v -> {
            int pos = selectedPosition.get();
            if (pos != -1) {
                String removedCity = dataList.get(pos);
                dataList.remove(pos);
                selectedPosition.set(-1);
                cityAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Removed: " + removedCity, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Select a city first", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && cityInput != null) {
            imm.hideSoftInputFromWindow(cityInput.getWindowToken(), 0);
        }
    }

}