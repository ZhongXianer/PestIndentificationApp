package com.example.pestidentificationapp.model;

/**
 * 历史识别类
 */
public class HistoryIdentificationResult {
    private String pestUri; //害虫图片存储地址
    private String pestName; //害虫名称
    private String pestLatinName;//害虫拉丁学名
    private String date; //识别日期
    private String time; //识别时间

    public HistoryIdentificationResult(String pestUri, String pestName, String pestLatinName, String date, String time) {
        this.pestUri = pestUri;
        this.pestName = pestName;
        this.pestLatinName = pestLatinName;
        this.date = date;
        this.time = time;
    }

    public String getPestUri() {
        return pestUri;
    }

    public void setPestUri(String pestUri) {
        this.pestUri = pestUri;
    }

    public String getPestName() {
        return pestName;
    }

    public void setPestName(String pestName) {
        this.pestName = pestName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPestLatinName() {
        return pestLatinName;
    }

    public void setPestLatinName(String pestLatinName) {
        this.pestLatinName = pestLatinName;
    }
}
