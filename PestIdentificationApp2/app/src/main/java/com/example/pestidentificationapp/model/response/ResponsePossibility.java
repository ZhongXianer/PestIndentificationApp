package com.example.pestidentificationapp.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponsePossibility {
    @SerializedName("latin_name")
    private String latinName;
    @SerializedName("percentage")
    private double possibility;

    public ResponsePossibility(String latinName, Double possibility) {
        this.latinName = latinName;
        this.possibility = possibility;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public double getPossibility() {
        return possibility;
    }

    public void setPossibility(double possibility) {
        this.possibility = possibility;
    }
}
