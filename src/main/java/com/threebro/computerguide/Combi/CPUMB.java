package com.threebro.computerguide.Combi;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.MainBoard;

import java.util.ArrayList;
import java.util.List;

public class CPUMB {
    private CPU CPU= new CPU();
    private List<MainBoard> mbList = new ArrayList<>();

    public CPU getCPU() {
        return CPU;
    }

    public void setCPU(CPU CPU) {
        this.CPU = CPU;
    }

    public List<MainBoard> getMbList() {
        return mbList;
    }

    public void setMbList(List<MainBoard> mbList) {
        this.mbList = mbList;
    }



}
