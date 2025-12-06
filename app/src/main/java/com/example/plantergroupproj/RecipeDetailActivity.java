package com.example.plantergroupproj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class RecipeDetailActivity extends AppCompatActivity {

    private String titleStr;
    private String shortStr;
    private String fullStr;
    private int imageId;
    private String plantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // UI references
        TextView title = findViewById(R.id.detailTitle);
        TextView shortDesc = findViewById(R.id.detailShortDesc);
        TextView desc = findViewById(R.id.detailDescription);
        ImageView imgV = findViewById(R.id.recipeImage);

        // get all recipe info from the intent (sent from RecipeFragment)
        titleStr = getIntent().getStringExtra("title");
        fullStr = getIntent().getStringExtra("description");
        shortStr = getIntent().getStringExtra("shortDesc");
        imageId = getIntent().getIntExtra("imageResId", 0);
        plantName = getIntent().getStringExtra("plantName");

        // check for null title or plantName to avoid crash
        if (titleStr == null || plantName == null) {
            Log.e("RecipeDetailActivity", "Missing title or plantName in Intent!");
            Toast.makeText(this, "Recipe not found.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // set UI immediately
        title.setText(titleStr);
        shortDesc.setText(shortStr);
        desc.setText(fullStr);
        if (imageId != 0) {
            imgV.setImageResource(imageId);
        }

        // back button functionality
        ImageButton backButton = findViewById(R.id.backBtnBasil);
        backButton.setOnClickListener(v -> finish());

        // save button functionality
        ImageView saveRecipeBtn = findViewById(R.id.saveRecipeBtn);
        saveRecipeBtn.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            // just putting all the stuff in saved recipe button area
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
        }); // save button onclick listener
    } // end onCreate
} // end RecipeDetailActivity
