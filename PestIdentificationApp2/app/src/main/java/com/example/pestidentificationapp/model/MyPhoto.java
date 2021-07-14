package com.example.pestidentificationapp.model;

public class MyPhoto {
    private String Uri;
    private String name;
    private String path;

    public MyPhoto(){

    }

    public MyPhoto(String Uri, String name,String path) {
        this.Uri = Uri;
        this.name = name;
        this.path = path;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
