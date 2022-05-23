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

    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_detailed_usage);

        layout = findViewById(R.id.detailedUsageContainer);


        Intent receivedIntent = getIntent();
        Bundle rcvBundle = receivedIntent.getBundleExtra("typeBundle");
        String usageType = rcvBundle.getString("type");
        String computerType = rcvBundle.getString("computerType");
        TextView detailedUsageTitle = findViewById(R.id.detailedUsageTitle);

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

    private void setDetailedUsageButtons(String[] typeList, String[] typeExampleList,
                                         String computerType, String usageType) {
        DetailedUsageButton[] gameButtons = new DetailedUsageButton[typeList.length];
        for(int i = 0; i < typeList.length; i++)
        {
            final int detailedUsageType = i;
            gameButtons[i] = new DetailedUsageButton(this);

            if(typeExampleList != null)
                gameButtons[i].setText(getFormattedString(typeList[i], typeExampleList[i]));
            else
                gameButtons[i].setText(typeList[i]);

            gameButtons[i].setOnClickListener(new View.OnClickListener() {
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
            layout.addView(gameButtons[i]);
        }
    }

    private SpannableString getFormattedString(String name, String example) {
        SpannableString spannableString = new SpannableString(name + "\n" + example);
        spannableString.setSpan(new RelativeSizeSpan(0.75f), name.length(), spannableString.length(), 0);
        return spannableString;
    }

    private void startNextActivityOfDesktop(String usageType, int detailedUsageType, String computerType) {
        Intent intent = new Intent(getApplicationContext(), BudgetSelectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("usageType", usageType);
        bundle.putInt("detailedUsageType", detailedUsageType);
        bundle.putString("computerType", computerType);
        intent.putExtra("usageBundle", bundle);
        startActivity(intent);
    }

    private void startNextActivityOfLaptop(String usageType, int detailedUsageType, String computerType) {
        Intent intent = new Intent(getApplicationContext(), LaptopSizeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("usageType", usageType);
        bundle.putInt("detailedUsageType", detailedUsageType);
        bundle.putString("computerType", computerType);
        intent.putExtra("usageBundle", bundle);
        startActivity(intent);
    }
}