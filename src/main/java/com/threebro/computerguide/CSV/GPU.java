package com.threebro.computerguide.CSV;

public class GPU {
    private String Name;
    private String Series;
    private int Price;
    private int Power;
    private boolean HDMI;
    private boolean DP;
    private boolean DVI;
    private int Priority;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    public boolean isHDMI() {
        return HDMI;
    }

    public void setHDMI(boolean HDMI) {
        this.HDMI = HDMI;
    }

    public boolean isDP() {
        return DP;
    }

    public void setDP(boolean DP) {
        this.DP = DP;
    }

    public boolean isDVI() {
        return DVI;
    }

    public void setDVI(boolean DVI) {
        this.DVI = DVI;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "Name='" + Name + '\'' +
                ", Series='" + Series + '\'' +
                ", Price=" + Price +
                ", Power=" + Power +
                ", HDMI=" + HDMI +
                ", DP=" + DP +
                ", DVI=" + DVI +
                ", Priority=" + Priority +
                '}';
    }
}
