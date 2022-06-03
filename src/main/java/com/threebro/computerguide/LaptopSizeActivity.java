package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaptopSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_size);

        // Set screen size button
        Button[] sizeButtons = new Button[5];
        sizeButtons[0] = findViewById(R.id.size13Button);
        sizeButtons[1] = findViewById(R.id.size14Button);
        sizeButtons[2] = findViewById(R.id.size15Button);
        sizeButtons[3] = findViewById(R.id.size16Button);
        sizeButtons[4] = findViewById(R.id.size17Button);

        // Set screen size button listener that starts next activity with prior activity's bundle and size data
        for(int i = 0; i < sizeButtons.length; i++)
        {
            final int sizeIndex = i;
            sizeButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = getIntent().getBundleExtra("usageBundle");
                    bundle.putInt("laptopSizeIndex", sizeIndex);
                    Intent weightActivity = new Intent(getApplicationContext(), LaptopWeightActivity.class);
                    weightActivity.putExtra("sizeBundle", bundle);
                    startActivity(weightActivity);
                }
            });
        }
    }
}