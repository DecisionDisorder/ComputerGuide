package com.threebro.computerguide.Combi;

import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.Power;

import java.util.ArrayList;
import java.util.List;

public class GPUPW {
    private GPU GPU = new GPU();
    private List<Power> Power = new ArrayList<>();

    public GPU getGPU() {
        return GPU;
    }

    public void setGPU(GPU GPU) {
        this.GPU = GPU;
    }

    public List<Power> getPower() {
        return Power;
    }

    public void setPower(List<Power> power) {
        Power = power;
    }
}
