package com.threebro.computerguide.Combi;

import com.threebro.computerguide.CSV.CPU;
import com.threebro.computerguide.CSV.Case;
import com.threebro.computerguide.CSV.Cooler;
import com.threebro.computerguide.CSV.GPU;
import com.threebro.computerguide.CSV.MainBoard;
import com.threebro.computerguide.CSV.Power;
import com.threebro.computerguide.CSV.RAM;
import com.threebro.computerguide.CSV.Storage;

public class FinalTwo {
    private Case ca = new Case();
    private Storage st = new Storage();
    private RAM rm = new RAM();
    private Cooler cl = new Cooler();

    private CPU cpu = new CPU();
    private GPU gpu = new GPU();
    private MainBoard mb = new MainBoard();
    private Power pw = new Power();
    private int price;

    public int getPrice() {
        return ca.getPrice() + st.getPrice() + cl.getPrice() + rm.getPrice() + gpu.getPrice()
                + cpu.getPrice() + mb.getPrice() + pw.getPrice();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MainBoard getMb() {
        return mb;
    }

    public void setMb(MainBoard mb) {
        this.mb = mb;
    }

    public Power getPw() {
        return pw;
    }

    public void setPw(Power pw) {
        this.pw = pw;
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

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }
}
