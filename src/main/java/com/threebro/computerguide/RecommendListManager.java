package com.threebro.computerguide;

import android.util.Log;

import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecommendListManager {
    public ArrayList<RecommendedSet> recommendedSetList;

    public RecommendListManager() {
        recommendedSetList = new ArrayList<>();
    }

    public FinalTwo[] getCompareList(int leftIndex, int rightIndex) {
        FinalTwo[] estimate = new FinalTwo[2];
        estimate[0] = recommendedSetList.get(leftIndex).getRecommendedSet();
        estimate[1] = recommendedSetList.get(rightIndex).getRecommendedSet();
        return estimate;
    }

    public void addCompareList(FinalTwo estimate) {
        if(checkOverlap(estimate)) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            recommendedSetList.add(new RecommendedSet(format.format(date) + " Set", estimate, recommendedSetList.size()));
        }
        else
            Log.d("Compare Manager", "Overlap detected");
    }

    private boolean checkOverlap(FinalTwo estimate) {
        for (int i = 0; i < recommendedSetList.size(); i++) {
            FinalTwo est = recommendedSetList.get(i).getRecommendedSet();
            if(est.equals(estimate)) {
                return false;
            }
        }
        return true;
    }
}
