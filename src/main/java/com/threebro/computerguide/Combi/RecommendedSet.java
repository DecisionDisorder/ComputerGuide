package com.threebro.computerguide.Combi;

import java.util.ArrayList;

public class RecommendedSet {
    private String name;
    private FinalTwo recommendedSet;
    private int index;

    public RecommendedSet(String name, FinalTwo recommendedSet, int index) {
        this.name = name;
        this.recommendedSet = recommendedSet;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FinalTwo getRecommendedSet() {
        return recommendedSet;
    }

    public void setRecommendedSet(FinalTwo recommendedSet) {
        this.recommendedSet = recommendedSet;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
