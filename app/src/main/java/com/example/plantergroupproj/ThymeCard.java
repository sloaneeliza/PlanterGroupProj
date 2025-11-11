package com.example.plantergroupproj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ThymeCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thyme_card);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeFragment, RecipeFragment.newInstance("Thyme"))
                .commit();
    }
}
