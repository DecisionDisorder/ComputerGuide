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

    private ArrayList<String[]> tips;
    private String[] tipsCategories;

    private TextView tipTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_tip, container, false);

        tipTextView = view.findViewById(R.id.tipTextView);

        boolean result = initData(view.getContext());

        if(result) {
            setTip(getAppropriateTip());
        }

        return view;
    }

    private int getActivityIndex (String activityName) {
        for(int i = 0; i < tipsCategories.length; i++) {
            if(tipsCategories[i].equals(activityName))
                return i;
        }
        return -1;
    }

    private String getAppropriateTip() {
        String activityName = getActivity().getClass().getSimpleName();

        int activityIndex = getActivityIndex(activityName);
        if(activityIndex != -1) {
            Random random = new Random();
            int randomContentIndex = random.nextInt(tips.get(activityIndex).length);

            return tips.get(activityIndex)[randomContentIndex];
        }
        return "";
    }

    private void setTip(String tip) {
        tipTextView.setText(tip);
    }

    private boolean initData (Context context) {
        String json = getJsonString(context);
        return parseJson(json);
    }

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

    private boolean parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray tipsArray = jsonObject.getJSONArray("tips");
            tips = new ArrayList<>();
            tipsCategories = new String[tipsArray.length()];

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
            System.out.println(e.getMessage());
            return false;
        }
    }
}