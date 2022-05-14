package com.threebro.computerguide;

import android.content.Context;
import android.content.Intent;
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

public class SampleModel extends LinearLayout {

    PastModelListActivity pastModelListActivity;

    public TextView modelTitleTextView;
    public TextView modelSpecTextView;
    public ImageView modelIconImageView;
    public LinearLayout pastModelContainer;
    private CheckBox compareCheckBox;

    public SampleModel(Context context, PastModelListActivity activity) {
        super(context);

        init(context, activity);
    }

    public SampleModel(Context context, AttributeSet attrs, PastModelListActivity activity) {
        super(context, attrs);

        init(context, activity);
    }

    private void init(Context context, PastModelListActivity activity){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_model_layout, this, true);

        pastModelListActivity = activity;

        pastModelContainer = findViewById(R.id.pastModelContainer);
        modelTitleTextView = findViewById(R.id.modelTitleTextView);
        modelSpecTextView = findViewById(R.id.modelSpecTextView);
        modelIconImageView = findViewById(R.id.modelIconImg);
        compareCheckBox = findViewById(R.id.compareCheckBox);

        pastModelContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EstimateListActivity.class);
                context.startActivity(intent);
            }
        });

        compareCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    if(!activity.isUnderCheckedLimit()) {
                        compareCheckBox.setChecked(false);
                        Toast.makeText(context, getResources().getString(R.string.maximum_compare_check), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    activity.isUnderCheckedLimit();
                }
            }
        });
    }

    public void setActiveCheckBox(boolean active) {
        if(active)
            compareCheckBox.setVisibility(VISIBLE);
        else
            compareCheckBox.setVisibility(GONE);
    }

    public boolean isChecked() {
        return compareCheckBox.isChecked();
    }
}
