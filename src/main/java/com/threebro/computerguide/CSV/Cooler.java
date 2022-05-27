package com.threebro.computerguide.CSV;

public class Cooler {
    private String Name;
    private String Type;
    private int Price;
    private int RPM;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getRPM() {
        return RPM;
    }

    public void setRPM(int RPM) {
        this.RPM = RPM;
    }

    @Override
    public String toString() {
        return "Cooler{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Price=" + Price +
                ", RPM=" + RPM +
                '}';
    }
}
