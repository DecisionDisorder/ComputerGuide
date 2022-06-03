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

    static DesktopSet desktopSet;   // Static objects that retain the desktop estimate list
    static LaptopSet laptopSet;     // Static objects that retain the laptop model list

    // Create a listener object to exit the app by pressing the Back button twice
    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new desktop set if empty (prevent duplicate creation)
        if(desktopSet == null)
            desktopSet = new DesktopSet(this);

        // Read desktop csv data and Considering compatibility, make the basic combination.
        desktopSet.readCsvData();
        desktopSet.MakeComBi();

        // Create a new laptop set if empty (prevent duplicate creation)
        if(laptopSet == null)
            laptopSet = new LaptopSet(this);
        // Read laptop csv data
        laptopSet.readLaptop();

        // Set desktop button listener
        LinearLayout desktopButton = findViewById(R.id.desktopBtn);
        desktopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desktopIntent = new Intent(getApplicationContext(), UsageSelectionActivity.class);
                desktopIntent.putExtra("computerType", ComputerType.DESKTOP.toString());
                startActivity(desktopIntent);
            }
        });

        // Set laptop button listener
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

    // Override back key event and call back key handler method
    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }
}

class PriceCompare implements Comparator<FinalRes>{

    // Compare the total cost of the two estimates
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