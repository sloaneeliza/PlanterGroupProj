package com.example.plantergroupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {
    TextView userNameText, herbStatusText;
    Button selectHerbButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userNameText = findViewById(R.id.userNameText);
        selectHerbButton = findViewById(R.id.herbSelectBtn);

        String name = UserPrefs.getName(this);
        if (name != null) {
            userNameText.setText(name + ".");
        }
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String herb = prefs.getString("selectedHerb", null);
        prefs.edit().clear().apply(); //prefs clear every launch

        selectHerbButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, HerbSelection.class);
            startActivity(intent);
        });
    }
}
