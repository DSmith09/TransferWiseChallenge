package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackLink {
    private Url mExternalUrls;
    private String mHref;
    private String mId;
    private String mType;
    private String mUri;

    public TrackLink() {}

    public TrackLink(Url externalUrls, String href, String id, String type, String uri) {
        mExternalUrls = externalUrls;
        mHref = href;
        mId = id;
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
