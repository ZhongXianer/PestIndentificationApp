package com.example.pestidentificationapp.model;

import java.util.List;

public class HistoryIdentificationJson {
    private List<HistoryIdentificationResult> resultList;

    public List<HistoryIdentificationResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<HistoryIdentificationResult> resultList) {
        this.resultList = resultList;
    }
}
