package com.example.plantergroupproj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OreganoCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oregano_card);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeContainer, RecipeFragment.newInstance("Oregano"))
                .commit();
    }
}
