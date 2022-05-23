package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.LinearLayout;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Case;
import com.threebro.computerguide.CSV.Cooler;
import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.MainBoard;
import com.threebro.computerguide.CSV.Power;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;
import com.threebro.computerguide.Combi.CPUMB;
import com.threebro.computerguide.Combi.FinalRes;
import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.GPUPW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static DesktopSet desktopSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(desktopSet == null)
            desktopSet = new DesktopSet(this);

        desktopSet.readCsvData();
        desktopSet.MakeComBi();

        desktopSet.FinalCombinationGaming(61, 13);
        desktopSet.FinalCombinationPrice(800000,900000);

        LinearLayout desktopButton = findViewById(R.id.desktopBtn);
        desktopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desktopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                desktopIntent.putExtra("computerType", ComputerType.DESKTOP.toString());
                startActivity(desktopIntent);
            }
        });

        LinearLayout laptopButton = findViewById(R.id.laptopBtn);
        laptopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent laptopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                laptopIntent.putExtra("computerType", ComputerType.LAPTOP.toString());
                startActivity(laptopIntent);
            }
        });
    }
}

class PriceCompare implements Comparator<FinalRes>{

    @Override
    public int compare(FinalRes f1, FinalRes f2) {
        if(f1.getTotalPrice()>f2.getTotalPrice()){
            return 1;
        }
        else if(f1.getTotalPrice()<f2.getTotalPrice()){
            return -1;
        }
        return 0;
    }
}






