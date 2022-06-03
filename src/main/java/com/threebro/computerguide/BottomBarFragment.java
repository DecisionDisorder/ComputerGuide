package com.threebro.computerguide;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BottomBarFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate bottom bar fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_bottom_bar, container, false);

        // Get UI views from fragment xml
        LinearLayout homeContainer = view.findViewById(R.id.homeContainer);
        LinearLayout listContainer = view.findViewById(R.id.listContainer);

        TextView homeTextView = view.findViewById(R.id.homeTextView);
        TextView listTextView = view.findViewById(R.id.listTextView);

        ImageView homeImageView = view.findViewById(R.id.homeImageView);
        ImageView listImageView = view.findViewById(R.id.listImageView);

        // If current activity is not ListActivity, set Home button to active color
        if(!isListActivity()) {
            homeTextView.setTextColor(getResources().getColor(R.color.active_color));
            homeImageView.setColorFilter(getResources().getColor(R.color.active_color));
            listTextView.setTextColor(Color.WHITE);
            listImageView.setColorFilter(Color.WHITE);
        }
        // If current activity is ListActivity, set List button to active color
        else {
            listTextView.setTextColor(getResources().getColor(R.color.active_color));
            listImageView.setColorFilter(getResources().getColor(R.color.active_color));
            homeTextView.setTextColor(Color.WHITE);
            homeImageView.setColorFilter(Color.WHITE);
        }

        // Set home button click listener
        homeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If current activity is ListActivity, finish the list activity
                if(isListActivity()) {
                    getActivity().finish();
                }
            }
        });

        // Set List button click listener
        listContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If current activity is not List activity, start ListActivity
                if(!isListActivity()) {
                    Intent listActivity = new Intent(getActivity().getApplicationContext(), PastModelListActivity.class);
                    startActivity(listActivity);
                }
            }
        });

        return view;
    }

    // Check if current activity is ListActivity
    private boolean isListActivity() {
        return getActivity().getClass() == PastModelListActivity.class;
    }
}