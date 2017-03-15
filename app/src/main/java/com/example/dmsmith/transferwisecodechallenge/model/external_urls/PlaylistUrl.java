package com.example.dmsmith.transferwisecodechallenge.model.external_urls;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistUrl {
    private String mKey;

    public PlaylistUrl() {}

    public PlaylistUrl(String key) {
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
