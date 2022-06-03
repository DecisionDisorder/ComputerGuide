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
import android.widget.TextView;

import java.util.ArrayList;

public class PastModelListActivity extends AppCompatActivity {

    private LinearLayout compareButton;                 // Button layout to enable/disable comparison mode
    private ArrayList<SampleModel> estimateSetList;     // Desktop SampleModel of estimate set Array List
    private ArrayList<SampleModel> laptopSetList;       // Laptop SampleModel of model set Array List
    private boolean isCompareMode = false;              // Indicates if compare mode
    private Button startCompareButton;                  // Starting compare two model button

    private int[] compareIndex = {-1, -1};              // Index to be compared
    private String[] compareSetNames = new String[2];   // Comparison Set Names
    private DBHelper dbHelper;                          // Internal database helper (for loading)

    // Recommended Laptop and Desktop List Manager
    static RecommendListManager recommendListManager = new RecommendListManager();

    private LinearLayout desktopModelContainer;         // Containers to keep desktop estimate list
    private LinearLayout laptopModelContainer;          // Containers to keep laptop model list

    private TextView emptyDesktopText;                  // Text to indicate that the desktop list is empty
    private TextView emptyLaptopText;                   // Text to indicate that the laptop list is empty

    private ComputerType computerType;                  // The type of computer list currently displaying.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_model_list);

        // Initialize internal database helper
        dbHelper = new DBHelper(this);

        // Load from internal database
        dbHelper.getResult();
        dbHelper.getLaptopResult();

        // Initialize list and set UI views
        estimateSetList = new ArrayList<>();
        laptopSetList = new ArrayList<>();
        desktopModelContainer = findViewById(R.id.desktopModelContainer);
        laptopModelContainer = findViewById(R.id.laptopModelContainer);
        emptyDesktopText = findViewById(R.id.emptyDesktopTextView);
        emptyLaptopText = findViewById(R.id.emptyLaptopTextView);

        // Set desktop estimate list with name, spec, and icon
        for(int i = recommendListManager.recommendedSetList.size() - 1; i >= 0; i--) {
            SampleModel modelSet = new SampleModel(this, i);
            modelSet.setName(recommendListManager.recommendedSetList.get(i).getName());
            modelSet.setSpec(recommendListManager.recommendedSetList.get(i));
            modelSet.setIcon("desktop");
            modelSet.setType(ComputerType.DESKTOP);
            estimateSetList.add(modelSet);
            desktopModelContainer.addView(modelSet);
        }

        // Set laptop model list with name, spec, and icon
        for(int i = recommendListManager.recommendLaptopSetList.size() - 1; i >= 0; i--) {
            SampleModel modelSet = new SampleModel(this, i);
            modelSet.setName(recommendListManager.recommendLaptopSetList.get(i).getName());
            modelSet.setSpec(recommendListManager.recommendLaptopSetList.get(i));
            modelSet.setIcon("laptop");
            modelSet.setType(ComputerType.LAPTOP);
            laptopSetList.add(modelSet);
            laptopModelContainer.addView(modelSet);
        }
        // Set Empty Text
        setEmptyText(computerType);

        // Set dropdown for select desktop estimate list or laptop model list
        Spinner computerTypeSpinner = findViewById(R.id.computerTypeSpinner);
        String[] computerTypes = getResources().getStringArray(R.array.computer_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, computerTypes);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        computerTypeSpinner.setAdapter(adapter);
        computerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // If desktop is selected, active desktop container and disable laptop container
                if(i == 0) {
                    desktopModelContainer.setVisibility(View.VISIBLE);
                    laptopModelContainer.setVisibility(View.GONE);
                    computerType = ComputerType.DESKTOP;
                    resetCheckBoxes();
                }
                // If laptop is selected, active laptop container and disable desktop container
                else {
                    desktopModelContainer.setVisibility(View.GONE);
                    laptopModelContainer.setVisibility(View.VISIBLE);
                    computerType = ComputerType.LAPTOP;
                    resetCheckBoxes();
                }
                // Set Empty Text
                setEmptyText(computerType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Set compare mode button listener
        compareButton = findViewById(R.id.compareButton);
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveCompare(isCompareMode = !isCompareMode);
            }
        });

        // Set start comparing button listener
        startCompareButton = findViewById(R.id.startCompareButton);
        startCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set Bundle data to be sent to comparison activity
                Intent compareIntent = new Intent(getApplicationContext(), CompareActivity.class);
                Bundle compareBundle = new Bundle();
                compareBundle.putIntArray("CompareIndex", compareIndex);
                compareBundle.putString("ComputerType", computerType.toString());
                compareBundle.putStringArray("SetNames", compareSetNames);
                compareIntent.putExtra("CompareBundle", compareBundle);
                startActivity(compareIntent);
            }
        });
    }

    // If the desktop or laptop list is empty, expose the empty message.
    private void setEmptyText(ComputerType type) {
        if(type == ComputerType.DESKTOP) {
            if (estimateSetList.size() == 0)
                emptyDesktopText.setVisibility(View.VISIBLE);
            else
                emptyDesktopText.setVisibility(View.GONE);
            emptyLaptopText.setVisibility(View.GONE);
        }
        else {
            emptyDesktopText.setVisibility(View.GONE);
            if(laptopSetList.size() == 0)
                emptyLaptopText.setVisibility(View.VISIBLE);
            else
                emptyLaptopText.setVisibility(View.GONE);
        }
    }

    // Disable all check box
    private void resetCheckBoxes() {
        for(int i = 0; i < estimateSetList.size(); i++) {
            estimateSetList.get(i).setCompareCheckBox(false);
        }
        for(int i = 0; i < laptopSetList.size(); i++) {
            laptopSetList.get(i).setCompareCheckBox(false);
        }
    }

    // Enable/Disable check boxes to check for comparison targets
    private void setActiveCompare(boolean active) {
        if(active)
            compareButton.setBackgroundColor(getResources().getColor(R.color.active_color));
        else
            compareButton.setBackgroundColor(getResources().getColor(R.color.unactive_color));

        for (int i = 0; i < estimateSetList.size(); i++) {
            estimateSetList.get(i).setActiveCheckBox(active);
        }
        for (int i = 0; i < laptopSetList.size(); i++) {
            laptopSetList.get(i).setActiveCheckBox(active);
        }
    }

    // Returns the number of check boxes checked
    private int getCheckedAmount() {
        int count = 0;
        if(computerType == ComputerType.DESKTOP) {
            for (int i = 0; i < estimateSetList.size(); i++) {
                if (estimateSetList.get(i).isChecked())
                    count++;
            }
        }
        else {
            for (int i = 0; i < laptopSetList.size(); i++) {
                if (laptopSetList.get(i).isChecked())
                    count++;
            }
        }
        return count;
    }

    // Check that the number checked is not more than 2
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

    // Specifying a comparison target index
    public void setCompareIndex(int index) {
        if(compareIndex[0] == -1) {
            compareIndex[0] = index;
        }
        else if(compareIndex[1] == -1) {
            compareIndex[1] = index;
        }
    }

    // Reset a comparison target index
    public void resetCompareIndex(int index) {
        if(compareIndex[0] == index) {
            compareIndex[0] = -1;
        }
        else if(compareIndex[1] == index) {
            compareIndex[1] = -1;
        }
    }

    // Set comparison target set name
    public void setSelectedSetNames(String name) {
        if(compareIndex[0] == -1) {
            compareSetNames[0] = name;
        }
        else if(compareIndex[1] == -1) {
            compareSetNames[1] = name;
        }
    }

    // Set Starting compare button active/unactive
    private void setCompareStartActive(boolean active) {
        if(active) {
            // If active, show button and play animation effect.
            startCompareButton.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.active_button_anim);
            startCompareButton.startAnimation(anim);
        }
        else {
            // If unactive, play disabling animation effect and disable button.
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.unactive_button_anim);
            startCompareButton.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startCompareButton.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
    }
}