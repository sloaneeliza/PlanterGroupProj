package com.example.plantergroupproj;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class RecipeDetailFragment extends Fragment {

    // on main thing where you scroll down and its basil


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
