package com.example.pestidentificationapp.model;

import com.google.gson.annotations.SerializedName;

public class TestData {

    @SerializedName("fileName")
    private String fileName;
    @SerializedName("fileType")
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
