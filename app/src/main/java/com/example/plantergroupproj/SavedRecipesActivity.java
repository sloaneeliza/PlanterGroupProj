package com.example.plantergroupproj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;
import java.util.HashSet;

public class SavedRecipesActivity extends AppCompatActivity {

    private LinearLayout recipeContainer;
    private TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        recipeContainer = findViewById(R.id.savedRecipeContainer);
        emptyMessage = findViewById(R.id.emptyMessage);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        loadSavedRecipes();
    }

    private void loadSavedRecipes() {

        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        Set<String> savedSet = prefs.getStringSet("recipes", new HashSet<>());

        if (savedSet.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            recipeContainer.setVisibility(View.GONE);
            return;
        }

        emptyMessage.setVisibility(View.GONE);
        recipeContainer.setVisibility(View.VISIBLE);

        for (String recipeTitle : savedSet) {

            View item = getLayoutInflater().inflate(R.layout.item_recipe, recipeContainer, false);

            TextView title = item.findViewById(R.id.recipeTitle);
            TextView desc = item.findViewById(R.id.recipeDesc);

            title.setText(recipeTitle);
            desc.setText("Tap to view recipe");

            item.setOnClickListener(v -> {
                Intent intent = new Intent(SavedRecipesActivity.this, RecipeDetailActivity.class);
                intent.putExtra("title", recipeTitle);

                startActivity(intent);
            });

            recipeContainer.addView(item);
        }
    }
}
