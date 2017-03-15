package com.example.dmsmith.transferwisecodechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Followers {
    private String mHref;
    private int mTotal;

    public Followers() {}

    public Followers(String href, int total) {
        this.mHref = href;
        this.mTotal = total;
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
