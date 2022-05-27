package com.threebro.computerguide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CompareLayout extends LinearLayout {

    private TextView titleTextView;
    private TextView nameTextView;

    public CompareLayout(Context context) {
        super(context);

        init(context);
    }

    public CompareLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compare_layout, this, true);

        titleTextView = findViewById(R.id.compareTitleTextView);
        nameTextView = findViewById(R.id.compareNameTextView);
    }

    public void setComponentTitle(String title) {
        titleTextView.setText(title);
    }

    public void setComponentName(String name) {
        nameTextView.setText(name);
    }
}
