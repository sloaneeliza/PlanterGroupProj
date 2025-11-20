package com.example.plantergroupproj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class PlanterFragment extends Fragment {
    private LinearLayout planterList;


    private final Map<String, Integer> herbPics = new HashMap<String, Integer>() {{
        put("Basil", R.drawable.basil);
        put("Bay", R.drawable.bay);
        put("Mint", R.drawable.mint);
        put("Rosemary", R.drawable.rosemary);
        put("Thyme", R.drawable.thyme);
        put("Oregano", R.drawable.oregano);
        put("Parsley", R.drawable.parsley);
    }};

    private final Map<String, Integer> waterTimes = new HashMap<String, Integer>() {{
        put("Basil", 1);
        put("Bay", 7);
        put("Mint", 5);
        put("Rosemary", 4);
        put("Thyme", 5);
        put("Oregano", 4);
        put("Parsley", 3);
    }};

    private final Map<String, Integer> harvestTimes = new HashMap<String, Integer>() {{
        put("Basil", 3);
        put("Bay", 8);
        put("Mint", 6);
        put("Rosemary", 12);
        put("Thyme", 21);
        put("Oregano", 14);
        put("Parsley", 20);
    }};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_planter, container, false);
        planterList = view.findViewById(R.id.planterList);
        loadSavedHerbs(inflater);

        LinearLayout savedRecipesCard = view.findViewById(R.id.savedRecipesCard);

        savedRecipesCard.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SavedRecipesActivity.class);
            startActivity(intent);
        });


        LinearLayout addHerbCard = view.findViewById(R.id.addHerbCard);
        addHerbCard.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), HerbSelection.class);
            startActivity(intent);
        });

        return view;
    }

    private void loadSavedHerbs(LayoutInflater inflater) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("MyHerbs", Context.MODE_PRIVATE);
        Set<String> savedHerbs = prefs.getStringSet("savedHerbs", new HashSet<>());

        planterList.removeAllViews();

        for (String herb : savedHerbs) {
            View card = inflater.inflate(R.layout.item_planter_card, planterList, false);

            TextView herbName = card.findViewById(R.id.herbName);
            TextView waterDays = card.findViewById(R.id.waterDays);
            TextView harvestDays = card.findViewById(R.id.harvestDays);
            ImageView herbImage = card.findViewById(R.id.herbImage);

            herbName.setText(herb);
            herbImage.setImageResource(herbPics.get(herb));
            waterDays.setText(String.valueOf(waterTimes.get(herb)));
            harvestDays.setText(String.valueOf(harvestTimes.get(herb)));
            herbImage.setImageResource(herbPics.get(herb));

            card.findViewById(R.id.moreButton).setOnClickListener(v -> {
                Intent i = new Intent(requireContext(), BasilCard.class);
                i.putExtra("herbName", herb);
                startActivity(i);
            });

            planterList.addView(card);
        }
    }
}
