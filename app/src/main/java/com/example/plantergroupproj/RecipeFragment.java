package com.example.plantergroupproj;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {

    private static final String ARG_HERB = "herb";
    FirebaseDatabase database;

    // database

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
       // FirebaseApp.initializeApp(requireContext());




        // path for recipes with all the info as children
        // im pushing into here

        // switch all the current switch cases into new recipes and then add them
        // can i make it in the database i have basil and then in there a recipe how do i make a new thing maybe i make another constructor in recipe class
        // so i can have the plant tag and then sort them by plant tag - or maybe number even
        List<Recipe> recipes = new ArrayList<>();

        // recipes is arraylist so go through and add to database



        switch (herb) {
            case "Basil":
                recipes.add(new Recipe("Caprese Salad",
                        "Fresh tomatoes, mozzarella, and basil drizzled with olive oil.",
                        "Slice tomatoes and mozzarella. Layer with basil. Drizzle olive oil. Season.",
                        R.drawable.caprese, "Basil"));
                recipes.add(new Recipe("Walnut Pesto",
                        "Serve on pasta or use as dressing.",
                        "Blend basil, walnuts, parmesan, garlic, olive oil. Season.",
                        R.drawable.walnutpesto, "Basil"));
                recipes.add(new Recipe("Basil Lemonade",
                        "Refreshing lemonade with basil.",
                        "Make basil syrup. Add lemon juice and water. Serve cold.",
                        R.drawable.lemonade, "Basil"));
                break;

            case "Bay":
                recipes.add(new Recipe("Classic Beef Stew",
                        "Hearty stew with bay leaves.",
                        "Brown beef. Add veggies, broth, bay leaves. Simmer.",
                        R.drawable.beef_stew, "Bay"));
                recipes.add(new Recipe("Roasted Chicken with Bay",
                        "Juicy chicken with lemon & bay leaves.",
                        "Stuff chicken with bay & lemon. Roast until done.",
                        R.drawable.basicleaf, "Bay"));
                recipes.add(new Recipe("Bay Leaf Pound Cake",
                        "Moist fragrant pound cake.",
                        "Mix batter with bay flavor. Bake until golden.",
                        R.drawable.basicleaf, "Bay"));
                break;

            case "Mint":
                recipes.add(new Recipe("Mint Yogurt Dip",
                        "Creamy dip flavored with mint.",
                        "Mix yogurt, mint, garlic, lemon. Chill.",
                        R.drawable.basicleaf, "Mint"));
                recipes.add(new Recipe("Mint Lemon Tea",
                        "Refreshing mint tea.",
                        "Steep mint in hot water. Add lemon.",
                        R.drawable.basicleaf, "Mint"));
                recipes.add(new Recipe("Chocolate Mint Smoothie",
                        "Cool smoothie with chocolate and mint.",
                        "Blend milk, chocolate, mint leaves, ice.",
                        R.drawable.basicleaf, "Mint"));
                break;

            case "Oregano":
                recipes.add(new Recipe("Oregano Tomato Pasta",
                        "Quick pasta with oregano tomato sauce.",
                        "Cook pasta. Toss with tomato sauce and oregano.",
                        R.drawable.basicleaf, "Oregano"));
                recipes.add(new Recipe("Grilled Oregano Chicken",
                        "Juicy chicken with oregano flavor.",
                        "Marinate chicken with oregano. Grill.",
                        R.drawable.basicleaf, "Oregano"));
                recipes.add(new Recipe("Oregano Roasted Veggies",
                        "Simple roasted veggies with oregano.",
                        "Toss veggies with oregano & oil. Roast.",
                        R.drawable.basicleaf, "Oregano"));
                break;

            case "Parsley":
                recipes.add(new Recipe("Parsley Lemon Potatoes",
                        "Roasted potatoes with parsley.",
                        "Toss potatoes with parsley & oil. Roast.",
                        R.drawable.basicleaf, "Parsley"));
                recipes.add(new Recipe("Garlic Parsley Shrimp",
                        "Quick shrimp sauté with parsley.",
                        "Sauté shrimp with garlic & parsley.",
                        R.drawable.basicleaf, "Parsley"));
                recipes.add(new Recipe("Parsley Salad",
                        "Fresh parsley salad with lemon.",
                        "Mix parsley, lemon juice, olive oil. Serve.",
                        R.drawable.basicleaf, "Parsley"));
                break;

            case "Thyme":
                recipes.add(new Recipe("Thyme Roasted Chicken",
                        "Juicy chicken with thyme.",
                        "Season chicken with thyme. Roast.",
                        R.drawable.basicleaf, "Thyme"));
                recipes.add(new Recipe("Lemon Thyme Fish",
                        "Light fish with lemon and thyme.",
                        "Season fish with thyme & lemon. Bake.",
                        R.drawable.basicleaf, "Thyme"));
                recipes.add(new Recipe("Thyme Mashed Potatoes",
                        "Creamy mashed potatoes with thyme.",
                        "Mash potatoes with thyme and butter.",
                        R.drawable.basicleaf, "Thyme"));
                break;

            case "Rosemary":
                recipes.add(new Recipe("Rosemary Garlic Bread",
                        "Savory bread with rosemary.",
                        "Mix dough with rosemary & garlic. Bake.",
                        R.drawable.basicleaf, "Rosemary"));
                recipes.add(new Recipe("Rosemary Roast Lamb",
                        "Tender lamb with rosemary flavor.",
                        "Season lamb with rosemary. Roast until done.",
                        R.drawable.basicleaf, "Rosemary"));
                recipes.add(new Recipe("Rosemary Potato Wedges",
                        "Crispy potato wedges with rosemary.",
                        "Toss potato wedges with rosemary & oil. Bake.",
                        R.drawable.basicleaf, "Rosemary"));
                break;

        } // end switch cases


       // Log.i("LAURENRECIPIES", recipes.toString());


       // recipesRef.push().setValue(BasilLemonade);
        //recipesRef.push().setValue(CapreseSalad);

        database= FirebaseDatabase.getInstance();

        DatabaseReference recipesRef = FirebaseDatabase.getInstance().getReference("recipes");

//        recipesRef.limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot current : snapshot.getChildren()){
//                    Recipe r = current.getValue(Recipe.class);
//                    Log.i("LAUREN", r.getShortDescription());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.i("LAURENE", error.getDetails());
//            }
//        });


        for (Recipe recipe : recipes) {
            DatabaseReference plantRef = recipesRef.child(recipes.get(0).getPlantName());

            //Log.i("LAUREN", recipe.getPlantName());

            // now we can use recipe.getPlantName() here
            String nodeId = plantRef.push().getKey();
           // Log.i("LAUREN", nodeId);
            plantRef.child(nodeId).setValue(recipe).addOnCompleteListener(task -> {
                Log.i("LAUREN", task.toString());
                if (task.isSuccessful()) {
                    Log.i("LAURENRECIPE", "Uploaded: " + recipe.getTitle());
                } else {
                    Log.e("LAURENRECIPEFAIL", "Failed to upload", task.getException());
                }
            });
        } // end for loop





        //add each recipe to the container
        for (Recipe recipe : recipes) {
            View recipeItemView = createRecipeItemView(inflater, recipesContainer, recipe);
            recipesContainer.addView(recipeItemView);
        }
        return view;
        // like here ill put that thing
    }

    private View createRecipeItemView(LayoutInflater inflater, ViewGroup parent, Recipe recipe) {
        View recipeItemView = inflater.inflate(R.layout.item_recipe, parent, false);
        TextView title = recipeItemView.findViewById(R.id.recipeTitle);
        TextView description = recipeItemView.findViewById(R.id.recipeDesc);
        ImageView image = recipeItemView.findViewById(R.id.recipeImage);

        title.setText(recipe.getTitle());
        description.setText(recipe.getShortDescription());

        if (recipe.getImageResId() != 0) {
            image.setImageResource(recipe.getImageResId());
        }

        recipeItemView.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), RecipeDetailActivity.class);
            intent.putExtra("title", recipe.getTitle());
            intent.putExtra("imageResId", recipe.getImageResId());
            intent.putExtra("shortDesc", recipe.getShortDescription());
            intent.putExtra("description", recipe.getFullDescription());
            intent.putExtra("plantName", recipe.getPlantName());
            startActivity(intent);
        });

        // giving the recipes to recipe detail activity from this fragment where they are created
        // so ill create them on initial and put them in the database and then ill just update the recipe detail activity
        // to query the database for the recipes
        return recipeItemView;
    }
}