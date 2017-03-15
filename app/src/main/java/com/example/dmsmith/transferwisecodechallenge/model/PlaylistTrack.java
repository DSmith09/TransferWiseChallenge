package com.example.dmsmith.transferwisecodechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaylistTrack {
    private String mAddedAt;
    private User mAddedBy;
    private boolean mIsLocal;
    private Track mTrack;

    public PlaylistTrack() {}

    public PlaylistTrack(String addedAt, User addedBy, boolean isLocal, Track track) {
        mAddedAt = addedAt;
        mAddedBy = addedBy;
        mIsLocal = isLocal;
        mTrack = track;
    }

    @JsonProperty("added_at")
    public String getAddedAt() {
        return mAddedAt;
    }

    public void setAddedAt(String addedAt) {
        mAddedAt = addedAt;
    }

    @JsonProperty("added_by")
    public User getAddedBy() {
        return mAddedBy;
    }

    public void setAddedBy(User addedBy) {
        mAddedBy = addedBy;
    }

    @JsonProperty("is_local")
    public boolean isLocal() {
        return mIsLocal;
    }

    public void setLocal(boolean local) {
        mIsLocal = local;
    }

    @JsonProperty("track")
    public Track getTrack() {
        return mTrack;
    }

    public void setTrack(Track track) {
        mTrack = track;
    }
}
