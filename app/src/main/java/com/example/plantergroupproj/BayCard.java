package com.example.plantergroupproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class BayCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bay_card);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
        RecipeFragment fragment = RecipeFragment.newInstance("Bay");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeContainer, fragment)
                .commit();
    }
}