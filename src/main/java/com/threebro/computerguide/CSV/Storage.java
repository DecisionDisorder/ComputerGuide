package com.threebro.computerguide.CSV;

public class Storage {
    private String Name;
    private String Type;
    private String Pc;
    private String Capacity;
    private int Price;
    private int Amount = 1;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
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
        return Price * Amount;
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

    /*public boolean isLeftBetterStorage(Storage storageLeft, Storage storageRight) {
        if(storageLeft.getType().contains("SSD") && storageRight.getType().contains("SSD")) {
            if(storageLeft.getCapacity() > storageRight.getCapacity())
                return true;
            else if()
        }
        else if(storageLeft.getType().contains("SSD")) {

        }
        else if(storageRight.getType().contains("SSD")) {

        }
        else {

        }
    }*/
}
