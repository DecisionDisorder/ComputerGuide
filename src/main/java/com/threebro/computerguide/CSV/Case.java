package com.threebro.computerguide.CSV;

public class Case {
    private String Name;
    private String Type;
    private String Size;
    private int Price;
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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Case{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Size='" + Size + '\'' +
                ", Price=" + Price +
                '}';
    }
}
