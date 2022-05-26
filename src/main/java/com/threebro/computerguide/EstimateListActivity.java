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
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.FinalTwo;

import java.text.DecimalFormat;

public class EstimateListActivity extends AppCompatActivity {

    private int indexOfSet;
    private PcComponent[] pcComponents;
    private FinalTwo estimate;

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
        String listType = rcvintent.getStringExtra("ListType");
        if(listType.equals("New")) {
            estimate = MainActivity.desktopSet.getFinal2().get(indexOfSet);
            loadEstimateList();
            PastModelListActivity.recommendListManager.addCompareList(estimate, this);
        }
            else if(listType.equals("Past")) {
            estimate = PastModelListActivity.recommendListManager.recommendedSetList.get(indexOfSet).getRecommendedSet();
            loadEstimateList();
        }
    }

    private void loadEstimateList() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        priceTextView = findViewById(R.id.priceID);
        priceTextView.setText("Total : " + formatter.format(estimate.getPrice()) + "원");

        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        pcComponents = new PcComponent[PcComponentType.values().length];

        String[] componentsNameArr = getResources().getStringArray(R.array.computer_components);
        TypedArray iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

        for (int i = 0; i < pcComponents.length; i++) {
            if (i == PcComponentType.RAM.ordinal()) {
                int maxAmount = estimate.getMb().getSlotAmount();
                pcComponents[i] = new PcComponent(this, maxAmount);
                pcComponents[i].setActiveAmountSet(true, getComponentAmount(i));
            } else
                pcComponents[i] = new PcComponent(this);
            pcComponents[i].setTitle(componentsNameArr[i]);
            pcComponents[i].setIcon(iconIdArr.getDrawable(i));

            String name = getComponentName(i, estimate);
            String price = formatter.format(getComponentPrice(i)) + "원";
            pcComponents[i].setNameAndPrice(name, price);
            if (i == PcComponentType.VGA.ordinal() || i == PcComponentType.POWER.ordinal())
                pcComponents[i].setTextSize();
            componentContainer.addView(pcComponents[i]);
        }
    }

    public String getComponentName(int component, FinalTwo estimate) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return estimate.getCpu().getName();
            case COOLER:
                return estimate.getCl().getName();
            case MB:
                return estimate.getMb().getName();
            case RAM:
                RAM ram = estimate.getRm();
                return ram.getName() + " " + ram.getRamCapacity();
            case VGA:
                return estimate.getGpu().getName();
            case STORAGE:
                Storage storage = estimate.getSt();
                return storage.getName() + " " + storage.getCapacity();
            case CASE:
                return estimate.getCa().getName();
            case POWER:
                return estimate.getPw().getName();
        }
        return "";
    }

    public int getComponentPrice(int component) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return estimate.getCpu().getPrice();
            case COOLER:
                return estimate.getCl().getPrice();
            case MB:
                return estimate.getMb().getPrice();
            case RAM:
                return estimate.getRm().getPrice();
            case VGA:
                return estimate.getGpu().getPrice();
            case STORAGE:
                return estimate.getSt().getPrice();
            case CASE:
                return estimate.getCa().getPrice();
            case POWER:
                return estimate.getPw().getPrice();
        }
        return 0;
    }

    public void setComponentAmount(int component, int amount) {
        if(component == PcComponentType.RAM.ordinal()) {
            int priorAmount = getComponentAmount(component);
            estimate.getRm().setAmount(priorAmount + amount);
            pcComponents[component].setAmountTextView(getComponentAmount(component));
            updatePrice(component);
        }
    }

    public int getComponentAmount(int component) {
        if(component == PcComponentType.RAM.ordinal()) {
            return estimate.getRm().getAmount();
        }

        return 0;
    }

    private void updatePrice(int changedComponent) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(changedComponent == PcComponentType.RAM.ordinal()) {
            String price = formatter.format(getComponentPrice(changedComponent)) + "원";
            pcComponents[changedComponent].setNameAndPrice(getComponentName(changedComponent, estimate), price);
        }

        priceTextView.setText("Total : "+formatter.format(estimate.getPrice()) + "원");
    }
}