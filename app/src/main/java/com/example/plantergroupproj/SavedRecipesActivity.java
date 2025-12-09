package com.example.plantergroupproj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class SavedRecipesActivity extends AppCompatActivity {

    private LinearLayout recipeContainer;
    private TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        recipeContainer = findViewById(R.id.savedRecipeContainer);
        emptyMessage = findViewById(R.id.emptyMessage);

        ImageButton backButton = findViewById(R.id.backBtnBasil);
        backButton.setOnClickListener(v -> finish());

        loadSavedRecipes();
    }

    private void loadSavedRecipes() {
        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        Set<String> savedTitles = prefs.getStringSet("titles", new HashSet<>());

        recipeContainer.removeAllViews();

        if (savedTitles.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            return;
        }

        emptyMessage.setVisibility(View.GONE);

        for (String title : savedTitles) {
            String plant = prefs.getString(title + "_plant", "");
            String shortDesc = prefs.getString(title + "_shortDesc", "");
            String fullDesc = prefs.getString(title + "_fullDesc", "");
            int imageResId = prefs.getInt(title + "_image", 0);

            View item = getLayoutInflater().inflate(R.layout.item_recipe, recipeContainer, false);

            TextView titleView = item.findViewById(R.id.recipeTitle);
            TextView descView = item.findViewById(R.id.recipeDesc);
            ImageView imgView = item.findViewById(R.id.recipeImage);

            titleView.setText(title);
            descView.setText(shortDesc); // show short description in card
            if (imageResId != 0) imgView.setImageResource(imageResId);

            item.setOnClickListener(v -> {
                Intent intent = new Intent(SavedRecipesActivity.this, RecipeDetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("plantName", plant);
                startActivity(intent);
            });

            recipeContainer.addView(item);
        }
    }
}
