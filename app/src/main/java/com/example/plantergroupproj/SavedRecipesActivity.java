package com.example.plantergroupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SavedRecipesActivity extends AppCompatActivity {

    private ListView savedRecipesListView;
    private RecipeAdapter adapter;
    private List<Recipe> savedRecipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        savedRecipesListView = findViewById(R.id.savedRecipesListView);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        savedRecipesList = new ArrayList<>();
        loadSavedRecipes();
        adapter = new RecipeAdapter(this, savedRecipesList);
        savedRecipesListView.setAdapter(adapter);

        savedRecipesListView.setOnItemClickListener((parent, view, position, id) -> {
            Recipe recipe = savedRecipesList.get(position);

            Intent intent = new Intent(SavedRecipesActivity.this, RecipeDetailActivity.class);
            intent.putExtra("title", recipe.getTitle());
            intent.putExtra("shortDesc", recipe.getShortDescription());
            intent.putExtra("description", recipe.getFullDescription());
            intent.putExtra("imageResId", recipe.getImageResId());
            startActivity(intent);
        });
    }

    private void loadSavedRecipes() {
        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        Set<String> titles = prefs.getStringSet("titles", new HashSet<>());

        for (String title : titles) {

            String shortDesc = prefs.getString(title + "_short", "");
            String fullDesc = prefs.getString(title + "_full", "");
            int imageResId = prefs.getInt(title + "_image", 0);

            savedRecipesList.add(new Recipe(title, shortDesc, fullDesc, imageResId));
        }
    }
}

