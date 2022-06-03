package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UsageSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_selection);

        // Get computer type from prior activity
        Intent rcvIntent = getIntent();
        String type = rcvIntent.getStringExtra("computerType");

        // Set usage:game button listener
        Button gameButton = findViewById(R.id.gameBtn);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "game");
            }
        });

        // Set usage:professional button listener
        Button proWorkButton = findViewById(R.id.professionBtn);
        proWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "professional");
            }
        });

        // Set usage:simple_work button listener
        Button simpleWorkButton = findViewById(R.id.simpleWorkBtn);
        simpleWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "simple_work");
            }
        });
    }

    // Start popup detailed usage activity
    private void openDetailedPopup(String computerType, String detailedType) {
        Intent detailedUsagePopup = new Intent(this, DetailedUsageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", detailedType);
        bundle.putString("computerType", computerType);
        detailedUsagePopup.putExtra("typeBundle", bundle);
        startActivity(detailedUsagePopup);
    }
}