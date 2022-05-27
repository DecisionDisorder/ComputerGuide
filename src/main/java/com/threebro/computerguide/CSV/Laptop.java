package com.threebro.computerguide.CSV;

public class Laptop {
    private String name, company, cpu1,cpu2,cpu3,os,graphic;
    private float display, memory, ssd, weight;
    private int clv, glv, comnum, price,index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getComnum() {
        return comnum;
    }

    public void setComnum(int comnum) {
        this.comnum = comnum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getClv() {
        return clv;
    }

    public void setClv(int clv) {
        this.clv = clv;
    }

    public int getGlv() {
        return glv;
    }

    public void setGlv(int glv) {
        this.glv = glv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCpu1() {
        return cpu1;
    }

    public void setCpu1(String cpu1) {
        this.cpu1 = cpu1;
    }

    public String getCpu2() {
        return cpu2;
    }

    public void setCpu2(String cpu2) {
        this.cpu2 = cpu2;
    }

    public String getCpu3() {
        return cpu3;
    }

    public void setCpu3(String cpu3) {
        this.cpu3 = cpu3;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(String graphic) {
        this.graphic = graphic;
    }

    public float getDisplay() {
        return display;
    }

    public void setDisplay(float display) {
        this.display = display;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }

    public float getSsd() {
        return ssd;
    }

    public void setSsd(float ssd) {
        this.ssd = ssd;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isContainOS() {
        if(os.contains("프리도스"))
            return false;
        else
            return true;
    }
}