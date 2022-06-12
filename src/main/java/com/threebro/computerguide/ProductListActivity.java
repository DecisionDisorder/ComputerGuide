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

        // Get UI views from activity xml
        TextView spec1 = findViewById(R.id.set1SpecTextView);
        LinearLayout set1SpecContainer = findViewById(R.id.set1SpecContainer);
        TextView spec2 = findViewById(R.id.set2SpecTextView);
        ImageView productSet1ImageView = findViewById(R.id.productSet1ImageView);
        ImageView productSet2ImageView = findViewById(R.id.productSet2ImageView);

        // Set set 1 click listener
        set1SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                Bundle productBundle = getIntent().getBundleExtra("budgetBundle");
                intent.putExtra("ListType", "New");
                intent.putExtra("index",0);
                intent.putExtra("productBundle", productBundle);
                startActivity(intent);
            }
        });

        // Set set 2 click listener
        LinearLayout set2SpecContainer = findViewById(R.id.set2SpecContainer);
        set2SpecContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EstimateListActivity.class);
                Bundle productBundle = getIntent().getBundleExtra("budgetBundle");
                intent.putExtra("ListType", "New");
                intent.putExtra("index",1);
                intent.putExtra("productBundle", productBundle);
                startActivity(intent);
            }
        });

        // Get intent from prior activity and unpack the bundle data
        Intent budgetIntent = getIntent();
        Bundle budgetBundle = budgetIntent.getBundleExtra("budgetBundle");
        String computerType = budgetBundle.getString("computerType");
        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            // If it is a desktop, set icons and simple specifications of desktop
            productSet1ImageView.setImageResource(R.drawable.desktop);
            productSet2ImageView.setImageResource(R.drawable.desktop);
            spec1.setText(DesktopSet.getSimpleString(MainActivity.desktopSet.getFinal2().get(0)));
            spec2.setText(DesktopSet.getSimpleString(MainActivity.desktopSet.getFinal2().get(1)));
            set2SpecContainer.setEnabled(true);
        }
        else {
            // If it is a laptop, set icons and simple specifications of laptop
            productSet1ImageView.setImageResource(R.drawable.laptop);
            productSet2ImageView.setImageResource(R.drawable.laptop);
            // Get additional data for laptops.
            Intent budgetIntent1 = getIntent();
            Bundle laptopBundle = budgetIntent1.getBundleExtra("budgetBundle");
            String usageType = laptopBundle.getString("usageType");
            String detailedUsageType = laptopBundle.getString("detailedUsageType");
            int laptopSizeIndex = laptopBundle.getInt("laptopSizeIndex");
            int laptopWeightIndex = laptopBundle.getInt("laptopWeightIndex");
            int brandType = laptopBundle.getInt("brandType");
            int budgetIndex = laptopBundle.getInt("budgetType");

            // Some data is converted to a processable form.
            // ex) weight and display size data is index
            // convert to real data
            brandType += 1;
            float wt = (float) (1.5 + 0.5 * laptopWeightIndex);
            float dsize = 13 + 1 * laptopSizeIndex;
            int price = 500000 + 100000 * budgetIndex;
            // Select the laptop according to the conditions.
            MainActivity.laptopSet.SelectLaptop(usageType, detailedUsageType, dsize, brandType, wt, price);

            // If there is only one laptop available, disable the second set.
            if(MainActivity.laptopSet.getFlaptop().size()==1){
                spec1.setText(LaptopSet.getSimpleString(MainActivity.laptopSet.getFlaptop().get(0)));
                spec2.setText("No more result");
                set2SpecContainer.setEnabled(false);
            }
            // Otherwise, set up two sets.
            else {
                spec1.setText(LaptopSet.getSimpleString(MainActivity.laptopSet.getFlaptop().get(0)));
                spec2.setText(LaptopSet.getSimpleString(MainActivity.laptopSet.getFlaptop().get(1)));
                set2SpecContainer.setEnabled(true);
            }
        }
    }
}