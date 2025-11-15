package com.example.plantergroupproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BasilCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basil_card);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
        RecipeFragment fragment = RecipeFragment.newInstance("Basil");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeContainer, fragment)
                .commit();

        TextView homeBtn = findViewById(R.id.homeButton);

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(BasilCard.this, HomePageActivity.class);
            startActivity(intent);
        });

    }
}