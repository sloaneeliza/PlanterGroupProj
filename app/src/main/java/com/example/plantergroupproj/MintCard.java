package com.example.plantergroupproj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MintCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mint_card);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipeContainer, RecipeFragment.newInstance("Mint"))
                .commit();
    }
}
