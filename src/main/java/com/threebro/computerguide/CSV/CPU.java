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
    private int PriorityGaming;
    private int PriorityTask;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getSocket() {
        return Socket;
    }

    public void setSocket(String socket) {
        Socket = socket;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCore() {
        return Core;
    }

    public void setCore(String core) {
        Core = core;
    }

    public String getThread() {
        return Thread;
    }

    public void setThread(String thread) {
        Thread = thread;
    }

    public String getBaseClock() {
        return BaseClock;
    }

    public void setBaseClock(String baseClock) {
        BaseClock = baseClock;
    }

    public String getMaxClock() {
        return MaxClock;
    }

    public void setMaxClock(String maxClock) {
        MaxClock = maxClock;
    }

    public String getL3Cache() {
        return L3Cache;
    }

    public void setL3Cache(String l3Cache) {
        L3Cache = l3Cache;
    }

    public String getTDP() {
        return TDP;
    }

    public void setTDP(String TDP) {
        this.TDP = TDP;
    }

    public String getMemoryStandard() {
        return MemoryStandard;
    }

    public void setMemoryStandard(String memoryStandard) {
        MemoryStandard = memoryStandard;
    }

    public String getMemoryMaxClock() {
        return MemoryMaxClock;
    }

    public void setMemoryMaxClock(String memoryMaxClock) {
        MemoryMaxClock = memoryMaxClock;
    }

    public String getInternalGraphic() {
        return InternalGraphic;
    }

    public void setInternalGraphic(String internalGraphic) {
        InternalGraphic = internalGraphic;
    }

    public String getBundleCooler() {
        return BundleCooler;
    }

    public void setBundleCooler(String bundleCooler) {
        BundleCooler = bundleCooler;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public int getPriorityGaming() {
        return PriorityGaming;
    }

    public void setPriorityGaming(int priorityGaming) {
        PriorityGaming = priorityGaming;
    }

    public int getPriorityTask() {
        return PriorityTask;
    }

    public void setPriorityTask(int priorityTask) {
        PriorityTask = priorityTask;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "Manufacturer='" + Manufacturer + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
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
                ", PriorityGaming=" + PriorityGaming +
                ", PriorityTask=" + PriorityTask +
                '}';
    }
}
