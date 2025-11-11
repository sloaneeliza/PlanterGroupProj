package com.example.plantergroupproj;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RecipeDetailFragment extends Fragment {

    public static RecipeDetailFragment newInstance(String title, String description) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        TextView title = view.findViewById(R.id.recipeDetailTitle);
        TextView description = view.findViewById(R.id.recipeDetailDescription);

        Bundle args = getArguments();
        if (args != null) {
            title.setText(args.getString("title"));
            description.setText(args.getString("description"));
        }

        return view;
    }
}
