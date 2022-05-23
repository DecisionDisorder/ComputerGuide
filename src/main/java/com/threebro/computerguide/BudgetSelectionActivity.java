package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.DecimalFormat;

public class BudgetSelectionActivity extends AppCompatActivity {

    private LinearLayout layout;
    private Button[] budgetButtons;

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

                if (i >= length - 1)
                    addBudgetButton(price, i, length, lastParam);
                else
                    addBudgetButton(price, i, length, param);
            }
        }
    }

    private void addBudgetButton(int price, int i, int length, LinearLayout.LayoutParams param) {
        DecimalFormat formatter =new DecimalFormat("#,###");
        Typeface font = Typeface.createFromAsset(getAssets(), "godo_m.TTF");
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
                        Intent productListActivity = new Intent(getApplicationContext(), ProductListActivity.class);
                        productListActivity.putExtra("budgetBundle", bundle);
                        startActivity(productListActivity);
                    }
                });
            }
        }
    }
}