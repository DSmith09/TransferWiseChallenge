package com.example.dmsmith.transferwisecodechallenge.model.external_ids;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackId {
    private String mKey;

    public TrackId() {
    }

    public TrackId(String key) {
        mKey = key;
    }

    @JsonProperty("isrc")
    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }
}
