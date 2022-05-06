package com.threebro.computerguide.Combi;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.MainBoard;

import java.util.ArrayList;
import java.util.List;

public class CpuMbCom {
    private CPU CPUList = new CPU();
    private List<MainBoard> mbList = new ArrayList<>();

    public CPU getCPUList() {
        return CPUList;
    }

    public void setCPUList(CPU CPUList) {
        this.CPUList = CPUList;
    }

    public List<MainBoard> getMbList() {
        return mbList;
    }

    public void setMbList(List<MainBoard> mbList) {
        this.mbList = mbList;
    }



}
