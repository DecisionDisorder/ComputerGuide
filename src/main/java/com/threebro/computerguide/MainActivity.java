package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button desktopButton = findViewById(R.id.desktopBtn);
        desktopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desktopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                desktopIntent.putExtra("computerType", ComputerType.DESKTOP.toString());
                startActivity(desktopIntent);
            }
        });

        Button laptopButton = findViewById(R.id.laptopBtn);
        laptopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent laptopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                laptopIntent.putExtra("computerType", ComputerType.LAPTOP.toString());
                startActivity(laptopIntent);
            }
        });
    }
}