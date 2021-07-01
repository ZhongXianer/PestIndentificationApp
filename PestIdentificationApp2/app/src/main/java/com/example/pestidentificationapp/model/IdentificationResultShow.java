package com.example.pestidentificationapp.model;

/**
 * 识别结果展示类
 */
public class IdentificationResultShow {
    private String pestUri;
    private String pestName;
    private String latinName;
    private String plant;
    private String area;

    public IdentificationResultShow(String pestUri, String pestName, String latinName, String plant, String area) {
        this.pestUri = pestUri;
        this.pestName = pestName;
        this.latinName = latinName;
        this.plant = plant;
        this.area = area;
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

    public String getPestUri() {
        return pestUri;
    }

    public void setPestUri(String pestUri) {
        this.pestUri = pestUri;
    }
}
