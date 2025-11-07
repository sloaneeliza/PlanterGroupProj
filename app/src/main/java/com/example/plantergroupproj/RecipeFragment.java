package com.example.plantergroupproj;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {

    private static final String ARG_HERB = "herb";

    public static RecipeFragment newInstance(String herb) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HERB, herb);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ListView listView = view.findViewById(R.id.recipeListView);

        String herb = getArguments() != null ? getArguments().getString(ARG_HERB) : "";

        // basic dummy recipe filtering - can be extended
        // TODO: fix layout to match Figma UI

        List<String> recipes = new ArrayList<>();
        switch (herb) {
            case "Basil":
                recipes.add("Caprese Salad");
                recipes.add("Pesto Pasta");
                recipes.add("Tomato Basil Soup");
                break;
            case "Mint":
                recipes.add("Mint Lemonade");
                recipes.add("Mojito");
                break;
            case "Rosemary":
                recipes.add("Rosemary Roasted Potatoes");
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                recipes
        );
        listView.setAdapter(adapter);

        return view;
    }
}
