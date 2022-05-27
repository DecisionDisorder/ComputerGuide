package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.FinalTwo;

import java.text.DecimalFormat;

public class EstimateListActivity extends AppCompatActivity {

    private int indexOfSet;
    private PcComponent[] pcComponents;
    private FinalTwo desktopEstimate;
    private Laptop laptopEstimate;

    static String[] componentsNameArr;

    private TextView priceTextView;
    private DBHelper db;

    public enum PcComponentType {
        CPU, COOLER, MB, RAM, VGA, STORAGE, CASE, POWER;
        private static PcComponentType[] allValues = values();
        public static PcComponentType fromOrdinal(int n) {return allValues[n];}
    }

    public enum LaptopComponentType {
        NAME, COMPANY, CPU, RAM, VGA, STORAGE, OS, DISPLAY, WEIGHT;
        private static LaptopComponentType[] allValues = values();
        public static LaptopComponentType fromOrdinal(int n) {return allValues[n];}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        setContentView(R.layout.activity_estimate_list);

        Intent rcvintent = getIntent();
        indexOfSet = rcvintent.getIntExtra("index",3);
        String listType = rcvintent.getStringExtra("ListType");
        Bundle productBundle = rcvintent.getBundleExtra("productBundle");
        String computerType = productBundle.getString("computerType");
        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            if (listType.equals("New")) {
                desktopEstimate = MainActivity.desktopSet.getFinal2().get(indexOfSet);
                loadEstimateList(ComputerType.DESKTOP);
                db.addProductList(desktopEstimate);
                Log.d("test", db.getResult());
            } else if (listType.equals("Past")) {
                desktopEstimate = PastModelListActivity.recommendListManager.recommendedSetList.get(indexOfSet).getRecommendedSet();
                loadEstimateList(ComputerType.DESKTOP);
            }
        }
        else {
            if (listType.equals("New")) {
                laptopEstimate = MainActivity.laptopSet.getFlaptop().get(indexOfSet);
                loadEstimateList(ComputerType.LAPTOP);
                db.addLabProductList(laptopEstimate);
            } else if (listType.equals("Past")) {
                desktopEstimate = PastModelListActivity.recommendListManager.recommendedSetList.get(indexOfSet).getRecommendedSet();
                loadEstimateList(ComputerType.LAPTOP);
            }
        }
    }

    private void loadEstimateList(ComputerType type) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        priceTextView = findViewById(R.id.priceID);

        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        TypedArray iconIdArr;

        if(type == ComputerType.DESKTOP) {
            priceTextView.setText("Total : " + formatter.format(desktopEstimate.getPrice()) + "원");
            pcComponents = new PcComponent[PcComponentType.values().length];
            componentsNameArr = getResources().getStringArray(R.array.computer_components);
            iconIdArr = getResources().obtainTypedArray(R.array.icon_array);

            for (int i = 0; i < pcComponents.length; i++) {
                if (i == PcComponentType.RAM.ordinal()) {
                    int maxAmount = desktopEstimate.getMb().getSlotAmount();
                    pcComponents[i] = new PcComponent(this, maxAmount);
                    pcComponents[i].setActiveAmountSet(true, getComponentAmount(i));
                } else
                    pcComponents[i] = new PcComponent(this);
                pcComponents[i].setTitle(componentsNameArr[i]);
                pcComponents[i].setIcon(iconIdArr.getDrawable(i));

                String name = getComponentName(i, desktopEstimate);
                String price = formatter.format(getComponentPrice(i, desktopEstimate)) + "원";
                pcComponents[i].setNameAndPrice(name, price);
                if (i == PcComponentType.VGA.ordinal() || i == PcComponentType.POWER.ordinal())
                    pcComponents[i].setTextSize();
                componentContainer.addView(pcComponents[i]);
            }
        }
        else {
            priceTextView.setText("Total : " + formatter.format(laptopEstimate.getPrice()) + "원");
            pcComponents = new PcComponent[LaptopComponentType.values().length];
            componentsNameArr = getResources().getStringArray(R.array.laptop_components);
            iconIdArr = getResources().obtainTypedArray(R.array.laptop_icon_array);

            for (int i = 0; i < pcComponents.length; i++) {
                pcComponents[i] = new PcComponent(this);
                pcComponents[i].setTitle(componentsNameArr[i]);
                pcComponents[i].setIcon(iconIdArr.getDrawable(i));

                String name = getComponentName(i, laptopEstimate);
                String price = "";
                pcComponents[i].setNameAndPrice(name, price);
                componentContainer.addView(pcComponents[i]);
            }
        }
    }

    public static String getComponentName(int component, FinalTwo desktopEstimate) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return desktopEstimate.getCpu().getName();
            case COOLER:
                return desktopEstimate.getCl().getName();
            case MB:
                return desktopEstimate.getMb().getName();
            case RAM:
                RAM ram = desktopEstimate.getRm();
                return ram.getName() + " " + ram.getRamCapacity();
            case VGA:
                return desktopEstimate.getGpu().getName();
            case STORAGE:
                Storage storage = desktopEstimate.getSt();
                return storage.getName() + " " + storage.getCapacity();
            case CASE:
                return desktopEstimate.getCa().getName();
            case POWER:
                return desktopEstimate.getPw().getName();
        }
        return "";
    }

    public static int getComponentPrice(int component, FinalTwo desktopEstimate) {
        PcComponentType type = PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                return desktopEstimate.getCpu().getPrice();
            case COOLER:
                return desktopEstimate.getCl().getPrice();
            case MB:
                return desktopEstimate.getMb().getPrice();
            case RAM:
                return desktopEstimate.getRm().getPrice();
            case VGA:
                return desktopEstimate.getGpu().getPrice();
            case STORAGE:
                return desktopEstimate.getSt().getPrice();
            case CASE:
                return desktopEstimate.getCa().getPrice();
            case POWER:
                return desktopEstimate.getPw().getPrice();
        }
        return 0;
    }

    public static String getComponentName(int component, Laptop laptopEstimate) {
        LaptopComponentType type = LaptopComponentType.fromOrdinal(component);
        switch (type) {
            case NAME:
                return laptopEstimate.getName();
            case COMPANY:
                return laptopEstimate.getCompany();
            case CPU:
                return laptopEstimate.getCpu3();
            case RAM:
                return (int)laptopEstimate.getMemory() + "GB";
            case VGA:
                return laptopEstimate.getGraphic();
            case STORAGE:
                return (int)laptopEstimate.getSsd() + "GB";
            case OS:
                return laptopEstimate.getOs();
            case DISPLAY:
                return laptopEstimate.getDisplay() + "inch";
            case WEIGHT:
                return laptopEstimate.getWeight() + "kg";
        }
        return "";
    }

    public void setComponentAmount(int component, int amount) {
        if(component == PcComponentType.RAM.ordinal()) {
            int priorAmount = getComponentAmount(component);
            desktopEstimate.getRm().setAmount(priorAmount + amount);
            pcComponents[component].setAmountTextView(getComponentAmount(component));
            updatePrice(component);
        }
    }

    public int getComponentAmount(int component) {
        if(component == PcComponentType.RAM.ordinal()) {
            return desktopEstimate.getRm().getAmount();
        }

        return 0;
    }

    private void updatePrice(int changedComponent) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        if(changedComponent == PcComponentType.RAM.ordinal()) {
            String price = formatter.format(getComponentPrice(changedComponent, desktopEstimate)) + "원";
            pcComponents[changedComponent].setNameAndPrice(getComponentName(changedComponent, desktopEstimate), price);
        }

        priceTextView.setText("Total : "+formatter.format(desktopEstimate.getPrice()) + "원");
    }
}