package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Url {
    private String mKey;
    private String mValue;

    public Url() {}

    public Url(String key, String value) {
        this.mKey = key;
        this.mValue = value;
    }

    @JsonProperty("spotify")
    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    @JsonProperty("value")
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        this.mValue = value;
    }
}
