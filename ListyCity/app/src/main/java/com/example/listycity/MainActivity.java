package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    LinearLayout inputContainer;
    Button addCity;
    Button delCity;
    EditText cityInput;
    Button confirm;

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        delCity = findViewById(R.id.del_city);
        cityInput = findViewById(R.id.edit_city);
        confirm = findViewById(R.id.btn_confirm);
        inputContainer = findViewById(R.id.input_container);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPos = position;
            }
        });
    }

    public void updateText(View view) {
        String newCity = cityInput.getText().toString().trim();
        if (!newCity.isEmpty()) {
            dataList.add(newCity);
            cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
            cityList.setAdapter(cityAdapter);
            cityInput.setText("");
            inputContainer.setVisibility(View.GONE);
        }
    }
    public void showEnter(View view) {
        if (inputContainer.getVisibility() == View.GONE) {
            inputContainer.setVisibility(View.VISIBLE);
        } else {
            inputContainer.setVisibility(View.GONE);
        }
    }

    public void remCity(View view) {
        if (selectedPos != -1) {
            dataList.remove(selectedPos);
            cityAdapter.notifyDataSetChanged();
            selectedPos = -1;
        }
    }



}