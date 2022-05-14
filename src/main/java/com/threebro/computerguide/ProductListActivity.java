package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        LinearLayout set1SpecContainer = findViewById(R.id.set1SpecContainer);
        set1SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout set2SpecContainer = findViewById(R.id.set2SpecContainer);
        set2SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                startActivity(intent);
            }
        });

        ImageView productSet1ImageView = findViewById(R.id.productSet1ImageView);
        ImageView productSet2ImageView = findViewById(R.id.productSet2ImageView);

        Intent budgetIntent = getIntent();
        Bundle budgetBundle = budgetIntent.getBundleExtra("budgetBundle");
        String computerType = budgetBundle.getString("computerType");
        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            productSet1ImageView.setImageResource(R.drawable.desktop);
            productSet2ImageView.setImageResource(R.drawable.desktop);
        }
        else {
            productSet1ImageView.setImageResource(R.drawable.laptop);
            productSet2ImageView.setImageResource(R.drawable.laptop);
        }
    }
}