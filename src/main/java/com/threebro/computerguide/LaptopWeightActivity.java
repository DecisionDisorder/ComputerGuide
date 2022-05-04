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

        Button[] weightButtons = new Button[4];
        weightButtons[0] = findViewById(R.id.weightButton0);
        weightButtons[1] = findViewById(R.id.weightButton1);
        weightButtons[2] = findViewById(R.id.weightButton2);
        weightButtons[3] = findViewById(R.id.weightButton3);

        for(int i = 0; i < weightButtons.length; i++)
        {
            final int weightIndex = i;
            weightButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int weight = weightIndex; // TODO: 그냥 sizeIndex를 써도 될지 확인해보기
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