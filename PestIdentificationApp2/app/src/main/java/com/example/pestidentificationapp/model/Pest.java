package com.example.pestidentificationapp.model;

import com.example.pestidentificationapp.network.NetWorkUtil;
import com.google.gson.annotations.SerializedName;

public class Pest {
    @SerializedName("order")
    private String order;//害虫所属 目
    @SerializedName("family")
    private String family;//害虫所属 科
    @SerializedName("genus")
    private String genus;//害虫所属 属
    @SerializedName("name")
    private String name;//害虫名称
    @SerializedName("latin_name")
    private String latinName;//害虫拉丁学名
    @SerializedName("plant")
    private String plant;//害虫分布植物
    @SerializedName("area")
    private String area;//害虫分布区域
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String picture;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = NetWorkUtil.BaseUrl + picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
