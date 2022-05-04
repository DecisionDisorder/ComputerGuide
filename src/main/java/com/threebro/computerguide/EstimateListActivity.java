package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class EstimateListActivity extends AppCompatActivity {

    private enum PcComponentType {CPU, COOLER, MB, RAM, VGA, SSD, HDD, CASE, POWER}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_list);

        LinearLayout componentContainer = findViewById(R.id.componentContainer);
        PcComponent[] pcComponents = new PcComponent[PcComponentType.values().length];

        for(int i = 0; i < pcComponents.length; i++)
            pcComponents[i] = new PcComponent(this);

        pcComponents[PcComponentType.CPU.ordinal()].setTitle(getResources().getString(R.string.cpu));
        pcComponents[PcComponentType.COOLER.ordinal()].setTitle(getResources().getString(R.string.cooler));
        pcComponents[PcComponentType.MB.ordinal()].setTitle(getResources().getString(R.string.mainboard));
        pcComponents[PcComponentType.RAM.ordinal()].setTitle(getResources().getString(R.string.ram));
        pcComponents[PcComponentType.VGA.ordinal()].setTitle(getResources().getString(R.string.vga));
        pcComponents[PcComponentType.SSD.ordinal()].setTitle(getResources().getString(R.string.ssd));
        pcComponents[PcComponentType.HDD.ordinal()].setTitle(getResources().getString(R.string.hdd));
        pcComponents[PcComponentType.CASE.ordinal()].setTitle(getResources().getString(R.string.computer_case));
        pcComponents[PcComponentType.POWER.ordinal()].setTitle(getResources().getString(R.string.power));

        for(int i = 0; i < pcComponents.length; i++)
            componentContainer.addView(pcComponents[i]);
    }
}