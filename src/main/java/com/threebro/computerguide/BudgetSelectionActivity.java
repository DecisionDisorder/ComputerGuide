package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.DecimalFormat;

public class BudgetSelectionActivity extends AppCompatActivity {

    private LinearLayout layout;    // Layout container of budget buttons
    private Button[] budgetButtons; // Budget button array
    private int availableCount = 0; // Number of valid price ranges

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_selection);

        // Get UI views from activity xml
        layout = findViewById(R.id.budgetLayout);

        // Initialize number of valid price ranges
        availableCount = 0;

        // Receive bundles that activity can receive by computer type.
        Bundle desktopBundle = getIntent().getBundleExtra("usageBundle");
        Bundle laptopBundle = getIntent().getBundleExtra("brandBundle");
        if(desktopBundle != null) {
            // If desktop bundle is received, draw budget buttons and set budget button listener in desktop mode
            drawBudgetButtons();
            setButtonListener(desktopBundle);
        }
        else if(laptopBundle != null) {
            // Draw a budget button with the data received from the previous activity
            String usageType = laptopBundle.getString("usageType");
            String detailedUsageType = laptopBundle.getString("detailedUsageType");
            int laptopSizeIndex = laptopBundle.getInt("laptopSizeIndex");
            int laptopWeightIndex = laptopBundle.getInt("laptopWeightIndex");
            int brandType = laptopBundle.getInt("brandType");
            // Some data is converted to a processable form.
            // ex) weight and display size data is index
            // convert to real data
            brandType+=1;
            float wt = (float) (1.5 + 0.5 * laptopWeightIndex);
            float dsize = 13 + 1 * laptopSizeIndex;
            drawBudgetButtonsLT(usageType, detailedUsageType, dsize, brandType, wt);
            setButtonListener(laptopBundle);
        }

        // If there is no budget range available, a dialog is displayed to deliver the message.
        if(availableCount == 0){
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
    }

    // Draw buttons for available budget ranges of desktop.
    private void drawBudgetButtons() {
        // Get available budget range in boolean array format
        boolean[] availablePriceList = MainActivity.desktopSet.getAvailablePriceList();

        // Initialize buttons according to availablePriceList
        initBudgetButtons(availablePriceList);
    }

    // Draw buttons for available budget ranges of laptop.
    private void drawBudgetButtonsLT(String usage, String dusage, float dsize, int company, double wt) {
        // The laptop model is summarized primarily according to the conditions selected by the user.
        MainActivity.laptopSet.SelectPrice(usage, dusage, dsize, company, wt);

        // Get available budget range in boolean array format
        boolean[] availablePriceList = MainActivity.laptopSet.getAvailablePriceList();

        // Initialize buttons according to availablePriceList
        initBudgetButtons(availablePriceList);
    }

    // Initialize buttons according to availablePriceList
    private void initBudgetButtons(boolean[] availablePriceList) {
        int length = 16;        // Number of budget ranges (500,000~2,000,000)
        int budgetMin = 500000; // Minimum budget range
        int interval = 100000;  // Interval of budget ranges

        // Create buttons
        budgetButtons = new Button[length];
        // Set the layout settings on the LayoutParam object.
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(100, 50, 100, 0);
        LinearLayout.LayoutParams lastParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lastParam.setMargins(100, 50, 100, 50);

        // Add only the available budget buttons among the number of buttons.
        for(int i = 0; i < length; i++) {
            if(availablePriceList[i]) {
                int price = budgetMin + interval * i; // Convert price index to price range
                availableCount++;

                if (i >= length - 1)
                    addBudgetButton(price, i, lastParam);
                else
                    addBudgetButton(price, i, param);
            }
        }
    }

    // Create new budget button with price and layout param
    private void addBudgetButton(int price, int i, LinearLayout.LayoutParams param) {
        // Format numbers to make them easier to see.
        DecimalFormat formatter = new DecimalFormat("#,###");

        // Get font data from asset
        Typeface font = Typeface.createFromAsset(getAssets(), "nanum_square_l.ttf");

        // Set button settings and add it to the layout container
        budgetButtons[i] = new Button(this);
        budgetButtons[i].setText("\\" + formatter.format(price) + "s");
        budgetButtons[i].setAllCaps(false);
        budgetButtons[i].setTypeface(font);
        budgetButtons[i].setTextSize(Dimension.DP, 60);
        budgetButtons[i].setBackground(this.getResources().getDrawable(R.drawable.round_button));
        budgetButtons[i].setLayoutParams(param);
        layout.addView(budgetButtons[i]);
    }

    // Set button listener with bundle that will be sent to next activity
    private void setButtonListener(Bundle bundle){
        for(int i = 0; i < budgetButtons.length; i++)
        {
            // Check if budget button is created
            if(budgetButtons[i] != null) {
                final int budgetIndex = i;
                // Set budget button listener with budget index and bundle
                budgetButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putInt("budgetType", budgetIndex);

                        // Specify the price range for the desktop estimation
                        MainActivity.desktopSet.FinalCombinationPrice(budgetIndex * 100000 + 500000,(budgetIndex + 1) * 100000 + 500000);
                        Intent productListActivity = new Intent(getApplicationContext(), ProductListActivity.class);
                        productListActivity.putExtra("budgetBundle", bundle);

                        // Start ProductListActivity
                        startActivity(productListActivity);
                    }
                });
            }
        }
    }
}