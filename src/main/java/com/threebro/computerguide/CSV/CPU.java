package com.threebro.computerguide.CSV;

public class CPU {
    private String Manufacturer;
    private String Name;
    private int Price;
    private String Socket;
    private String process;
    private String Core;
    private String Thread;
    private String BaseClock;
    private String MaxClock;
    private String L3Cache;
    private String TDP;
    private String MemoryStandard;
    private String MemoryMaxClock;
    private String InternalGraphic;
    private String BundleCooler;
    private String Stock;

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    public String getSocket() {
        return Socket;
    }

    public String getProcess() {
        return process;
    }

    public String getCore() {
        return Core;
    }

    public String getThread() {
        return Thread;
    }

    public String getBaseClock() {
        return BaseClock;
    }

    public String getMaxClock() {
        return MaxClock;
    }

    public String getL3Cache() {
        return L3Cache;
    }

    public String getTDP() {
        return TDP;
    }

    public String getMemoryStandard() {
        return MemoryStandard;
    }

    public String getMemoryMaxClock() {
        return MemoryMaxClock;
    }

    public String getInternalGraphic() {
        return InternalGraphic;
    }

    public String getBundleCooler() {
        return BundleCooler;
    }

    public String getStock() {
        return Stock;
    }

    public void setManufacturer(String manufacturer) {
        this.Manufacturer = manufacturer;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setSocket(String socket) {
        Socket = socket;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setCore(String core) {
        Core = core;
    }

    public void setThread(String thread) {
        Thread = thread;
    }

    public void setBaseClock(String baseClock) {
        BaseClock = baseClock;
    }

    public void setMaxClock(String maxClock) {
        MaxClock = maxClock;
    }

    public void setL3Cache(String l3Cache) {
        L3Cache = l3Cache;
    }

    public void setTDP(String TDP) {
        this.TDP = TDP;
    }

    public void setMemoryStandard(String memoryStandard) {
        MemoryStandard = memoryStandard;
    }

    public void setMemoryMaxClock(String memoryMaxClock) {
        MemoryMaxClock = memoryMaxClock;
    }

    public void setInternalGraphic(String internalGraphic) {
        InternalGraphic = internalGraphic;
    }

    public void setBundleCooler(String bundleCooler) {
        BundleCooler = bundleCooler;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return "test{" +
                "Manufacturer='" + Manufacturer + '\'' +
                ", Name='" + Name + '\'' +
                ", Price='" + Price + '\'' +
                ", Socket='" + Socket + '\'' +
                ", process='" + process + '\'' +
                ", Core='" + Core + '\'' +
                ", Thread='" + Thread + '\'' +
                ", BaseClock='" + BaseClock + '\'' +
                ", MaxClock='" + MaxClock + '\'' +
                ", L3Cache='" + L3Cache + '\'' +
                ", TDP='" + TDP + '\'' +
                ", MemoryStandard='" + MemoryStandard + '\'' +
                ", MemoryMaxClock='" + MemoryMaxClock + '\'' +
                ", InternalGraphic='" + InternalGraphic + '\'' +
                ", BundleCooler='" + BundleCooler + '\'' +
                ", Stock='" + Stock + '\'' +
                '}';
    }
}
