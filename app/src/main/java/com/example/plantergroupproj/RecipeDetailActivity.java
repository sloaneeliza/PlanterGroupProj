package com.example.plantergroupproj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class RecipeDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView title = findViewById(R.id.detailTitle);
        TextView shortDesc = findViewById(R.id.detailShortDesc);
        TextView desc = findViewById(R.id.detailDescription);
        ImageView imgV = findViewById(R.id.recipeImage);

        String recipeTitle = getIntent().getStringExtra("title");
        String recipeDesc = getIntent().getStringExtra("description");
        String recipeShort = getIntent().getStringExtra("shortDesc");
        title.setText(recipeTitle);
        desc.setText(recipeDesc);
        shortDesc.setText(recipeShort);

        int imageResId = getIntent().getIntExtra("imageResId", 0);

        if (imageResId != 0) {
            imgV.setImageResource(imageResId);
        }
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        ImageView saveRecipeBtn = findViewById(R.id.saveRecipeBtn);

        String titleStr = recipeTitle;
        String shortStr = recipeShort;
        String fullStr = recipeDesc;
        int imageId = imageResId;

        saveRecipeBtn.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            Set<String> titles = prefs.getStringSet("titles", new HashSet<>());
            if (!titles.contains(titleStr)) {
                titles.add(titleStr);
                editor.putStringSet("titles", titles);

                editor.putString(titleStr + "_short", shortStr);
                editor.putString(titleStr + "_full", fullStr);
                editor.putInt(titleStr + "_image", imageId);

                editor.apply();
                Toast.makeText(this, "Recipe saved!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
