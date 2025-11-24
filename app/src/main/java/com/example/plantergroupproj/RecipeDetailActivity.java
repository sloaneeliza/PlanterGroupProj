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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView title = findViewById(R.id.detailTitle);
        TextView shortDesc = findViewById(R.id.detailShortDesc);
        TextView desc = findViewById(R.id.detailDescription);
        ImageView imgV = findViewById(R.id.recipeImage);
        // the areas to update with stuff from firebase

        FirebaseDatabase database;

        String recipeTitle = getIntent().getStringExtra("title");
        String recipeDesc = getIntent().getStringExtra("description");
        String recipeShort = getIntent().getStringExtra("shortDesc");
        title.setText(recipeTitle);
        desc.setText(recipeDesc);
        shortDesc.setText(recipeShort);

        // this is where its setting stuff with the get intent but replace this part with firebase data pull

        int imageResId = getIntent().getIntExtra("imageResId", 0);

        if (imageResId != 0) {
            imgV.setImageResource(imageResId);
        }
        ImageButton backButton = findViewById(R.id.backBtnBasil);
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


        //LAUREN WORKING
        database= FirebaseDatabase.getInstance();

        DatabaseReference recipesRef = FirebaseDatabase.getInstance().getReference("recipes");

        recipesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //runs even if data isn't changed- stupid name
                ArrayList<String> list = new ArrayList<>(); // this is global

                for (DataSnapshot child : snapshot.getChildren()) {
                    Recipe wordObj = child.getValue(Recipe.class);
                    if (wordObj != null) {
                        list.add(wordObj.getTitle()); // its a hashmap in my thing ugh this makes it deal with object instead of string
                    }
                }

                if (!list.isEmpty()) {
                   String random = list.get(new Random().nextInt(list.size()));
                   // secretWord = random.toLowerCase(); //making the random word the secret word variable then i want to check the letters against their selection
                    Log.i("LAUREN", random);
                }
            } // end on data change

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        }); // end add listener
    } // end on create
}
