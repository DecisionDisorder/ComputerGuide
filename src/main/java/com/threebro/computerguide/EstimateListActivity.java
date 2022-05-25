package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.RAM;

import java.text.DecimalFormat;

public class EstimateListActivity extends AppCompatActivity {

    String[] componentsNameArr;
    private PcComponent[] pcComponents;
    private int indexOfSet;
    private TextView priceTextView;

    public enum PcComponentType {
        CPU, COOLER, MB, RAM, VGA, STORAGE, CASE, POWER;
        private static PcComponentType[] allValues = values();
        public static PcComponentType fromOrdinal(int n) {return allValues[n];}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_list);

        Intent rcvintent = getIntent();
        indexOfSet = rcvintent.getIntExtra("index",3);

        DecimalFormat formatter = new DecimalFormat("#,###");
        priceTextView = findViewById(R.id.priceID);
        priceTextView.setText("Total : "+formatter.format(MainActivity.desktopSet.getFinal2().get(indexOfSet).getPrice())+"원");


        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        pcComponents = new PcComponent[PcComponentType.values().length];

        componentsNameArr = getResources().getStringArray(R.array.computer_components);
        TypedArray iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

        for(int i = 0; i < pcComponents.length; i++) {
            if(i == PcComponentType.RAM.ordinal()) {
                pcComponents[i] = new PcComponent(this, MainActivity.desktopSet.getFinal2().get(indexOfSet).getMb().getSlotAmount());
                pcComponents[i].setActiveAmountSet(true, MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount());
            }
            else
                pcComponents[i] = new PcComponent(this);
            pcComponents[i].setTitle(componentsNameArr[i]);
            pcComponents[i].setIcon(iconIdArr.getDrawable(i));
            if(i == PcComponentType.POWER.ordinal() || i == PcComponentType.VGA.ordinal())
                pcComponents[i].setTextSize();
            pcComponents[i].setNameAndPrice(getComponentName(i), formatter.format(getComponentPrice(i)) + "원");
            componentContainer.addView(pcComponents[i]);
        }
    }

    private String getComponentName(int component) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCpu().getName();
            case COOLER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCl().getName();
            case MB:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getMb().getName();
            case RAM:
                RAM ram = MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm();
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getName() + " " + ram.getRamCapacity();
            case VGA:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getGpu().getName();
            case STORAGE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getName();
            case CASE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCa().getName();
            case POWER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getPw().getName();
        }
        return "";
    }

    private int getComponentPrice(int component) {
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
            case STORAGE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt().getPrice();
            case CASE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCa().getPrice();
            case POWER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getPw().getPrice();
        }
        return 0;
    }

    public void setComponentAmount(int component, int amount) {
        if(component == PcComponentType.RAM.ordinal()) {
            int priorAmount = MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount();
            MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().setAmount(priorAmount + amount);
            pcComponents[component].setAmountTextView(MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount());
            updateFinalList(component);
        }
    }

    public int getComponentAmount(int component) {
        if(component == PcComponentType.RAM.ordinal())
            return MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount();
        else
            return 0;
    }

    public void updateFinalList(int updatedComponent) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(updatedComponent == PcComponentType.RAM.ordinal())
            pcComponents[updatedComponent].setNameAndPrice(getComponentName(updatedComponent),
                    formatter.format(getComponentPrice(updatedComponent)) + "원");

        int price = MainActivity.desktopSet.getFinal2().get(indexOfSet).getPrice();
        priceTextView.setText("Total : " + formatter.format(price)+"원");
    }
}