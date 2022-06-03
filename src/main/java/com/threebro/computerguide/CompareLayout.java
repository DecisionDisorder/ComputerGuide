package com.threebro.computerguide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CompareLayout extends LinearLayout {

    private TextView titleTextView; // Comparing component's title
    private TextView nameTextView;  // Comparing component's name

    public CompareLayout(Context context) {
        super(context);

        init(context);
    }

    public CompareLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    // Initialize inflation and view
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compare_layout, this, true);

        titleTextView = findViewById(R.id.compareTitleTextView);
        nameTextView = findViewById(R.id.compareNameTextView);
    }

    // Set component title TextView with parameter title
    public void setComponentTitle(String title) {
        titleTextView.setText(title);
    }

    // Set component name TextView with parameter name
    public void setComponentName(String name) {
        nameTextView.setText(name);
    }

    // Set better color to green and worse color to red
    public void setCompareColor(boolean isBetter) {
        if(isBetter) {
            nameTextView.setTextColor(getResources().getColor(R.color.better_color));
        }
        else {
            nameTextView.setTextColor(getResources().getColor(R.color.worse_color));
        }
    }
}
