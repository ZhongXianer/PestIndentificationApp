package com.example.pestidentificationapp.model.response;

import com.example.pestidentificationapp.model.Pest;

import java.util.ArrayList;
import java.util.List;

public class ResponseLibraryDate {
    private List<Pest> pests = new ArrayList<>();

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }
}
