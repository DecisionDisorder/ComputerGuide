package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.FinalTwo;

public class CompareActivity extends AppCompatActivity {

    private int[] compareIndex = new int[2];
    private FinalTwo[] estimateList;
    private Laptop[] laptops;

    private LinearLayout leftContainer;
    private LinearLayout rightContainer;

    String[] componentsNameArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        leftContainer = findViewById(R.id.compareSet1Container);
        rightContainer = findViewById(R.id.compareSet2Container);

        Intent intent = getIntent();
        Bundle compareBundle = intent.getBundleExtra("CompareBundle");
        String computerType = compareBundle.getString("ComputerType");
        compareIndex = compareBundle.getIntArray("CompareIndex");

        estimateList = PastModelListActivity.recommendListManager.getCompareDesktopList(compareIndex);
        laptops = PastModelListActivity.recommendListManager.getCompareLaptopList(compareIndex);

        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            componentsNameArr = getResources().getStringArray(R.array.computer_components);

            initDesktopCompareList();
        }
        else{
            componentsNameArr = getResources().getStringArray(R.array.laptop_components);

            initLaptopCompareList();
        }
    }

    private void initDesktopCompareList() {
        for(int i = 0; i < componentsNameArr.length; i++) {
            CompareLayout leftComponent = new CompareLayout(this);
            leftComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            if(i != EstimateListActivity.PcComponentType.RAM.ordinal())
                leftComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[0]));
            else
                leftComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[0]) + " x" + estimateList[0].getRm().getAmount());
            leftContainer.addView(leftComponent);

            CompareLayout rightComponent = new CompareLayout(this);
            rightComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            if(i != EstimateListActivity.PcComponentType.RAM.ordinal())
                rightComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[1]));
            else
                rightComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[1]) + " x" + estimateList[1].getRm().getAmount());
            rightContainer.addView(rightComponent);

            setDesktopCompareColor(leftComponent, rightComponent, i);
        }
    }
    private void initLaptopCompareList() {
        for(int i = 0; i < componentsNameArr.length; i++) {
            CompareLayout leftComponent = new CompareLayout(this);
            leftComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            leftComponent.setComponentName(EstimateListActivity.getComponentName(i, laptops[0]));
            leftContainer.addView(leftComponent);

            CompareLayout rightComponent = new CompareLayout(this);
            rightComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            rightComponent.setComponentName(EstimateListActivity.getComponentName(i, laptops[1]));
            rightContainer.addView(rightComponent);

            setLaptopCompareColor(leftComponent, rightComponent, i);
        }
    }

    private void setDesktopCompareColor(CompareLayout leftComponent, CompareLayout rightComponent, int component) {
        int leftPerformance = 0;
        int rightPerformance = 0;

        EstimateListActivity.PcComponentType type = EstimateListActivity.PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                leftPerformance = estimateList[0].getCpu().getPriorityGaming();
                rightPerformance = estimateList[1].getCpu().getPriorityGaming();
                break;
            case COOLER:
                break;
            case MB:
                break;
            case RAM:
                break;
            case VGA:
                leftPerformance = estimateList[0].getGpu().getPriority();
                rightPerformance = estimateList[1].getGpu().getPriority();
                break;
            case STORAGE:
                break;
            case CASE:
                break;
            case POWER:
                break;
        }


        if(leftPerformance > rightPerformance) {
            leftComponent.setCompareColor(true);
            rightComponent.setCompareColor(false);
        }
        else if(rightPerformance > leftPerformance) {
            rightComponent.setCompareColor(true);
            leftComponent.setCompareColor(false);
        }
    }

    private void setLaptopCompareColor(CompareLayout leftComponent, CompareLayout rightComponent, int component) {
        int leftPerformance = 0;
        int rightPerformance = 0;

        EstimateListActivity.LaptopComponentType type = EstimateListActivity.LaptopComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                leftPerformance = laptops[0].getClv();
                rightPerformance = laptops[1].getClv();
                break;
            case RAM:
                break;
            case VGA:
                leftPerformance = laptops[0].getGlv();
                rightPerformance = laptops[1].getGlv();
                break;
            case STORAGE:
                break;
            case OS:
                leftPerformance = laptops[0].isContainOS() ? 1 : 0;
                rightPerformance = laptops[1].isContainOS() ? 1 : 0;
                break;
            case DISPLAY:
                break;
            case WEIGHT:
                leftPerformance = 1000 - (int)(laptops[0].getWeight() * 100);
                rightPerformance = 1000 - (int)(laptops[1].getWeight() * 100);
                break;
        }


        if(leftPerformance > rightPerformance) {
            leftComponent.setCompareColor(true);
            rightComponent.setCompareColor(false);
        }
        else if(rightPerformance > leftPerformance) {
            rightComponent.setCompareColor(true);
            leftComponent.setCompareColor(false);
        }
    }
}