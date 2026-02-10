package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class AddCityFragment extends DialogFragment {

    // Interface to communicate with MainActivity
    interface AddCityDialogListener {
        void addCity(City city);
        void updateCity(City city);
    }

    private static final String ARG_CITY = "arg_city";

    private AddCityDialogListener listener;

    static AddCityFragment newInstance(@Nullable City city) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        if (city != null) {
            args.putSerializable(ARG_CITY, city);
        }
        fragment.setArguments(args);
        return fragment;
    }

    // This is called when the fragment is attached to the activity
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Make sure the activity implements the listener
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Inflate the custom layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_city, null);

        // Get references to the EditText fields
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        City existingCity = null;
        Bundle args = getArguments();
        if (args != null) {
            Serializable serialized = args.getSerializable(ARG_CITY);
            if (serialized instanceof City) {
                existingCity = (City) serialized;
                editCityName.setText(existingCity.getName());
                editProvinceName.setText(existingCity.getProvince());
            }
        }

        boolean isEditMode = existingCity != null;
        String title = isEditMode ? "Edit City" : "Add a City";

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        City finalExistingCity = existingCity;
        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();

                    if (finalExistingCity != null) {
                        finalExistingCity.setName(cityName);
                        finalExistingCity.setProvince(provinceName);
                        listener.updateCity(finalExistingCity);
                    } else {
                        City newCity = new City(cityName, provinceName);
                        listener.addCity(newCity);
                    }
                });

        return builder.create();
    }
}