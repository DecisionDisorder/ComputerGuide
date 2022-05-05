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

        Intent rcvIntent = getIntent();
        String type = rcvIntent.getStringExtra("computerType");

        Button gameButton = findViewById(R.id.gameBtn);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "game");
            }
        });

        Button proWorkButton = findViewById(R.id.professionBtn);
        proWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "professional");
            }
        });

        Button simpleWorkButton = findViewById(R.id.simpleWorkBtn);
        simpleWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailedPopup(type, "simple_work");
            }
        });
    }

    private void openDetailedPopup(String computerType, String detailedType) {
        Intent gamePopup = new Intent(this, DetailedUsageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", detailedType);
        bundle.putString("computerType", computerType);
        gamePopup.putExtra("typeBundle", bundle);
        startActivity(gamePopup);
    }
}