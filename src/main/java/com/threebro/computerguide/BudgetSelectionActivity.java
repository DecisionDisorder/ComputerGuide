package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private int availableCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_selection);

        layout = findViewById(R.id.budgetLayout);
        TextView noBudgetTextView = findViewById(R.id.noBudgetTextView);
        availableCount = 0;

        Bundle desktopBundle = getIntent().getBundleExtra("usageBundle");
        Bundle laptopBundle = getIntent().getBundleExtra("brandBundle");
        if(desktopBundle != null) {
            String usageType = desktopBundle.getString("usageType");
            int detailedUsageType = desktopBundle.getInt("detailedUsageType");
            drawBudgetButtons();
            setButtonListener(desktopBundle);
        }
        else if(laptopBundle != null) {
            String usageType = laptopBundle.getString("usageType");
            String detailedUsageType = laptopBundle.getString("detailedUsageType");
            int laptopSizeIndex = laptopBundle.getInt("laptopSizeIndex");
            int laptopWeightIndex = laptopBundle.getInt("laptopWeightIndex");
            int brandType = laptopBundle.getInt("brandType");
            brandType+=1;
            float wt = (float) (1.5 + 0.5*laptopWeightIndex);
            float dsize = 13+1*laptopSizeIndex;
            drawBudgetButtonsLT(usageType, detailedUsageType, dsize, brandType, wt);
            setButtonListener(laptopBundle);
        }

        if(availableCount == 0){
            //noBudgetTextView.setVisibility(View.VISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Budget Message").setMessage(getResources().getString(R.string.no_budget_message)).
                setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BudgetSelectionActivity.this.finish();
                    }
                });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
            noBudgetTextView.setVisibility(View.GONE);
    }

    private void drawBudgetButtons() {
        int length = 16;
        int budgetMin = 500000;
        int interval = 100000;

        boolean[] availablePriceList = MainActivity.desktopSet.getAvailablePriceList();
        budgetButtons = new Button[length];
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(100, 50, 100, 0);
        LinearLayout.LayoutParams lastParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lastParam.setMargins(100, 50, 100, 50);

        for(int i = 0; i < length; i++) {
            if(availablePriceList[i]) {
                int price = budgetMin + interval * i;
                availableCount++;
                if (i >= length - 1)
                    addBudgetButton(price, i, length, lastParam);
                else
                    addBudgetButton(price, i, length, param);
            }
        }
    }

    //이준희 코드
    private void drawBudgetButtonsLT(String usage, String dusage, float dsize, int company, double wt) {
        int length = 16;
        int budgetMin = 500000;
        int interval = 100000;

        int price1 = MainActivity.laptopSet.Selectprice(usage, dusage, dsize, company, wt);

        boolean[] availablePriceList = MainActivity.laptopSet.getAvailablePriceList(price1);
        budgetButtons = new Button[length];
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(100, 50, 100, 0);
        LinearLayout.LayoutParams lastParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lastParam.setMargins(100, 50, 100, 50);

        for(int i = 0; i < length; i++) {
            if(availablePriceList[i]) {
                int price = budgetMin + interval * i;
                availableCount++;

                if (i >= length - 1)
                    addBudgetButton(price, i, length, lastParam);
                else
                    addBudgetButton(price, i, length, param);
            }
        }
    }

    private void addBudgetButton(int price, int i, int length, LinearLayout.LayoutParams param) {
        DecimalFormat formatter =new DecimalFormat("#,###");
        Typeface font = Typeface.createFromAsset(getAssets(), "nanum_square_l.ttf");
        budgetButtons[i] = new Button(this);
        budgetButtons[i].setText("\\" + formatter.format(price) + "s");
        budgetButtons[i].setAllCaps(false);
        budgetButtons[i].setTypeface(font);
        budgetButtons[i].setTextSize(Dimension.DP, 60);
        budgetButtons[i].setBackground(this.getResources().getDrawable(R.drawable.round_button));
        budgetButtons[i].setLayoutParams(param);
        layout.addView(budgetButtons[i]);
    }

    private void setButtonListener(Bundle bundle){
        for(int i = 0; i < budgetButtons.length; i++)
        {
            if(budgetButtons[i] != null) {
                final int budgetIndex = i;
                budgetButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putInt("budgetType", budgetIndex);

                        MainActivity.desktopSet.FinalCombinationPrice(budgetIndex*100000+500000,(budgetIndex+1)*100000+500000);
                        Intent productListActivity = new Intent(getApplicationContext(), ProductListActivity.class);
                        productListActivity.putExtra("budgetBundle", bundle);
                        startActivity(productListActivity);
                    }
                });
            }
        }
    }
}