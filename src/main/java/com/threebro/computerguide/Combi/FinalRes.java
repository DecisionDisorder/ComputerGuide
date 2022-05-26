package com.threebro.computerguide.Combi;

//최종 조합을 짜기 위해서


import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Case;
import com.threebro.computerguide.CSV.Cooler;
import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;

import java.util.ArrayList;
import java.util.List;

public class FinalRes {
    /* 여기 까지는 기본 으로 정해둔 애들을 사삼성전자 램8기가 기본 i=4
    삼성 ssd pm9a1 기본 I=1
    Case darkFlask DK200 기본 I=1
    써모랩 트리니니 기본 i=17 */

    private Case ca = new Case();
    private Storage st = new Storage();
    private RAM rm = new RAM();
    private Cooler cl = new Cooler();

    private CPUMB cpu = new CPUMB();//생성 가능한 모든 CPU,MB조합
    private GPUPW gpu = new GPUPW();//생성 가능한 모든 GPU,PW조합

    //private int TotalPrice;// 최종 가격 설정

    public FinalRes(Case ca, Storage st, RAM rm, Cooler cl){// 초기 조건 넣기위해서
        this.ca = ca;
        this.st = st;
        this.rm = rm;
        this.cl = cl;
    }



    public Case getCa() {
        return ca;
    }

    public void setCa(Case ca) {
        this.ca = ca;
    }

    public Storage getSt() {
        return st;
    }

    public void setSt(Storage st) {
        this.st = st;
    }

    public RAM getRm() {
        return rm;
    }

    public void setRm(RAM rm) {
        this.rm = rm;
    }

    public Cooler getCl() {
        return cl;
    }

    public void setCl(Cooler cl) {
        this.cl = cl;
    }

    public CPUMB getCpu() {
        return cpu;
    }

    public void setCpu(CPUMB cpu) {
        this.cpu = cpu;
    }

    public GPUPW getGpu() {
        return gpu;
    }

    public void setGp(GPUPW gp) {
        this.gpu = gp;
    }

    /*public int getTotalPrice() {
        return TotalPrice;
    }*/

    public int getTotalPrice() {
        return getCa().getPrice()+getSt().getPrice() + getRm().getPrice() + getCl().getPrice() + getGpu().getGPU().getPrice()
                + getCpu().getCPU().getPrice() + getCpu().getMbList().get(0).getPrice() + getGpu().getPower().get(0).getPrice()
                + getCa().getPrice();
    }

    /*public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }*/


}
