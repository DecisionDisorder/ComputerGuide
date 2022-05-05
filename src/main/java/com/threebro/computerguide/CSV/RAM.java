package com.threebro.computerguide.CSV;

public class RAM {
    private String Name;
    private String RamCapacity;
    private String MemoryStandard;
    private String MemoryClock;
    private String Stock;
    private int Price;

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
        return Price;
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