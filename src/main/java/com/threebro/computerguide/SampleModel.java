package com.threebro.computerguide;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SampleModel extends LinearLayout {

    public TextView modelTitleTextView;
    public TextView modelSpecTextView;
    public ImageView modelIconImageView;
    public LinearLayout pastModelContainer;

    public SampleModel(Context context, AppCompatActivity activity) {
        super(context);

        init(context, activity);
    }

    public SampleModel(Context context, AttributeSet attrs, AppCompatActivity activity) {
        super(context, attrs);

        init(context, activity);
    }

    private void init(Context context, AppCompatActivity activity){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_model_layout, this, true);

        pastModelContainer = findViewById(R.id.pastModelContainer);
        modelTitleTextView = findViewById(R.id.modelTitleTextView);
        modelSpecTextView = findViewById(R.id.modelSpecTextView);
        modelIconImageView = findViewById(R.id.modelIconImg);

        pastModelContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EstimateListActivity.class);
                context.startActivity(intent);
            }
        });
    }

}
