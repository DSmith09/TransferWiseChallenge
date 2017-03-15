package com.example.dmsmith.transferwisecodechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// Simplified Artist Object
public class Artist {
    private Url mExternalUrls;
    private String mHref;
    private String mId;
    private String mName;
    private String mType;
    private String mUri;

    public Artist() {}

    public Artist(Url externalUrls, String href, String id, String name, String type,
            String uri) {
        mExternalUrls = externalUrls;
        mHref = href;
        mId = id;
        mName = name;
        mType = type;
        mUri = uri;
    }

    @JsonProperty("external_urls")
    public Url getExternalUrls() {
        return mExternalUrls;
    }

    public void setExternalUrls(Url externalUrls) {
        mExternalUrls = externalUrls;
    }

    @JsonProperty("href")
    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    @JsonProperty("id")
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @JsonProperty("type")
    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @JsonProperty("uri")
    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }
}
