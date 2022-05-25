package com.threebro.computerguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Dimension;

public class PcComponent extends LinearLayout {

    private TextView componentTitleTextView;
    private ImageView componentIconImageView;
    private TextView nameAndPriceTextView;

    public PcComponent(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public PcComponent(Context context) {
        super(context);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pc_component_layout, this, true);

        componentTitleTextView = findViewById(R.id.componentTitleTextView);
        componentIconImageView = findViewById(R.id.componentIconImageView);
        nameAndPriceTextView = findViewById(R.id.nameAndPriceTextView);
    }

    public void setTitle(String title) {
        componentTitleTextView.setText(title);
    }

    public void setNameAndPrice(String name, String price) {
        nameAndPriceTextView.setText(name + "\n" + price);
    }

    public void setTextSize(){
        nameAndPriceTextView.setTextSize(Dimension.DP,26);
    }

    public void setIcon(Drawable img) {
        componentIconImageView.setImageDrawable(img);
    }
}
