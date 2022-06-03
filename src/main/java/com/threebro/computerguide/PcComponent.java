package com.threebro.computerguide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PcComponent extends LinearLayout {

    private EstimateListActivity estimateListActivity;

    private TextView componentTitleTextView;
    private ImageView componentIconImageView;
    private TextView nameAndPriceTextView;
    private LinearLayout amountSetContainer;

    private TextView amountTextView;

    private int maxAmount;

    public PcComponent(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, 0);
    }

    public PcComponent(Context context) {
        super(context);

        init(context, 0);
    }

    public PcComponent(Context context, int maxAmount) {
        super(context);

        init(context, maxAmount);
    }

    private void init(Context context, int maxAmount) {
        estimateListActivity = (EstimateListActivity) context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pc_component_layout, this, true);

        componentTitleTextView = findViewById(R.id.componentTitleTextView);
        componentIconImageView = findViewById(R.id.componentIconImageView);
        nameAndPriceTextView = findViewById(R.id.nameAndPriceTextView);
        amountSetContainer = findViewById(R.id.amountSetContainer);
        amountTextView = findViewById(R.id.amountTextView);

        this.maxAmount = maxAmount;

        Button addAmountButton = findViewById(R.id.amountAddButton);
        addAmountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addAmount();
            }
        });

        Button subtractAmountButton = findViewById(R.id.amountSubtractButton);
        subtractAmountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                subtractAmount();
            }
        });
    }

    public void setActiveAmountSet(boolean active, int amount) {
        if(active) {
            amountSetContainer.setVisibility(VISIBLE);
            setAmountTextView(amount);
        }
        else {
            amountSetContainer.setVisibility(GONE);
        }
    }

    public void setTitle(String title) {
        componentTitleTextView.setText(title);
    }

    public void setNameAndPrice(String name, String price) {
        if(price.equals(""))
            nameAndPriceTextView.setText(name);
        else
            nameAndPriceTextView.setText(name + "\n" + price);
    }

    public void setTextSize() {
        nameAndPriceTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
    }

    public void setIcon(Drawable img) {
        componentIconImageView.setImageDrawable(img);
    }

    private void addAmount() {
        if(estimateListActivity.getComponentAmount(EstimateListActivity.PcComponentType.RAM.ordinal()) < maxAmount)
            estimateListActivity.addComponentAmount(EstimateListActivity.PcComponentType.RAM.ordinal(), 1);
    }

    private void subtractAmount() {
        if(estimateListActivity.getComponentAmount(EstimateListActivity.PcComponentType.RAM.ordinal()) > 1)
            estimateListActivity.addComponentAmount(EstimateListActivity.PcComponentType.RAM.ordinal(), -1);
    }

    public void setAmountTextView(int amount) {
        amountTextView.setText("" + amount);
    }
}
