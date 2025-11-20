package com.example.plantergroupproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RosemaryCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosemary_card);
        ImageButton backButton = findViewById(R.id.backBtnRosemary);
        backButton.setOnClickListener(v -> {
            finish();
        });
        RecipeFragment fragment = RecipeFragment.newInstance("Rosemary");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeContainer, fragment)
                .commit();

        TextView homeBtn = findViewById(R.id.homeBtnRosemary);

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RosemaryCard.this, HomePageActivity.class);
            startActivity(intent);
        });
        TextView saveHerbBtn = findViewById(R.id.saveRosemaryBtn);

        saveHerbBtn.setOnClickListener(v -> {
            saveHerb();
        });

    }

    @SuppressLint("MutatingSharedPrefs")
    private void saveHerb() {
        android.content.SharedPreferences prefs = getSharedPreferences("MyHerbs", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = prefs.edit();

        java.util.Set<String> savedHerbs = prefs.getStringSet("savedHerbs", new java.util.HashSet<>());
        savedHerbs.add("Rosemary");

        editor.putStringSet("savedHerbs", savedHerbs);
        editor.apply();
        android.widget.Toast.makeText(this, "Rosemary" + " saved!", android.widget.Toast.LENGTH_SHORT).show();
    }
}
