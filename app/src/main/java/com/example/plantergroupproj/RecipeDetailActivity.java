package com.example.plantergroupproj;

import android.content.SharedPreferences;
import android.os.Bundle;
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

    private TextView titleView, shortDescView, fullDescView;
    private ImageView imgView;
    private Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        titleView = findViewById(R.id.detailTitle);
        shortDescView = findViewById(R.id.detailShortDesc);
        fullDescView = findViewById(R.id.detailDescription);
        imgView = findViewById(R.id.recipeImage);

        ImageButton backButton = findViewById(R.id.backBtnBasil);
        backButton.setOnClickListener(v -> finish());

        String title = getIntent().getStringExtra("title");
        String plant = getIntent().getStringExtra("plantName");

        if (title == null || plant == null) {
            Toast.makeText(this, "Recipe not found.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        titleView.setText(title);

        // Fetch the full recipe from Firebase
        fetchRecipeFromFirebase(title, plant);

        findViewById(R.id.saveRecipeBtn).setOnClickListener(v ->
                saveRecipe(title, plant));
    }

    private void fetchRecipeFromFirebase(String title, String plant) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("recipes")
                .child(plant);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Recipe r = snap.getValue(Recipe.class);
                    if (r != null && title.equals(r.getTitle())) {
                        currentRecipe = r;

                        shortDescView.setText(r.getShortDescription());
                        fullDescView.setText(r.getFullDescription());
                        if (r.getImageResId() != 0) imgView.setImageResource(r.getImageResId());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecipeDetailActivity.this, "Failed to load recipe.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveRecipe(String title, String plant) {
        if (currentRecipe == null) {
            Toast.makeText(this, "Recipe still loading...", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> titles = prefs.getStringSet("titles", new HashSet<>());
        if (!titles.contains(title)) {
            titles.add(title);
            editor.putStringSet("titles", titles);

            editor.putString(title + "_plant", plant);
            editor.putString(title + "_shortDesc", currentRecipe.getShortDescription());
            editor.putString(title + "_fullDesc", currentRecipe.getFullDescription());
            editor.putInt(title + "_image", currentRecipe.getImageResId());

            editor.apply();
            Toast.makeText(this, "Recipe saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Recipe already saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
