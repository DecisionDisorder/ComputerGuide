package com.threebro.computerguide.CSV;

public class Storage {
    private String Name;
    private String Type;
    private String Pc;
    private String Capacity;
    private int Price;

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

    public String getPc() {
        return Pc;
    }

    public void setPc(String Pc) {
        this.Pc = Pc;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Pcle='" + Pc + '\'' +
                ", Capacity='" + Capacity + '\'' +
                ", Price=" + Price +
                '}';
    }
}
