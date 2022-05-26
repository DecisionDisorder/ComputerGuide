package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        TextView spec1 = findViewById(R.id.set1SpecTextView) ;

        LinearLayout set1SpecContainer = findViewById(R.id.set1SpecContainer);

        TextView spec2 = findViewById(R.id.set2SpecTextView) ;

        set1SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                intent.putExtra("ListType", "New");
                intent.putExtra("index",0);
                startActivity(intent);
            }
        });


        LinearLayout set2SpecContainer = findViewById(R.id.set2SpecContainer);
        set2SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                intent.putExtra("ListType", "New");
                intent.putExtra("index",1);
                startActivity(intent);
            }
        });

        //이준희 코드
        Intent budgetIntent1 = getIntent();
        Bundle laptopBundle = budgetIntent1.getBundleExtra("budgetBundle");
        String usageType = laptopBundle.getString("usageType");
        String detailedUsageType = laptopBundle.getString("detailedUsageType");
        int laptopSizeIndex = laptopBundle.getInt("laptopSizeIndex");
        int laptopWeightIndex = laptopBundle.getInt("laptopWeightIndex");
        int brandType = laptopBundle.getInt("brandType");
        int budgetindex = laptopBundle.getInt("budgetType");
        brandType += 1;
        float wt = (float) (1.5 + 0.5 * laptopWeightIndex);
        float dsize = 13 + 1 * laptopSizeIndex;
        int price = 500000 + 100000 * budgetindex;
        MainActivity.laptopSet.Selectlaptop(usageType, detailedUsageType, dsize, brandType, wt, price);

        ImageView productSet1ImageView = findViewById(R.id.productSet1ImageView);
        ImageView productSet2ImageView = findViewById(R.id.productSet2ImageView);

        Intent budgetIntent = getIntent();
        Bundle budgetBundle = budgetIntent.getBundleExtra("budgetBundle");
        spec1.setText(MainActivity.desktopSet.getSimpleString(0));
        spec2.setText(MainActivity.desktopSet.getSimpleString(1));
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