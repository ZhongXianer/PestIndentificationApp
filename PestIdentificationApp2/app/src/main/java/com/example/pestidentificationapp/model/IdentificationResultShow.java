package com.example.pestidentificationapp.model;

/**
 * 识别结果展示类
 */
public class IdentificationResultShow implements Comparable<IdentificationResultShow> {
    private int order;
    private String pestName;
    private String latinName;
    private String plant;
    private String area;
    private String picture = null;
    private String probability;

    public IdentificationResultShow(String pestUri, String pestName, String latinName, String plant, String area) {
        this.pestName = pestName;
        this.latinName = latinName;
        this.plant = plant;
        this.area = area;
    }

    public IdentificationResultShow(Pest pest, String probability) {
        this.pestName = pest.getName();
        this.latinName = pest.getLatinName();
        this.plant = pest.getPlant();
        this.area = pest.getArea();
        this.picture = pest.getPicture();
        this.probability = probability;
    }

    public IdentificationResultShow(int order, String name, String probability) {
        this.order = order;
        this.pestName = name;
        this.probability = probability;
        this.latinName = "";
        this.plant = "";
        this.area = "";
    }

    public IdentificationResultShow(String name, String probability) {
        this.pestName = name;
        this.probability = probability;
        this.latinName = "";
        this.plant = "";
        this.area = "";
    }

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrder() {
        return Integer.toString(order);
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo(IdentificationResultShow identificationResultShow) {
        return this.order - identificationResultShow.order;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
