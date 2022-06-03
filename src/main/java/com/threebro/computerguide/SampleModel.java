package com.threebro.computerguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.text.DecimalFormat;

public class SampleModel extends LinearLayout {

    PastModelListActivity pastModelListActivity;    // Recommended List activity (Activity that created this class)

    public TextView modelTitleTextView;             // Model title text view
    public TextView modelSpecTextView;              // Model simple specification text view
    public ImageView modelIconImageView;            // Model icon Image view
    public LinearLayout pastModelContainer;         // Root layout container
    private CheckBox compareCheckBox;               // Compare check box
    private ComputerType type;                      // Current model's computer type

    public SampleModel(Context context, int index) {
        super(context);

        init(context, index);
    }

    public SampleModel(Context context, AttributeSet attrs, int index) {
        super(context, attrs);

        init(context, index);
    }

    // Initialize this sample model
    private void init(Context context, int index){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_model_layout, this, true);

        pastModelListActivity = (PastModelListActivity)context;

        // Get UI views from activity xml
        pastModelContainer = findViewById(R.id.pastModelContainer);
        modelTitleTextView = findViewById(R.id.modelTitleTextView);
        modelSpecTextView = findViewById(R.id.modelSpecTextView);
        modelIconImageView = findViewById(R.id.modelIconImg);
        compareCheckBox = findViewById(R.id.compareCheckBox);

        // Set button listener to start detailed estimate activity
        pastModelContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EstimateListActivity.class);
                intent.putExtra("ListType", "Past");
                intent.putExtra("index", index);
                Bundle bundle = new Bundle();
                bundle.putString("computerType", type.toString());
                intent.putExtra("productBundle", bundle);
                context.startActivity(intent);
            }
        });

        // Set checkbox event listener that check if checked amount is under limit and enabling start compare button
        compareCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    if(!pastModelListActivity.isUnderCheckedLimit()) {
                        compareCheckBox.setChecked(false);
                        Toast.makeText(context, getResources().getString(R.string.maximum_compare_check), Toast.LENGTH_LONG).show();
                    }
                    else {
                        pastModelListActivity.setSelectedSetNames(modelTitleTextView.getText().toString());
                        pastModelListActivity.setCompareIndex(index);
                    }
                }
                else {
                    pastModelListActivity.isUnderCheckedLimit();
                    pastModelListActivity.resetCompareIndex(index);
                }
            }
        });
    }

    // Set CheckBox active/unactive
    public void setActiveCheckBox(boolean active) {
        if(active)
            compareCheckBox.setVisibility(VISIBLE);
        else
            compareCheckBox.setVisibility(GONE);
    }

    // Get if this checkbox is checked
    public boolean isChecked() {
        return compareCheckBox.isChecked();
    }

    // Set this set's name
    public void setName(String name) {
        modelTitleTextView.setText(name);
    }

    // set this set's desktop simple spec
    public void setSpec(RecommendedSet set) {
        FinalTwo estimate = set.getRecommendedSet();
        String spec = DesktopSet.getSimpleString(estimate);

        modelSpecTextView.setText(spec);
    }
    // set this set's laptop simple spec
    public void setSpec(RecommendLaptopSet set) {
        Laptop model = set.getRecommendedLaptop();
        String spec = LaptopSet.getSimpleString(model);

        modelSpecTextView.setText(spec);
    }

    // Set checkbox checked/unchecked
    public void setCompareCheckBox(boolean active) {
        compareCheckBox.setChecked(active);
    }

    // Set icon desktop or laptop
    public void setIcon(String type) {
        if(type.equals("desktop")) {
            modelIconImageView.setImageDrawable(getResources().getDrawable(R.drawable.desktop));
        }
        else if(type.equals("laptop")) {
            modelIconImageView.setImageDrawable(getResources().getDrawable(R.drawable.laptop));
        }
    }

    // Set computer type to desktop or laptop
    public void setType(ComputerType type) {
        this.type = type;
    }
}
