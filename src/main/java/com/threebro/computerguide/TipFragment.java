package com.threebro.computerguide;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class TipFragment extends Fragment {

    private ArrayList<String[]> tips;   // Tips string array list
    private String[] tipsCategories;    // Tip categories

    private TextView tipTextView;       // Tip text view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_tip, container, false);

        // Get textview from fragment xml
        tipTextView = view.findViewById(R.id.tipTextView);

        // Load tip data from json and get result
        boolean result = initData(view.getContext());

        // If loading is successful, show the appropriate tip
        if(result) {
            setTip(getAppropriateTip());
        }

        return view;
    }

    // Get index of activity with name
    private int getActivityIndex (String activityName) {
        for(int i = 0; i < tipsCategories.length; i++) {
            if(tipsCategories[i].equals(activityName))
                return i;
        }
        return -1;
    }

    // Get appropriate tip according to current activity
    private String getAppropriateTip() {
        // Get current activity's name
        String activityName = getActivity().getClass().getSimpleName();

        // return current activity's index and get tip of that activity
        int activityIndex = getActivityIndex(activityName);
        if(activityIndex != -1) {
            Random random = new Random();
            int randomContentIndex = random.nextInt(tips.get(activityIndex).length);

            return tips.get(activityIndex)[randomContentIndex];
        }
        return "";
    }

    // Set tip textview
    private void setTip(String tip) {
        tipTextView.setText(tip);
    }

    // Load json data, parse, and return if successful
    private boolean initData (Context context) {
        String json = getJsonString(context);
        return parseJson(json);
    }

    // Read json data from json file of asset directory
    private String getJsonString(Context context) {
        String json = "";

        try {
            InputStream is = context.getAssets().open("tip.json");

            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return json;
    }

    // Parse json string to tip array list
    private boolean parseJson(String json) {
        try {
            // Get json array with json library
            JSONObject jsonObject = new JSONObject(json);
            JSONArray tipsArray = jsonObject.getJSONArray("tips");
            // Initialize array list and array
            tips = new ArrayList<>();
            tipsCategories = new String[tipsArray.length()];

            // Read data from json array object and add to tip list
            for(int i = 0; i < tipsArray.length(); i++)
            {
                JSONObject tipOnActivityObj = tipsArray.getJSONObject(i);

                tipsCategories[i] = tipOnActivityObj.getString("activity");

                JSONArray tipsOnActivityArray = tipOnActivityObj.getJSONArray("tip_contents");
                String[] tipsContents = new String[tipsOnActivityArray.length()];
                for(int j = 0; j < tipsOnActivityArray.length(); j++) {
                    JSONObject tipObj = tipsOnActivityArray.getJSONObject(j);
                    tipsContents[j] = tipObj.getString("content");
                }
                tips.add(tipsContents);
            }
            return true;

        } catch (JSONException e) {
            // If there's any error, return false
            System.out.println(e.getMessage());
            return false;
        }
    }
}