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

    private int[] compareIndex = new int[2];    // Two indexes to be compared
    private String[] compareSetNames;           // Names of set to be compared
    private FinalTwo[] estimateList;            // List of two desktop estimates to be compared
    private Laptop[] laptops;                   // List of two laptop models to be compared

    private LinearLayout leftContainer;         // Left container of estimate(model) 0
    private LinearLayout rightContainer;        // Right container of estimate(model) 1

    String[] componentsNameArr;                 // Component names of each computer type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // Get UI views from activity xml
        leftContainer = findViewById(R.id.compareSet1Container);
        rightContainer = findViewById(R.id.compareSet2Container);

        // Get intent and bundle data received from prior activity
        Intent intent = getIntent();
        Bundle compareBundle = intent.getBundleExtra("CompareBundle");
        String computerType = compareBundle.getString("ComputerType");
        compareIndex = compareBundle.getIntArray("CompareIndex");
        compareSetNames = compareBundle.getStringArray("SetNames");

        // Get and set UI views from activity xml
        TextView leftTitleTextView = findViewById(R.id.leftSetTitleText);
        TextView rightTitleTextView = findViewById(R.id.rightSetTitleText);
        leftTitleTextView.setText(compareSetNames[0]);
        rightTitleTextView.setText(compareSetNames[1]);

        // Initialize by loading specifications and names of components,
        // depending on the type of computer user wants to compare
        if(computerType.equals(ComputerType.DESKTOP.toString())) {
            estimateList = PastModelListActivity.recommendListManager.getCompareDesktopList(compareIndex);
            componentsNameArr = getResources().getStringArray(R.array.computer_components);

            initDesktopCompareList();
        }
        else{
            laptops = PastModelListActivity.recommendListManager.getCompareLaptopList(compareIndex);
            componentsNameArr = getResources().getStringArray(R.array.laptop_components);

            initLaptopCompareList();
        }
    }

    // Initialize desktop compare list for all components
    private void initDesktopCompareList() {
        for(int i = 0; i < componentsNameArr.length; i++) {
            // Set Left and Right component list with title and name
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

            // Set the color of text that indicates which one is better
            setDesktopCompareColor(leftComponent, rightComponent, i);
        }
    }

    // Initialize laptop compare list for all components
    private void initLaptopCompareList() {
        for(int i = 0; i < componentsNameArr.length; i++) {
            // Set Left and Right component list with title and name
            CompareLayout leftComponent = new CompareLayout(this);
            leftComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            leftComponent.setComponentName(EstimateListActivity.getComponentName(i, laptops[0]));
            leftContainer.addView(leftComponent);

            CompareLayout rightComponent = new CompareLayout(this);
            rightComponent.setComponentTitle("[" + componentsNameArr[i] + "]");
            rightComponent.setComponentName(EstimateListActivity.getComponentName(i, laptops[1]));
            rightContainer.addView(rightComponent);

            // Set the color of text that indicates which one is better
            setLaptopCompareColor(leftComponent, rightComponent, i);
        }
    }

    // Set the color of text that indicates which one is better
    private void setDesktopCompareColor(CompareLayout leftComponent, CompareLayout rightComponent, int component) {
        int leftPerformance = 0;    // Left component's performance (Higher is better)
        int rightPerformance = 0;   // Right component's performance (Higher is better)

        EstimateListActivity.PcComponentType type = EstimateListActivity.PcComponentType.fromOrdinal(component);
        switch (type) {
            case CPU:
                leftPerformance = 100 - estimateList[0].getCpu().getPriorityGaming();
                rightPerformance = 100 - estimateList[1].getCpu().getPriorityGaming();
                break;
            case COOLER:
                break;
            case MB:
                break;
            case RAM:
                break;
            case VGA:
                leftPerformance = 100 - estimateList[0].getGpu().getPriority();
                rightPerformance = 100 - estimateList[1].getGpu().getPriority();
                break;
            case STORAGE:
                break;
            case CASE:
                break;
            case POWER:
                break;
        }

        // If left component is better, set left color to positive and right color to negative color
        if(leftPerformance > rightPerformance) {
            leftComponent.setCompareColor(true);
            rightComponent.setCompareColor(false);
        }
        // If right component is better, set right color to positive and left color to negative color
        else if(rightPerformance > leftPerformance) {
            rightComponent.setCompareColor(true);
            leftComponent.setCompareColor(false);
        }
    }

    // Set the color of text that indicates which one is better
    private void setLaptopCompareColor(CompareLayout leftComponent, CompareLayout rightComponent, int component) {
        int leftPerformance = 0;    // Left component's performance (Higher is better)
        int rightPerformance = 0;   // Right component's performance (Higher is better)

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

        // If left component is better, set left color to positive and right color to negative color
        if(leftPerformance > rightPerformance) {
            leftComponent.setCompareColor(true);
            rightComponent.setCompareColor(false);
        }
        // If right component is better, set right color to positive and left color to negative color
        else if(rightPerformance > leftPerformance) {
            rightComponent.setCompareColor(true);
            leftComponent.setCompareColor(false);
        }
    }
}