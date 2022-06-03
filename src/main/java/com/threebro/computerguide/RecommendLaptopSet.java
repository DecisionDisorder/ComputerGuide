package com.threebro.computerguide;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.Combi.FinalTwo;

public class RecommendLaptopSet {
    private String name;                // Name of laptop set
    private Laptop recommendedLaptop;   // Laptop model that was recommended
    private int index;                  // Index of this laptop in the Recommended list

    public RecommendLaptopSet(String name, Laptop recommendedLaptop, int index) {
        this.name = name;
        this.recommendedLaptop = recommendedLaptop;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laptop getRecommendedLaptop() {
        return recommendedLaptop;
    }

    public void setRecommendedLaptop(Laptop recommendedLaptop) {
        this.recommendedLaptop = recommendedLaptop;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
