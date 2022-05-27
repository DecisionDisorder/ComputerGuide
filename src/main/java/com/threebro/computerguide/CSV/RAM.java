package com.threebro.computerguide.CSV;

public class RAM {
    private String Name;
    private String RamCapacity;
    private String MemoryStandard;
    private String MemoryClock;
    private String Stock;
    private int Price;
    private int amount = 2;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRamCapacity() {
        return RamCapacity;
    }

    public void setRamCapacity(String ramCapacity) {
        RamCapacity = ramCapacity;
    }

    public String getMemoryStandard() {
        return MemoryStandard;
    }

    public void setMemoryStandard(String memoryStandard) {
        MemoryStandard = memoryStandard;
    }

    public String getMemoryClock() {
        return MemoryClock;
    }

    public void setMemoryClock(String memoryClock) {
        MemoryClock = memoryClock;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public int getPrice() {
        return Price * amount;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "Name='" + Name + '\'' +
                ", RamCapacity='" + RamCapacity + '\'' +
                ", MemoryStandard='" + MemoryStandard + '\'' +
                ", MemoryClock='" + MemoryClock + '\'' +
                ", Stock='" + Stock + '\'' +
                ", Price=" + Price +
                '}';
    }
}
