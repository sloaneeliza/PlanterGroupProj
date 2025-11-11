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
                recipes.add(new Recipe(
                        "Basil Lemonade",
                        "A refreshing twist on classic lemonade with the aromatic flavor of fresh basil.",
                        "Ingredients:\n" +
                                "- 1 cup fresh basil leaves\n" +
                                "- 1 cup freshly squeezed lemon juice (about 4-6 lemons)\n" +
                                "- ¾ cup sugar or honey\n" +
                                "- 4 cups cold water\n" +
                                "- Ice cubes\n" +
                                "- Lemon slices and basil sprigs for garnish\n\n" +
                                "Instructions:\n" +
                                "1. In a small saucepan, combine 1 cup water, sugar, and basil leaves.\n" +
                                "2. Heat over medium, stirring until sugar dissolves (about 5 minutes).\n" +
                                "3. Remove from heat and let the basil steep for 15-20 minutes.\n" +
                                "4. Strain the basil syrup into a pitcher, pressing on the leaves to extract flavor.\n" +
                                "5. Add lemon juice and remaining 3 cups cold water.\n" +
                                "6. Stir well and refrigerate until chilled.\n" +
                                "7. Serve over ice, garnished with lemon slices and fresh basil.",
                        R.drawable.lemonade
                ));
                break;
            case "Bay":
                recipes.add(new Recipe(
                        "Classic Beef Stew",
                        "A hearty, comforting stew where bay leaves provide subtle aromatic depth.",
                        "Ingredients:\n" +
                                "- 2 lbs beef chuck, cubed\n" +
                                "- 2 tbsp olive oil\n" +
                                "- 1 large onion, chopped\n" +
                                "- 3 carrots, sliced\n" +
                                "- 3 celery stalks, sliced\n" +
                                "- 3 cloves garlic, minced\n" +
                                "- 4 cups beef broth\n" +
                                "- 1 cup red wine (optional)\n" +
                                "- 2 bay leaves\n" +
                                "- 1 tsp thyme\n" +
                                "- 2 potatoes, cubed\n" +
                                "- Salt and pepper to taste\n" +
                                "- 2 tbsp tomato paste\n\n" +
                                "Instructions:\n" +
                                "1. Season beef with salt and pepper. Heat oil in large pot and brown beef in batches.\n" +
                                "2. Add onions, carrots, and celery. Cook until softened (5-7 minutes).\n" +
                                "3. Add garlic and tomato paste, cook for 1 minute until fragrant.\n" +
                                "4. Return beef to pot, add broth, wine, bay leaves, and thyme.\n" +
                                "5. Bring to boil, then reduce heat and simmer covered for 1.5 hours.\n" +
                                "6. Add potatoes and cook for 30 more minutes until tender.\n" +
                                "7. Remove bay leaves before serving.\n" +
                                "8. Season with additional salt and pepper if needed.\n\n" +
                                "Tips:\n" +
                                "- Bay leaves are essential for depth of flavor - don't skip them!\n" +
                                "- Remember to remove bay leaves before serving as they can be sharp\n" +
                                "- Stew tastes even better the next day as flavors meld",
                        R.drawable.beef_stew
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