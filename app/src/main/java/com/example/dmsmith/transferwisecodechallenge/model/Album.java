package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

// Simplified Album Object
public class Album {
    private String mAlbumType;
    private Artist[] mArtists;
    private String[] mAvailableMarkets;
    private Id mExternalIds;
    private Url mExternalUrls;
    private String mHref;
    private String mId;
    private Image[] mImages;
    private String mName;
    private String mType;
    private String mUri;

    public Album() {}

    public Album(String albumType, Artist[] artists, String[] availableMarkets,
            Id externalIds, Url externalUrls, String href, String id,
            Image[] images, String name, String type, String uri) {
        mAlbumType = albumType;
        mArtists = artists;
        mAvailableMarkets = availableMarkets;
        mExternalIds = externalIds;
        mExternalUrls = externalUrls;
        mHref = href;
        mId = id;
        mImages = images;
        mName = name;
        mType = type;
        mUri = uri;
    }

    @JsonProperty("album_type")
    public String getAlbumType() {
        return mAlbumType;
    }

    public void setAlbumType(String albumType) {
        mAlbumType = albumType;
    }

    @JsonProperty("artists")
    public Artist[] getArtists() {
        return mArtists;
    }

    public void setArtists(Artist[] artists) {
        mArtists = artists;
    }

    @JsonProperty("available_markets")
    public String[] getAvailableMarkets() {
        return mAvailableMarkets;
    }

    public void setAvailableMarkets(String[] availableMarkets) {
        mAvailableMarkets = availableMarkets;
    }

    @JsonProperty("external_ids")
    public Id getExternalIds() {
        return mExternalIds;
    }

    public void setExternalIds(Id externalIds) {
        mExternalIds = externalIds;
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

    @JsonProperty("images")
    public Image[] getImages() {
        return mImages;
    }

    public void setImages(Image[] images) {
        mImages = images;
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
