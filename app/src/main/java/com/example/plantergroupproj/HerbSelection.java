package com.example.plantergroupproj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HerbSelection extends AppCompatActivity {
    private final String[] herbs = {
            "Basil",
            "Bay",
            "Mint",
            "Oregano",
            "Parsley",
            "Rosemary",
            "Thyme"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herb_selection);

        ImageButton backButton = findViewById(R.id.backBtnBasil);
        backButton.setOnClickListener(v -> {
            finish();
        });

        ListView listView = findViewById(R.id.herbListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_herb,
                R.id.herbName,
                herbs
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedHerb = herbs[position];
            Toast.makeText(this, "You chose: " + selectedHerb, Toast.LENGTH_SHORT).show();
            saveHerbSelection(selectedHerb);

            //user selection
            if (selectedHerb.equals("Basil")) {
                Intent intent = new Intent(this, BasilCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Bay")){
                Intent intent = new Intent(this, BayCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Mint")){
                Intent intent = new Intent(this, MintCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Oregano")){
                Intent intent = new Intent(this, OreganoCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Parsley")){
                Intent intent = new Intent(this, ParsleyCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Rosemary")){
                Intent intent = new Intent(this, RosemaryCard.class);
                startActivity(intent);
            }
            if(selectedHerb.equals("Thyme")){
                Intent intent = new Intent(this, ThymeCard.class);
                startActivity(intent);
            }
        });
    }
    public void saveHerbSelection(String herbName) {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("selectedHerb", herbName);
        editor.putInt("lastWateredDay", 0);
        editor.apply();
    }

}
