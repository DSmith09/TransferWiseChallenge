package com.example.dmsmith.transferwisecodechallenge.model;


import com.example.dmsmith.transferwisecodechallenge.model.external_ids.TrackId;
import com.example.dmsmith.transferwisecodechallenge.model.external_urls.TrackUrl;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {
    private Album mAlbum;
    private Artist[] mArtists;
    private String[] mAvailableMarkets;
    private int mDiscNumber;
    private int mDuration;
    private boolean mIsExplicit;
    private TrackId mExternalIds;
    private TrackUrl mExternalUrls;
    private String mHref;
    private String mId;
    private boolean mIsPlayable;
    private TrackLink mLinkedFrom;
    private String mName;
    private int mPopularity;
    private String mPreviewUrl;
    private int mTrackNumber;
    private String mType;
    private String mUri;

    public Track() {
    }

    public Track(Album album, Artist[] artists, String[] availableMarkets, int discNumber,
            int duration, boolean isExplicit, TrackId externalIds, TrackUrl externalUrls, String href,
            String id, boolean isPlayable, TrackLink linkedFrom, String name, int popularity,
            String previewUrl, int trackNumber, String type, String uri) {
        mAlbum = album;
        mArtists = artists;
        mAvailableMarkets = availableMarkets;
        mDiscNumber = discNumber;
        mDuration = duration;
        mIsExplicit = isExplicit;
        mExternalIds = externalIds;
        mExternalUrls = externalUrls;
        mHref = href;
        mId = id;
        mIsPlayable = isPlayable;
        mLinkedFrom = linkedFrom;
        mName = name;
        mPopularity = popularity;
        mPreviewUrl = previewUrl;
        mTrackNumber = trackNumber;
        mType = type;
        mUri = uri;
    }

    @JsonProperty("album")
    public Album getAlbum() {
        return mAlbum;
    }

    public void setAlbum(Album album) {
        mAlbum = album;
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

    @JsonProperty("disc_number")
    public int getDiscNumber() {
        return mDiscNumber;
    }

    public void setDiscNumber(int discNumber) {
        mDiscNumber = discNumber;
    }

    @JsonProperty("duration_ms")
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JsonProperty("explicit")
    public boolean isExplicit() {
        return mIsExplicit;
    }

    public void setExplicit(boolean explicit) {
        mIsExplicit = explicit;
    }

    @JsonProperty("external_ids")
    public TrackId getExternalIds() {
        return mExternalIds;
    }

    public void setExternalIds(TrackId externalIds) {
        mExternalIds = externalIds;
    }

    @JsonProperty("external_urls")
    public TrackUrl getExternalUrls() {
        return mExternalUrls;
    }

    public void setExternalUrls(TrackUrl externalUrls) {
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

    @JsonProperty("is_playable")
    public boolean isPlayable() {
        return mIsPlayable;
    }

    public void setPlayable(boolean playable) {
        mIsPlayable = playable;
    }

    @JsonProperty("linked_from")
    public TrackLink getLinkedFrom() {
        return mLinkedFrom;
    }

    public void setLinkedFrom(TrackLink linkedFrom) {
        mLinkedFrom = linkedFrom;
    }

    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @JsonProperty("popularity")
    public int getPopularity() {
        return mPopularity;
    }

    public void setPopularity(int popularity) {
        mPopularity = popularity;
    }

    @JsonProperty("preview_url")
    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        mPreviewUrl = previewUrl;
    }

    @JsonProperty("track_number")
    public int getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        mTrackNumber = trackNumber;
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
