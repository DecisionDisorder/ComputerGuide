package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.FinalTwo;

public class CompareActivity extends AppCompatActivity {

    private int[] compareIndex = new int[2];
    private FinalTwo[] estimateList;

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
        compareIndex = compareBundle.getIntArray("CompareIndex");

        estimateList = PastModelListActivity.recommendListManager.getCompareList(compareIndex);

        componentsNameArr = getResources().getStringArray(R.array.computer_components);

        initCompareList();
    }

    private void initCompareList() {
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

            setCompareColor(leftComponent, rightComponent, i);
        }
    }

    private void setCompareColor(CompareLayout leftComponent, CompareLayout rightComponent, int component) {
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
}