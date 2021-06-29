package com.example.pestidentificationapp.model;

/**
 * 识别结果类
 */
public class IdentificationResult {
    private String pestUri; //害虫图片存储地址
    private String pestName; //害虫名称
    private String date; //识别日期
    private String time; //识别时间

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
}
