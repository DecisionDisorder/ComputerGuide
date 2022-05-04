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
                openGamePopup(type);
            }
        });
    }

    private void openGamePopup(String computerType)
    {
        String[] gameList = getResources().getStringArray(R.array.game_category);
        Intent gamePopup = new Intent(this, DetailedUsageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "game");
        bundle.putString("computerType", computerType);
        bundle.putStringArray("gameList", gameList);
        gamePopup.putExtra("typeBundle", bundle);
        startActivity(gamePopup);
    }

}