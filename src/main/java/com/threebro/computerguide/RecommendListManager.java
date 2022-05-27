package com.threebro.computerguide;

import android.content.Context;
import android.util.Log;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.Combi.FinalTwo;
import com.threebro.computerguide.Combi.RecommendedSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecommendListManager {
    public ArrayList<RecommendedSet> recommendedSetList;
    public ArrayList<RecommendLaptopSet> recommendLaptopSetList;

    public RecommendListManager() {
        recommendedSetList = new ArrayList<>();
        recommendLaptopSetList = new ArrayList<>();
    }

    public FinalTwo[] getCompareDesktopList(int[] compareIndex) {
        FinalTwo[] estimate = new FinalTwo[2];
        estimate[0] = recommendedSetList.get(compareIndex[0]).getRecommendedSet();
        estimate[1] = recommendedSetList.get(compareIndex[1]).getRecommendedSet();
        return estimate;
    }

    public Laptop[] getCompareLaptopList(int[] compareIndex) {
        Laptop[] model = new Laptop[2];
        model[0] = recommendLaptopSetList.get(compareIndex[0]).getRecommendedLaptop();
        model[1] = recommendLaptopSetList.get(compareIndex[1]).getRecommendedLaptop();
        return model;
    }

    public void addCompareList(FinalTwo estimate) {
        if(checkOverlap(estimate)) {
            recommendedSetList.add(new RecommendedSet("Set " + (recommendedSetList.size() + 1), estimate, recommendedSetList.size()));
        }
        else
            Log.d("RecommendListManager", "Overlap detected");
    }

    public void addLaptopCompareList(Laptop model) {
        if(checkOverlap(model)) {
            recommendLaptopSetList.add(new RecommendLaptopSet("Laptop model " + (recommendLaptopSetList.size() + 1), model, recommendLaptopSetList.size()));
        }
        else
            Log.d("RecommendListManager", "Overlap detected");
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

    private boolean checkOverlap(Laptop model) {
        for(int i = 0; i < recommendLaptopSetList.size(); i++) {
            Laptop laptop = recommendLaptopSetList.get(i).getRecommendedLaptop();
            if(laptop.equals(model)) {
                return false;
            }
        }
        return true;
    }
}
