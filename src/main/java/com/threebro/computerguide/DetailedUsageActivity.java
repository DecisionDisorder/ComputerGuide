package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.threebro.computerguide.CSV.Usage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DetailedUsageActivity extends Activity {

    // Detailed Usage Container layout
    private LinearLayout layout;

    // List of specification rankings by usage
    private List<Usage> Usage = new ArrayList<>();

    // Read usage data from csv and initialize usage array list
    private void readUsageData(){
        // Open input stream with usage csv file
        InputStream is = getResources().openRawResource(R.raw.usage);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));

        String line ="";
        try{
            // Read the csv file and set the required CPU and GPU priority for each usage
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
    }//용도에 맡게 입력


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readUsageData();
        super.onCreate(savedInstanceState);

        // Set small activity without title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detailed_usage);

        // Get layout from activity
        layout = findViewById(R.id.detailedUsageContainer);

        // Set prior user selected data from prior activity's intent and bundle
        Intent receivedIntent = getIntent();
        Bundle rcvBundle = receivedIntent.getBundleExtra("typeBundle");
        String usageType = rcvBundle.getString("type");
        String computerType = rcvBundle.getString("computerType");
        TextView detailedUsageTitle = findViewById(R.id.detailedUsageTitle);

        // The detailed usage for each usage is floated by the button of the activity.
        if(usageType.equals("game")){
            detailedUsageTitle.setText(getResources().getString(R.string.game_usage_title));

            String[] gameList = getResources().getStringArray(R.array.game_category);
            String[] gameListEx = getResources().getStringArray(R.array.game_category_example);
            setDetailedUsageButtons(gameList, gameListEx, computerType, usageType);
        }
        else if(usageType.equals("professional")) {
            detailedUsageTitle.setText(getResources().getString(R.string.professional_work));
            String[] workList = getResources().getStringArray(R.array.pro_category);
            setDetailedUsageButtons(workList, null, computerType, usageType);
        }
        else if(usageType.equals("simple_work")) {
            detailedUsageTitle.setText(getResources().getString(R.string.simple_work));
            String[] workList = getResources().getStringArray(R.array.simple_work_category);
            setDetailedUsageButtons(workList, null, computerType, usageType);
        }
        else {
            Toast.makeText(this, "Invalid work type", Toast.LENGTH_SHORT).show();
        }
    }

    // Set detailed usage buttons with given parameters
    private void setDetailedUsageButtons(String[] typeList, String[] typeExampleList,
                                         String computerType, String usageType) {
        // Create button array
        DetailedUsageButton[] detailedButtons = new DetailedUsageButton[typeList.length];
        for(int i = 0; i < typeList.length; i++)
        {
            // Create and set Detailed Button with usage text
            final String detailedUsageType = typeList[i];
            detailedButtons[i] = new DetailedUsageButton(this);

            // If formatted string is needed, set text with formatted string(various sized text)
            if(typeExampleList != null)
                detailedButtons[i].setText(getFormattedString(typeList[i], typeExampleList[i]));
            else
                detailedButtons[i].setText(typeList[i]);

            // Set detailed usage button listener for each computer type to start the next activity
            detailedButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(computerType.equals(ComputerType.DESKTOP.toString())) {
                        startNextActivityOfDesktop(usageType, detailedUsageType, computerType);
                    }
                    else if(computerType.equals(ComputerType.LAPTOP.toString())){
                        startNextActivityOfLaptop(usageType, detailedUsageType, computerType);
                    }
                    else
                        Toast.makeText(DetailedUsageActivity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
                }
            });

            // Add detailed usage buttons to layout container
            layout.addView(detailedButtons[i]);
        }
    }

    // Get formatted string that has various sized text in Spannable String type
    private SpannableString getFormattedString(String name, String example) {
        SpannableString spannableString = new SpannableString(name + "\n" + example);
        spannableString.setSpan(new RelativeSizeSpan(0.75f), name.length(), spannableString.length(), 0);
        return spannableString;
    }

    // Start next activity with given conditions
    private void startNextActivityOfDesktop(String usageType, String detailedUsageType, String computerType) {
        Intent intent = new Intent(getApplicationContext(), BudgetSelectionActivity.class);

        // Build desktop estimates by usage
        for(int i=0;i<Usage.size();i++){
            if(detailedUsageType.equals(Usage.get(i).getUsage())){
                MainActivity.desktopSet.FinalCombinationGaming(Usage.get(i).CpuPriority,Usage.get(i).GpuPriority);
            }
        }

        // Set bundle to be send to next activity
        Bundle bundle = new Bundle();
        bundle.putString("usageType", usageType);
        bundle.putString("detailedUsageType", detailedUsageType);
        bundle.putString("computerType", computerType);
        intent.putExtra("usageBundle", bundle);

        // Start next activity (Budget Selection Activity)
        startActivity(intent);
    }

    private void startNextActivityOfLaptop(String usageType, String detailedUsageType, String computerType) {
        // Set bundle to be send to next activity
        Intent intent = new Intent(getApplicationContext(), LaptopSizeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("usageType", usageType);
        bundle.putString("detailedUsageType", detailedUsageType);
        bundle.putString("computerType", computerType);
        intent.putExtra("usageBundle", bundle);

        // Start next activity (Laptop Size Activity)
        startActivity(intent);
    }
}