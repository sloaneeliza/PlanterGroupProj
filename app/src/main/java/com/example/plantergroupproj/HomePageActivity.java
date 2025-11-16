package com.example.plantergroupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HomePageActivity extends AppCompatActivity {

    TextView userNameText;
    Button selectHerbButton;

    // herb images
    private final Map<String, Integer> herbImages = new HashMap<String, Integer>() {{
        put("Basil", R.drawable.basil);
        put("Bay", R.drawable.bay);
        put("Mint", R.drawable.mint);
        put("Rosemary", R.drawable.rosemary);
        put("Thyme", R.drawable.thyme);
        put("Oregano", R.drawable.oregano);
        put("Parsley", R.drawable.parsley);
    }};

    // watering intervals
    private final Map<String, Integer> waterTimes = new HashMap<String, Integer>() {{
        put("Basil", 1);
        put("Bay", 7);
        put("Mint", 5);
        put("Rosemary", 4);
        put("Thyme", 5);
        put("Oregano", 4);
        put("Parsley", 3);
    }};

    private int calculateDaysSince(long lastWateredTime) {
        long now = System.currentTimeMillis();
        long diff = now - lastWateredTime;
        return (int)(diff / (1000 * 60 * 60 * 24));
    }
    private String getHerbThatNeedsWateringSoonest(Set<String> herbs, SharedPreferences prefs) {
        String bestHerb = null;
        int bestDays = Integer.MAX_VALUE;

        for (String herb : herbs) {
            int interval = waterTimes.get(herb);

            long lastWatered = prefs.getLong("lastWatered_" + herb, System.currentTimeMillis());
            int daysSince = calculateDaysSince(lastWatered);
            int daysUntil = Math.max(0, interval - daysSince);

            if (daysUntil < bestDays) {
                bestDays = daysUntil;
                bestHerb = herb;
            }
        }
        return bestHerb;
    }

    private void buildCalendarStrip(int daysUntil) {
        LinearLayout dotRow = findViewById(R.id.calendarDots);
        LinearLayout numRow = findViewById(R.id.calendarNumbers);
        dotRow.removeAllViews();
        numRow.removeAllViews();

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int today = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        int waterDay = today + daysUntil;

        int maxDay = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        if (waterDay > maxDay) waterDay -= maxDay;

        for (int i = 0; i < 7; i++) {
            int dayNumber = today + i;

            if (dayNumber > maxDay) dayNumber -= maxDay;

            ImageView dot = new ImageView(this);
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(55, 55);
            dotParams.setMargins(10, 0, 10, 0);
            dot.setLayoutParams(dotParams);

            // 🔥 BLACK CIRCLE only on the watering day
            if (dayNumber == waterDay) {
                dot.setImageResource(R.drawable.black_circle);
            } else {
                dot.setImageResource(R.drawable.white_circle);
            }

            dotRow.addView(dot);

            TextView num = new TextView(this);
            num.setText(String.valueOf(dayNumber));
            num.setTextSize(14);
            num.setTextColor(Color.BLACK);

            LinearLayout.LayoutParams numParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            numParams.setMargins(16, 0, 16, 0);
            num.setLayoutParams(numParams);

            numRow.addView(num);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userNameText = findViewById(R.id.userNameText);
        selectHerbButton = findViewById(R.id.herbSelectBtn);

        String name = UserPrefs.getName(this);
        if (name != null) {
            userNameText.setText(name + ".");
        }
        selectHerbButton.setOnClickListener(v -> {
            startActivity(new Intent(this, HerbSelection.class));
        });
        LinearLayout firstHerbSection = findViewById(R.id.firstHerbSection);
        LinearLayout wateringPanel = findViewById(R.id.wateringSection);

        SharedPreferences herbPrefs = getSharedPreferences("MyHerbs", MODE_PRIVATE);
        Set<String> savedHerbs = herbPrefs.getStringSet("savedHerbs", new HashSet<>());

        if (savedHerbs.isEmpty()) {
            firstHerbSection.setVisibility(View.VISIBLE);
            wateringPanel.setVisibility(View.GONE);
            return;
        }
        firstHerbSection.setVisibility(View.GONE);
        wateringPanel.setVisibility(View.VISIBLE);

        String herb = getHerbThatNeedsWateringSoonest(savedHerbs, herbPrefs);

        ImageView leaf = findViewById(R.id.waterLeafImage);
        leaf.setImageResource(herbImages.get(herb));

        int interval = waterTimes.get(herb);
        long lastWatered = herbPrefs.getLong("lastWatered_" + herb, System.currentTimeMillis());
        int daysSince = calculateDaysSince(lastWatered);
        int daysUntil = Math.max(0, interval - daysSince);

        TextView wateringMessage = findViewById(R.id.waterMessage);
        if (daysUntil == 0) {
            wateringMessage.setText("Water your " + herb.toLowerCase() + " today!");
        } else {
            wateringMessage.setText("Water your " + herb.toLowerCase() + " in " + daysUntil + " days.");
        }

        TextView nextWateringText = findViewById(R.id.waterNextText);
        nextWateringText.setText(daysUntil + " days until it's time to water your " + herb.toLowerCase() + ".");
        buildCalendarStrip(daysUntil);
    }
}
