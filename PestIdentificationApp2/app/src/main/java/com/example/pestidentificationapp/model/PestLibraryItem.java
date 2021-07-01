package com.example.pestidentificationapp.model;

/**
 * 有害昆虫信息库列表展示类
 */
public class PestLibraryItem {
    private String pestName;
    private String pestLatinName;

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getPestLatinName() {
        return pestLatinName;
    }

    public void setPestLatinName(String pestLatinName) {
        this.pestLatinName = pestLatinName;
    }
}
