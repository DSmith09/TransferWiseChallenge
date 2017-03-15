package com.example.dmsmith.transferwisecodechallenge.model.external_urls;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackUrl {
    private String mKey;

    public TrackUrl() {
    }

    public TrackUrl(String key) {
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
