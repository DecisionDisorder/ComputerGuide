package com.threebro.computerguide.CSV;

public class Usage {
    public String Usage;
    public int CpuPriority;
    public int GpuPriority;

    public String getUsage() {
        return Usage;
    }

    public void setUsage(String usage) {
        Usage = usage;
    }

    public int getCpuPriority() {
        return CpuPriority;
    }

    public void setCpuPriority(int cpuPriority) {
        CpuPriority = cpuPriority;
    }

    public int getGpuPriority() {
        return GpuPriority;
    }

    public void setGpuPriority(int gpuPriority) {
        GpuPriority = gpuPriority;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "Usage='" + Usage + '\'' +
                ", CpuPriority=" + CpuPriority +
                ", GpuPriority=" + GpuPriority +
                '}';
    }
}
