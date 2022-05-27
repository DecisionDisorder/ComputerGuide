package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class PastModelListActivity extends AppCompatActivity {

    private LinearLayout compareButton;
    private ArrayList<SampleModel> modelSetList;
    private boolean isCompareMode = false;
    private Button startCompareButton;

    private int[] compareIndex = {-1, -1};
    private DBHelper dbHelper;

    static RecommendListManager recommendListManager= new RecommendListManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_model_list);

        dbHelper = new DBHelper(this);

        dbHelper.getResult();

        modelSetList = new ArrayList<>();
        LinearLayout modelContainer = findViewById(R.id.modelContainer);

        for(int i = recommendListManager.recommendedSetList.size() - 1; i >= 0; i--) {
            SampleModel modelSet = new SampleModel(this, i);
            modelSet.setName(recommendListManager.recommendedSetList.get(i).getName());
            modelSet.setSpec(recommendListManager.recommendedSetList.get(i));
            modelSet.setIcon("desktop"); //TODO: laptop도 대응

            modelSetList.add(modelSet);
            modelContainer.addView(modelSet);
        }


        Spinner computerTypeSpinner = findViewById(R.id.computerTypeSpinner);
        String[] computerTypes = getResources().getStringArray(R.array.computer_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, computerTypes);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        computerTypeSpinner.setAdapter(adapter);
        computerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: Destkop, Laptop 전환
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        compareButton = findViewById(R.id.compareButton);
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveCompare(isCompareMode = !isCompareMode);
            }
        });

        startCompareButton = findViewById(R.id.startCompareButton);
        startCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareIntent = new Intent(getApplicationContext(), CompareActivity.class);
                Bundle compareBundle = new Bundle();
                compareBundle.putIntArray("CompareIndex", compareIndex);

                compareIntent.putExtra("CompareBundle", compareBundle);
                startActivity(compareIntent);
            }
        });
    }

    private void setActiveCompare(boolean active) {
        if(active)
            compareButton.setBackgroundColor(getResources().getColor(R.color.active_color));
        else
            compareButton.setBackgroundColor(getResources().getColor(R.color.unactive_color));

        for(int i = 0; i < modelSetList.size(); i++) {
            modelSetList.get(i).setActiveCheckBox(active);
        }
    }

    private int getCheckedAmount() {
        int count = 0;
        for(int i = 0; i < modelSetList.size(); i++) {
            if(modelSetList.get(i).isChecked())
                count++;
        }
        return count;
    }

    public boolean isUnderCheckedLimit() {
        int amount = getCheckedAmount();
        if(amount > 2) {
            return false;
        }
        else if(amount == 2) {
            setCompareStartActive(true);
        }
        else {
            if(startCompareButton.getVisibility() == View.VISIBLE)
                setCompareStartActive(false);
        }
        return true;
    }

    public void setCompareIndex(int index) {
        if(compareIndex[0] == -1) {
            compareIndex[0] = index;
        }
        else if(compareIndex[1] == -1) {
            compareIndex[1] = index;
        }
    }

    public void resetCompareIndex(int index) {
        if(compareIndex[0] == index) {
            compareIndex[0] = -1;
        }
        else if(compareIndex[1] == index) {
            compareIndex[1] = -1;
        }
    }

    private void setCompareStartActive(boolean active) {
        if(active) {
            startCompareButton.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.active_button_anim);
            startCompareButton.startAnimation(anim);
        }
        else {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.unactive_button_anim);
            startCompareButton.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startCompareButton.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }
}