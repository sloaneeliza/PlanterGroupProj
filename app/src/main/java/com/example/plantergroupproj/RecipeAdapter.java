package com.example.plantergroupproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context, @NonNull List<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_recipe, parent, false);
        }

        Recipe recipe = getItem(position);

        TextView title = convertView.findViewById(R.id.recipeTitle);
        TextView desc = convertView.findViewById(R.id.recipeDesc);

        title.setText(recipe.getTitle());
        desc.setText(recipe.getShortDescription());

        return convertView;
    }
}
