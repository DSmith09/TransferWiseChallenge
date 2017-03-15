package com.example.dmsmith.transferwisecodechallenge.model.href;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackHref {
    private String mHref;
    private int mTotal;

    public TrackHref() {}

    public TrackHref(String href, int total) {
        mHref = href;
        mTotal = total;
    }

    @JsonProperty("href")
    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    @JsonProperty("total")
    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }
}
