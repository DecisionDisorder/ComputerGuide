package com.threebro.computerguide;

import com.threebro.computerguide.CSV.Laptop;
import com.threebro.computerguide.Combi.FinalTwo;

public class RecommendLaptopSet {
    private String name;
    private Laptop recommendedLaptop;
    private int index;

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
