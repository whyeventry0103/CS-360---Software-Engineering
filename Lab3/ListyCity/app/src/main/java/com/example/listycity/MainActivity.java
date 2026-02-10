package com.example.listycity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    // This method is called when the dialog's "Add" button is clicked
    @Override
    public void addCity(City city) {
        // Add the new city to the adapter
        cityAdapter.add(city);
        // Notify the adapter that data has changed
        cityAdapter.notifyDataSetChanged();

        // Optional: Show a confirmation message
        Toast.makeText(this,
                "Added: " + city.getName() + ", " + city.getProvince(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCity(City city) {
        cityAdapter.notifyDataSetChanged();
        Toast.makeText(this,
                "Updated: " + city.getName() + ", " + city.getProvince(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample data
        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};

        // Initialize the data list
        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        // Setup ListView and Adapter
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // Tap to edit a city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City cityToEdit = dataList.get(position);
            AddCityFragment dialog = AddCityFragment.newInstance(cityToEdit);
            dialog.show(getSupportFragmentManager(), "EditCityDialog");
        });

        // Setup the FloatingActionButton to show the dialog
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            // Create and show the dialog fragment
            AddCityFragment dialog = AddCityFragment.newInstance(null);
            dialog.show(getSupportFragmentManager(), "AddCityDialog");
        });
    }
}