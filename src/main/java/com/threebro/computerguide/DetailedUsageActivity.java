package com.threebro.computerguide;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailedUsageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_detailed_usage);

        LinearLayout layout = findViewById(R.id.detailedUsageContainer);

        Intent receivedIntent = getIntent();
        Bundle rcvBundle = receivedIntent.getBundleExtra("typeBundle");
        String usageType = rcvBundle.getString("type");
        String computerType = rcvBundle.getString("computerType");

        if(usageType.equals("game")){
            TextView detailedUsageTitle = findViewById(R.id.detailedUsageTitle);
            detailedUsageTitle.setText(getResources().getString(R.string.game_usage_title));

            String[] gameList = rcvBundle.getStringArray("gameList");
            Button[] gameButtons = new Button[gameList.length];
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            param.setMargins(100, 50, 100, 50);
            for(int i = 0; i < gameList.length; i++)
            {
                final int detailedUsageType = i;
                gameButtons[i] = new Button(this);
                gameButtons[i].setText(gameList[i]);
                gameButtons[i].setTextSize(Dimension.DP, 50);
                gameButtons[i].setLayoutParams(param);
                gameButtons[i].setBackground(this.getResources().getDrawable(R.drawable.underbar_button));
                gameButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(computerType.equals(ComputerType.DESKTOP.toString())) {
                            Intent intent = new Intent(getApplicationContext(), BudgetSelectionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("usageType", usageType);// TODO: 0을 다른 코드로 바꾸기(enum?)
                            bundle.putInt("detailedUsageType", detailedUsageType);
                            bundle.putString("computerType", computerType);
                            intent.putExtra("usageBundle", bundle);
                            startActivity(intent);
                        }
                        else if(computerType.equals(ComputerType.LAPTOP.toString())){
                            Intent intent = new Intent(getApplicationContext(), LaptopSizeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("usageType", usageType);// TODO: 0을 다른 코드로 바꾸기(enum?)
                            bundle.putInt("detailedUsageType", detailedUsageType);
                            bundle.putString("computerType", computerType);
                            intent.putExtra("usageBundle", bundle);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(DetailedUsageActivity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
                    }
                });
                layout.addView(gameButtons[i]);
            }
        }
    }
}