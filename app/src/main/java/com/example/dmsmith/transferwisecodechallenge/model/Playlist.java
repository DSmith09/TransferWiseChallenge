package com.example.dmsmith.transferwisecodechallenge.model;


import com.example.dmsmith.transferwisecodechallenge.model.external_urls.PlaylistUrl;
import com.example.dmsmith.transferwisecodechallenge.model.href.TrackHref;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Playlist {
    private boolean mCollaborative;
    private PlaylistUrl mExternalUrls;
    private String mHref;
    private String mId;
    private Image[] mImages;
    private String mName;
    private User mOwner;
    private boolean mIsPublic;
    private String mSnapshotId;
    private TrackHref mTracks;
    private String mType;
    private String mUri;

    public Playlist() {}

    public Playlist(boolean collaborative, PlaylistUrl externalUrls, String href, String id, Image[] images,
            String name, User owner, boolean isPublic, String snapshotId, TrackHref tracks, String type,
            String uri) {
        mCollaborative = collaborative;
        mExternalUrls = externalUrls;
        mHref = href;
        mId = id;
        mImages = images;
        mName = name;
        mOwner = owner;
        mIsPublic = isPublic;
        mSnapshotId = snapshotId;
        mTracks = tracks;
        mType = type;
        mUri = uri;
    }

    @JsonProperty("collaborative")
    public boolean isCollaborative() {
        return mCollaborative;
    }

    public void setCollaborative(boolean collaborative) {
        mCollaborative = collaborative;
    }

    @JsonProperty("external_urls")
    public PlaylistUrl getExternalUrls() {
        return mExternalUrls;
    }

    public void setExternalUrls(PlaylistUrl externalUrls) {
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

    @JsonProperty("owner")
    public User getOwner() {
        return mOwner;
    }

    public void setOwner(User owner) {
        mOwner = owner;
    }

    @JsonProperty("public")
    public boolean isPublic() {
        return mIsPublic;
    }

    public void setPublic(boolean aPublic) {
        mIsPublic = aPublic;
    }

    @JsonProperty("snapshot_id")
    public String getSnapshotId() {
        return mSnapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        mSnapshotId = snapshotId;
    }

    @JsonProperty("tracks")
    public TrackHref getTracks() {
        return mTracks;
    }

    public void setTracks(TrackHref tracks) {
        mTracks = tracks;
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
