package com.example.plantergroupproj;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayout recipesContainer = view.findViewById(R.id.recipeContainer);
        String herb = getArguments() != null ? getArguments().getString(ARG_HERB) : "";

        List<Recipe> recipes = new ArrayList<>();

        switch (herb) {
            case "Basil":
                recipes.add(new Recipe(
                        "Caprese Salad",
                        "Fresh tomatoes, mozzarella, and basil drizzled with olive oil.",
                        "Ingredients:\n" +
                                "- 3 tomatoes\n" +
                                "- Fresh basil leaves\n" +
                                "- Fresh mozzarella\n" +
                                "- Olive oil\n" +
                                "- Salt & pepper\n\n" +
                                "Instructions:\n" +
                                "1. Slice tomatoes and mozzarella.\n" +
                                "2. Layer tomato, mozzarella, and basil.\n" +
                                "3. Drizzle with olive oil.\n" +
                                "4. Add salt and pepper to taste.",
                        R.drawable.caprese
                ));
                recipes.add(new Recipe(
                        "Walnut Pesto",
                        "Serve on pasta or use as a dressing or marinade.",
                        "Ingredients:\n" +
                                "- 2 cups fresh basil leaves\n" +
                                "- 1/2 cup walnuts\n" +
                                "- 1/2 cup grated parmesan\n" +
                                "- 2 cloves garlic\n" +
                                "- 1/2 cup olive oil\n" +
                                "- Salt & pepper to taste\n\n" +
                                "Instructions:\n" +
                                "1. Add basil, walnuts, garlic, and parmesan to a food processor.\n" +
                                "2. Pulse several times until roughly chopped.\n" +
                                "3. Slowly drizzle in olive oil while blending.\n" +
                                "4. Blend until smooth and creamy.\n" +
                                "5. Season with salt and pepper to taste.",
                        R.drawable.walnutpesto
                ));
                break;

            case "Mint":
                // Add mint recipes here
                break;

            case "Rosemary":
                // Add rosemary recipes here
                break;
        }

        //add each recipe to the container
        for (Recipe recipe : recipes) {
            View recipeItemView = createRecipeItemView(inflater, recipesContainer, recipe);
            recipesContainer.addView(recipeItemView);
        }
        return view;
    }

    private View createRecipeItemView(LayoutInflater inflater, ViewGroup parent, Recipe recipe) {
        View recipeItemView = inflater.inflate(R.layout.item_recipe, parent, false);
        TextView title = recipeItemView.findViewById(R.id.recipeTitle);
        TextView description = recipeItemView.findViewById(R.id.recipeDesc);
        title.setText(recipe.getTitle());
        description.setText(recipe.getShortDescription());
        recipeItemView.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), RecipeDetailActivity.class);
            intent.putExtra("title", recipe.getTitle());
            intent.putExtra("imageResId", recipe.getImageResId());
            intent.putExtra("description", recipe.getFullDescription());
            startActivity(intent);
        });

        return recipeItemView;
    }
}