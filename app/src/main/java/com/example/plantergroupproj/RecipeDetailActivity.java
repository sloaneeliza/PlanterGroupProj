package com.example.plantergroupproj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        // get basic info from intent
        titleStr = getIntent().getStringExtra("title");
        plantName = getIntent().getStringExtra("plantName");

        // check for null title or plantName to avoid crash
        if (titleStr == null || plantName == null) {
            Log.e("RecipeDetailActivity", "Missing title or plantName in Intent!");
            Toast.makeText(this, "Recipe not found.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // set title immediately so user sees something
        title.setText(titleStr);

        // back button functionality
        ImageButton backButton = findViewById(R.id.backBtnBasil);
        backButton.setOnClickListener(v -> finish());

        // save button functionality
        ImageView saveRecipeBtn = findViewById(R.id.saveRecipeBtn);
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

        //
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference recipesRef = database.getReference("recipes").child(plantName);

        // fetch all recipes for this plant and find the one matching the title
        recipesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean found = false;
                for (DataSnapshot recipeSnap : snapshot.getChildren()) {
                    Recipe r = recipeSnap.getValue(Recipe.class);
                    if (r != null && r.getTitle().equals(titleStr)) {
                        // update UI with Firebase data
                        shortStr = r.getShortDescription();
                        fullStr = r.getFullDescription();
                        imageId = r.getImageResId();

                        shortDesc.setText(shortStr);
                        desc.setText(fullStr);
                        if (imageId != 0) imgV.setImageResource(imageId);

                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Toast.makeText(RecipeDetailActivity.this, "Recipe not found in database.", Toast.LENGTH_SHORT).show();
                    Log.w("RecipeDetailActivity", "Recipe with title " + titleStr + " not found under " + plantName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RecipeDetailActivity", "Firebase load failed: " + error.getMessage());
                Toast.makeText(RecipeDetailActivity.this, "Failed to load recipe.", Toast.LENGTH_SHORT).show();
            }
        });
    } // end onCreate
} // end RecipeDetailActivity
