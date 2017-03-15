package com.example.dmsmith.transferwisecodechallenge.model.external_urls;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUrl {
    private String mKey;

    public UserUrl() {}

    public UserUrl(String key) {
        mKey = key;
    }

    @JsonProperty("spotify")
    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }
}
