package com.threebro.computerguide.Combi;

import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.MainBoard;
import com.threebro.computerguide.CSV.Power;

import java.util.ArrayList;
import java.util.List;

public class GPWCom {
    private GPU GPU = new GPU();
    private List<Power> Power = new ArrayList<>();

    public com.threebro.computerguide.CSV.GPU getGPU() {
        return GPU;
    }

    public void setGPU(com.threebro.computerguide.CSV.GPU GPU) {
        this.GPU = GPU;
    }

    public List<com.threebro.computerguide.CSV.Power> getPower() {
        return Power;
    }

    public void setPower(List<com.threebro.computerguide.CSV.Power> power) {
        Power = power;
    }
}
