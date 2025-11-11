package com.example.plantergroupproj;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView title = findViewById(R.id.detailTitle);
        TextView desc = findViewById(R.id.detailDescription);
        ImageView imgV = findViewById(R.id.recipeImage);

        String recipeTitle = getIntent().getStringExtra("title");
        String recipeDesc = getIntent().getStringExtra("description");

        title.setText(recipeTitle);
        desc.setText(recipeDesc);

        int imageResId = getIntent().getIntExtra("imageResId", 0);

        if (imageResId != 0) {
            imgV.setImageResource(imageResId);
        }
    }
}
