package com.example.plantergroupproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plantergroupproj.HerbSelection;
import com.example.plantergroupproj.NameActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //If you want to start fresh
        // with no shared preferences:
        // Android Studio → Device Manager → ⋮ → Wipe Data
        String name = UserPrefs.getName(this);

        if (name != null && !name.isEmpty()) {
            //user already set up, so go straight to Home
            Intent intent = new Intent(WelcomeActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
            return;
        } else{
            setContentView(R.layout.activity_welcome);
        }

        getStartedBtn = findViewById(R.id.getStartedBtn);


        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, NameActivity.class));
            }
        });
    }
}
