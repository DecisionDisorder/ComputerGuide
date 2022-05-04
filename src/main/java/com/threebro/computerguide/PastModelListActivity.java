package com.threebro.computerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class PastModelListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_model_list);

        LinearLayout modelContainer = findViewById(R.id.modelContainer);
        SampleModel modelSet = new SampleModel(this, this);
        modelSet.modelTitleTextView.setText("2022.05.04 Set");

        modelContainer.addView(modelSet);
    }
}