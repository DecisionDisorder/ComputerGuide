package com.threebro.computerguide.CSV;


public class Power {
    private String Name;
    private String Type;
    private int Specification;
    private String Certification;
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
                ", Specifiaction=" + Specification +
                ", Certification='" + Certification + '\'' +
                ", Price=" + Price +
                '}';
    }
}
