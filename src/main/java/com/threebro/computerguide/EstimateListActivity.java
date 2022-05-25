package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.CPU;

import java.text.DecimalFormat;

public class EstimateListActivity extends AppCompatActivity {

    private enum PcComponentType {
        CPU, COOLER, MB, RAM, VGA, SSD, HDD, CASE, POWER;
        private static PcComponentType[] allValues = values();
        public static PcComponentType fromOrdinal(int n) {return allValues[n];}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_list);

        Intent rcvintent = getIntent();
        int index = rcvintent.getIntExtra("index",3);

        DecimalFormat formatter = new DecimalFormat("#,###");
        TextView price = findViewById(R.id.priceID);
        price.setText("Total : "+formatter.format(MainActivity.desktopSet.getFinal2().get(index).getPrice())+"원");


        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        PcComponent[] pcComponents = new PcComponent[PcComponentType.values().length];

        String[] componentsNameArr = getResources().getStringArray(R.array.computer_components);
        TypedArray iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

        for(int i = 0; i < pcComponents.length; i++) {
            pcComponents[i] = new PcComponent(this);
            pcComponents[i].setTitle(componentsNameArr[i]);
            pcComponents[i].setIcon(iconIdArr.getDrawable(i));
            if(componentsNameArr[i].equals("Power Supply")||componentsNameArr[i].equals("Graphic Card")){
                pcComponents[i].setTextSize();
            }
            pcComponents[i].setNameAndPrice(getComponentName(i, index), formatter.format(getComponentPrice(i, index)) + "원");
            componentContainer.addView(pcComponents[i]);
        }
    }

    private String getComponentName(int component, int indexOfSet) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCpu().getName();
            case COOLER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCl().getName();
            case MB:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getMb().getName();
            case RAM:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getName()+"  "+MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getRamCapacity()+"X"+MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount();
            case VGA:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getGpu().getName();
            case SSD:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getName();
            case HDD:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getName();
            case CASE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCa().getName();
            case POWER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getPw().getName();
        }
        return "";
    }

    private int getComponentPrice(int component, int indexOfSet) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCpu().getPrice();
            case COOLER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCl().getPrice();
            case MB:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getMb().getPrice();
            case RAM:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getPrice();
            case VGA:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getGpu().getPrice();
            case SSD:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getPrice();
            case HDD:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getPrice();
            case CASE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCa().getPrice();
            case POWER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getPw().getPrice();
        }
        return 0;
    }
}