package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Id {
    private String mKey;
    private String mValue;

    public Id() {}

    public Id(String key, String value) {
        mKey = key;
        mValue = value;
    }

    @JsonProperty("key")
    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    @JsonProperty("value")
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
