package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.threebro.computerguide.CSV.Usage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BudgetSelectionActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Button[] budgetButtons;

    private List<com.threebro.computerguide.CSV.Usage> Usage = new ArrayList<>();

    private void readUsageData(){
        InputStream is = getResources().openRawResource(R.raw.usage);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            reader.readLine();
            while( (line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                Usage us = new Usage();
                us.setUsage(tokens[0]);
                us.setCpuPriority(Integer.parseInt(tokens[1]));
                us.setGpuPriority(Integer.parseInt(tokens[2]));
                Usage.add(us);
            }
        } catch (IOException e) {
            Log.d("MyActivity", "Error reading data file "+line,e);
            e.printStackTrace();
        }
    }//용도에 만

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_selection);

        layout = findViewById(R.id.budgetLayout);
        drawBudgetButtons();

        Bundle desktopBundle = getIntent().getBundleExtra("usageBundle");
        Bundle laptopBundle = getIntent().getBundleExtra("brandBundle");
        if(desktopBundle != null) {
            String usageType = desktopBundle.getString("usageType");
            int detailedUsageType = desktopBundle.getInt("detailedUsageType");
            setButtonListener(desktopBundle);
        }
        else if(laptopBundle != null) {
            String usageType = laptopBundle.getString("usageType");
            int detailedUsageType = laptopBundle.getInt("detailedUsageType");
            int laptopSizeIndex = laptopBundle.getInt("laptopSizeIndex");
            int laptopWeightIndex = laptopBundle.getInt("laptopWeightIndex");
            int brandType = laptopBundle.getInt("brandType");
            setButtonListener(laptopBundle);
        }
    }

    private void drawBudgetButtons()
    {
        int length = 16;
        int budgetMin = 500000;
        int interval = 100000;
        budgetButtons = new Button[length];
        DecimalFormat formatter =new DecimalFormat("#,###");
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(100, 50, 100, 0);
        LinearLayout.LayoutParams lastParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lastParam.setMargins(100, 50, 100, 50);

        for(int i = 0; i < length; i++) {
            int price = budgetMin + interval * i;
            Typeface font = Typeface.createFromAsset(getAssets(), "godo_m.TTF");
            budgetButtons[i] = new Button(this);
            budgetButtons[i].setText("\\" + formatter.format(price) + "s");
            budgetButtons[i].setAllCaps(false);
            budgetButtons[i].setTypeface(font);
            budgetButtons[i].setTextSize(Dimension.DP, 60);
            if(i >= length - 1)
                budgetButtons[i].setLayoutParams(lastParam);
            else
                budgetButtons[i].setLayoutParams(param);
            budgetButtons[i].setBackground(this.getResources().getDrawable(R.drawable.round_button));
            layout.addView(budgetButtons[i]);
        }
    }

    private void setButtonListener(Bundle bundle){
        for(int i = 0; i < budgetButtons.length; i++)
        {
            final int budgetIndex = i;
            budgetButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bundle.putInt("budgetType", budgetIndex);
                    Intent productListActivity = new Intent(getApplicationContext(), ProductListActivity.class);
                    productListActivity.putExtra("budgetBundle", bundle);
                    startActivity(productListActivity);
                }
            });
        }
    }
}