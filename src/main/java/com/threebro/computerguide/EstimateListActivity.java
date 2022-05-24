package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EstimateListActivity extends AppCompatActivity {

    private enum PcComponentType {CPU, COOLER, MB, RAM, VGA, SSD, HDD, CASE, POWER}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_list);

        Intent rcvintent = getIntent();
        int index = rcvintent.getIntExtra("index",3);

        TextView price = findViewById(R.id.priceID);
        price.setText("Total : "+MainActivity.desktopSet.getFinal2().get(index).getPrice()+"원");


        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        PcComponent[] pcComponents = new PcComponent[PcComponentType.values().length];

        String[] componentsNameArr = getResources().getStringArray(R.array.computer_components);
        TypedArray iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

        for(int i = 0; i < pcComponents.length; i++) {
            pcComponents[i] = new PcComponent(this);
            pcComponents[i].setTitle(componentsNameArr[i]);
            pcComponents[i].setIcon(iconIdArr.getDrawable(i));
            componentContainer.addView(pcComponents[i]);
        }
    }
}