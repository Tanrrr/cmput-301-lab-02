package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityNameEditText;
    Button addCityButton;
    Button deleteCityButton;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.cityList);
        cityNameEditText = findViewById(R.id.cityNameEditText);
        addCityButton = findViewById(R.id.addCityButton);
        deleteCityButton = findViewById(R.id.deleteCityButton);

        String[] cities = {"Edmonton", "Calgary", "Vancouver", "Toronto", "Montreal", "Ottawa", "Tokyo", "Osaka", "Bejng"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Handle city selection in ListView
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                // Highlight the selected item
                cityList.setSelection(position);
            }
        });

        // Handle ADD CITY button click
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityNameEditText.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    cityNameEditText.setText("");
                    Toast.makeText(MainActivity.this, "City added: " + cityName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle DELETE CITY button click
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
                    String deletedCity = dataList.get(selectedPosition);
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                    Toast.makeText(MainActivity.this, "City deleted: " + deletedCity, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}