package com.threebro.computerguide;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailedUsageButton extends LinearLayout {

    // TextView of Detailed usage
    private TextView detailedUsageTextView;

    public DetailedUsageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DetailedUsageButton(Context context) {
        super(context);
        init(context);
    }

    // Inflate and initialize this button layout
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.detailed_usage_button_layout, this, true);

        detailedUsageTextView = findViewById(R.id.detailedUsageTextView);
    }

    // Set detailed usage text with formatted(spannable) string
    public void setText(SpannableString text) {
        detailedUsageTextView.setText(text);
    }
    // Set detailed usage text with normal string
    public void setText(String text) {
        detailedUsageTextView.setText(text);
    }
}
