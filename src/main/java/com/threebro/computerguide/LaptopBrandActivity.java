package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaptopBrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_brand);

        Button majorBrandButton = findViewById(R.id.majorBrandButton);
        majorBrandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetActivity(1);
            }
        });
        Button smeBrandButton = findViewById(R.id.smeBrandButton);
        smeBrandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetActivity(0);
            }
        });
    }

    private void openBudgetActivity(int brandType)
    {
        Intent intent = new Intent(getApplicationContext(), BudgetSelectionActivity.class);
        Bundle bundle = getIntent().getBundleExtra("weightBundle");
        bundle.putInt("brandType", brandType);
        intent.putExtra("brandBundle", bundle);
        startActivity(intent);
    }
}