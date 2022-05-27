package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.threebro.computerguide.Combi.FinalTwo;

public class CompareActivity extends AppCompatActivity {

    private int[] compareIndex = new int[2];
    private FinalTwo[] estimateList;

    private LinearLayout leftContainer;
    private LinearLayout rightContainer;

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

        initCompareList();
    }

    private void initCompareList() {
        for(int i = 0; i < EstimateListActivity.componentsNameArr.length; i++) {
            CompareLayout leftComponent = new CompareLayout(this);
            leftComponent.setComponentTitle("[" + EstimateListActivity.componentsNameArr[i] + "]");
            if(i != EstimateListActivity.PcComponentType.RAM.ordinal())
                leftComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[0]));
            else
                leftComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[0]) + " x" + estimateList[0].getRm().getAmount());
            leftContainer.addView(leftComponent);

            CompareLayout rightComponent = new CompareLayout(this);
            rightComponent.setComponentTitle("[" + EstimateListActivity.componentsNameArr[i] + "]");
            if(i != EstimateListActivity.PcComponentType.RAM.ordinal())
                rightComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[1]));
            else
                rightComponent.setComponentName(EstimateListActivity.getComponentName(i, estimateList[1]) + " x" + estimateList[1].getRm().getAmount());
            rightContainer.addView(rightComponent);
        }
    }
}