package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaptopWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_weight);

        // Set weight button
        Button[] weightButtons = new Button[4];
        weightButtons[0] = findViewById(R.id.weightButton0);
        weightButtons[1] = findViewById(R.id.weightButton1);
        weightButtons[2] = findViewById(R.id.weightButton2);
        weightButtons[3] = findViewById(R.id.weightButton3);

        // Set screen weight listener that starts next activity with prior activity's bundle and weight data
        for(int i = 0; i < weightButtons.length; i++)
        {
            final int weightIndex = i;
            weightButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = getIntent().getBundleExtra("sizeBundle");
                    bundle.putInt("laptopWeightIndex", weightIndex);
                    Intent brandActivity = new Intent(getApplicationContext(), LaptopBrandActivity.class);
                    brandActivity.putExtra("weightBundle", bundle);
                    startActivity(brandActivity);
                }
            });
        }
    }
}