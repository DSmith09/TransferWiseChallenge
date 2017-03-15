package com.example.dmsmith.transferwisecodechallenge.model;


import com.example.dmsmith.transferwisecodechallenge.model.external_urls.UserUrl;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String mDisplayName;
    private UserUrl mExternalUrls;
    private Followers mFollowers;
    private String mHref;
    private String mId;
    private Image[] mImages;
    private String mType;
    private String mUri;

    public User() {}

    public User(String displayName, UserUrl externalUrls, Followers followers, String href, String id,
                Image[] images, String type, String uri) {
        this.mDisplayName = displayName;
        this.mExternalUrls = externalUrls;
        this.mFollowers = followers;
        this.mHref = href;
        this.mId = id;
        this.mImages = images;
        this.mType = type;
        this.mUri = uri;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    @JsonProperty("external_urls")
    public UserUrl getExternalUrls() {
        return mExternalUrls;
    }

    public void setExternalUrls(UserUrl externalUrls) {
        mExternalUrls = externalUrls;
    }

    @JsonProperty("followers")
    public Followers getFollowers() {
        return mFollowers;
    }

    public void setFollowers(Followers followers) {
        mFollowers = followers;
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

    @JsonProperty("images")
    public Image[] getImages() {
        return mImages;
    }

    public void setImages(Image[] images) {
        mImages = images;
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
