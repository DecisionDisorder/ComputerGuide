package com.threebro.computerguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomBarFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_bottom_bar, container, false);

        Button homeButton = view.findViewById(R.id.homeButton);
        Button listButton = view.findViewById(R.id.listButton);

        if(!isListActivity()) {
            homeButton.setTextColor(getResources().getColor(R.color.active_color));
            listButton.setTextColor(Color.WHITE);
        }
        else {
            listButton.setTextColor(getResources().getColor(R.color.active_color));
            homeButton.setTextColor(Color.WHITE);
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isListActivity()) {
                    getActivity().finish();
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isListActivity()) {
                    Intent listActivity = new Intent(getActivity().getApplicationContext(), PastModelListActivity.class);
                    startActivity(listActivity);
                }
            }
        });

        return view;
    }

    private boolean isListActivity() {
        return getActivity().getClass() == PastModelListActivity.class;
    }
}