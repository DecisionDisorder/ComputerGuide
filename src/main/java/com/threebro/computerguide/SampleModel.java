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

import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.text.DecimalFormat;

public class SampleModel extends LinearLayout {

    PastModelListActivity pastModelListActivity;

    public TextView modelTitleTextView;
    public TextView modelSpecTextView;
    public ImageView modelIconImageView;
    public LinearLayout pastModelContainer;
    private CheckBox compareCheckBox;

    public SampleModel(Context context, int index) {
        super(context);

        init(context, index);
    }

    public SampleModel(Context context, AttributeSet attrs, int index) {
        super(context, attrs);

        init(context, index);
    }

    private void init(Context context, int index){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_model_layout, this, true);

        pastModelListActivity = (PastModelListActivity)context;

        pastModelContainer = findViewById(R.id.pastModelContainer);
        modelTitleTextView = findViewById(R.id.modelTitleTextView);
        modelSpecTextView = findViewById(R.id.modelSpecTextView);
        modelIconImageView = findViewById(R.id.modelIconImg);
        compareCheckBox = findViewById(R.id.compareCheckBox);

        pastModelContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EstimateListActivity.class);
                intent.putExtra("ListType", "Past");
                intent.putExtra("index", index);
                context.startActivity(intent);
            }
        });

        compareCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    if(!pastModelListActivity.isUnderCheckedLimit()) {
                        compareCheckBox.setChecked(false);
                        Toast.makeText(context, getResources().getString(R.string.maximum_compare_check), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    pastModelListActivity.isUnderCheckedLimit();
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

    public void setName(String name) {
        modelTitleTextView.setText(name);
    }

    public void setSpec(RecommendedSet set) {
        FinalTwo estimate = set.getRecommendedSet();
        String spec = getSimpleString(estimate);
                /*estimate.getCpu().getName() + "\n" + estimate.getRm().getRamCapacity() + " x" + estimate.getRm().getAmount() + "\n" +
                estimate.getGpu().getName() + "\n" + estimate.getSt().getName() + "\n" + price + "원";*/

        modelSpecTextView.setText(spec);
    }

    private String getSimpleString(FinalTwo estimate) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String price = String.format(formatter.format(estimate.getPrice()));

        String simple = estimate.getCpu().getName() + "\n" + estimate.getRm().getRamCapacity() + " x" + estimate.getRm().getAmount()
                + "\n" + estimate.getGpu().getSeries() + "\n" + estimate.getSt().getCapacity() + "\n" + price + "원";

        return simple;
    }

    public void setIcon(String type) {
        if(type.equals("desktop")) {
            modelIconImageView.setImageDrawable(getResources().getDrawable(R.drawable.desktop));
        }
        else if(type.equals("laptop")) {
            modelIconImageView.setImageDrawable(getResources().getDrawable(R.drawable.laptop));
        }
    }
}
