package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    private int mHeight;
    private String mUrl;
    private int mWidth;

    public Image() {}

    public Image(int height, String url, int width) {
        this.mHeight = height;
        this.mUrl = url;
        this.mWidth = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    @JsonProperty("url")
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    @JsonProperty("width")
    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        this.mWidth = width;
    }
}
