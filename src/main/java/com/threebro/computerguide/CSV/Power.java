package com.threebro.computerguide.CSV;


public class Power {
    private String Name;
    private String Type;
    private int Specification;
    private String Certification;
    private int Price;
    private int GPWPrice;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGPWPrice() {
        return GPWPrice;
    }

    public void setGPWPrice(int GPWPrice) {
        this.GPWPrice = GPWPrice;
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

    public int getSpecification() {
        return Specification;
    }

    public void setSpecification(int specifiaction) {
        Specification = specifiaction;
    }

    public String getCertification() {
        return Certification;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Power{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", Specification=" + Specification +
                ", Certification='" + Certification + '\'' +
                ", Price=" + Price +
                ", GPWPrice=" + GPWPrice +
                '}';
    }
}
