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
            FinalTwo estimate = MainActivity.desktopSet.getFinal2().get(indexOfSet);
            loadEstimateList(estimate);
            PastModelListActivity.recommendListManager.addCompareList(estimate);
        }
            else if(listType.equals("Past")) {
            FinalTwo estimate = PastModelListActivity.recommendListManager.recommendedSetList.get(indexOfSet).getRecommendedSet();
            loadEstimateList(estimate);
        }
    }

    private void loadEstimateList(FinalTwo estimate) {
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

            pcComponents[i].setNameAndPrice(getComponentName(i), formatter.format(getComponentPrice(i)) + "원");
            if (i == PcComponentType.VGA.ordinal() || i == PcComponentType.POWER.ordinal())
                pcComponents[i].setTextSize();
            componentContainer.addView(pcComponents[i]);
        }
    }

    public String getComponentName(int component) {
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
                return ram.getName() + " " + ram.getRamCapacity();
            case VGA:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getGpu().getName();
            case STORAGE:
                Storage storage = MainActivity.desktopSet.getFinal2().get(indexOfSet).getSt();
                return storage.getName() + " " + storage.getCapacity();
            case CASE:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getCa().getName();
            case POWER:
                return MainActivity.desktopSet.getFinal2().get(indexOfSet).getPw().getName();
        }
        return "";
    }

    public int getComponentPrice(int component) {
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
            int priorAmount = getComponentAmount(component);
            MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().setAmount(priorAmount + amount);
            pcComponents[component].setAmountTextView(getComponentAmount(component));
            updatePrice(component);
        }
    }

    public int getComponentAmount(int component) {
        if(component == PcComponentType.RAM.ordinal()) {
            return MainActivity.desktopSet.getFinal2().get(indexOfSet).getRm().getAmount();
        }

        return 0;
    }

    private void updatePrice(int changedComponent) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(changedComponent == PcComponentType.RAM.ordinal()) {
            String price = formatter.format(getComponentPrice(changedComponent)) + "원";
            pcComponents[changedComponent].setNameAndPrice(getComponentName(changedComponent), price);
        }

        priceTextView.setText("Total : "+formatter.format(MainActivity.desktopSet.getFinal2().get(indexOfSet).getPrice()) + "원");
    }
}