package com.threebro.computerguide.CSV;

public class MainBoard {
    private String Manufacturer;
    private String Name;
    private String Socket;
    private String Chipset;
    private String Size;
    private String DDR;
    private String MemoryMaxClock;

    private int SlotAmount;
    private String MemoryMaxSize;
    private String Stock;
    private int Price;
    private int CPUMbPrice;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCPUMbPrice() {
        return CPUMbPrice;
    }

    public void setCPUMbPrice(int CPUMbPrice) {
        this.CPUMbPrice = CPUMbPrice;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSocket() {
        return Socket;
    }

    public void setSocket(String socket) {
        Socket = socket;
    }

    public String getChipset() {
        return Chipset;
    }

    public void setChipset(String chipset) {
        Chipset = chipset;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getDDR() {
        return DDR;
    }

    public void setDDR(String DDR) {
        this.DDR = DDR;
    }

    public String getMemoryMaxClock() {
        return MemoryMaxClock;
    }

    public void setMemoryMaxClock(String memoryMaxClock) {
        MemoryMaxClock = memoryMaxClock;
    }

    public int getSlotAmount() {
        return SlotAmount;
    }

    public void setSlotAmount(int slotAmount) {
        SlotAmount = slotAmount;
    }

    public String getMemoryMaxSize() {
        return MemoryMaxSize;
    }

    public void setMemoryMaxSize(String memoryMaxSize) {
        MemoryMaxSize = memoryMaxSize;
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
        return "MainBoard{" +
                "Manufacturer='" + Manufacturer + '\'' +
                ", Name='" + Name + '\'' +
                ", Socket='" + Socket + '\'' +
                ", Chipset='" + Chipset + '\'' +
                ", Size='" + Size + '\'' +
                ", DDR='" + DDR + '\'' +
                ", MemoryMaxClock='" + MemoryMaxClock + '\'' +
                ", SlotAmount='" + SlotAmount + '\'' +
                ", MemoryMaxSize='" + MemoryMaxSize + '\'' +
                ", Stock='" + Stock + '\'' +
                ", Price=" + Price +
                ", CPUMbPrice=" + CPUMbPrice +
                '}';
    }

}
