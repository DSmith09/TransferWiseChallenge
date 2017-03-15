package com.example.dmsmith.transferwisecodechallenge.model.paging;


import com.example.dmsmith.transferwisecodechallenge.model.Playlist;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Playlists {
    private String mHref;
    private Playlist[] mPlaylists;
    private int mLimit;
    private String mNext;
    private int mOffset;
    private String mPrevious;
    private int mTotal;

    public Playlists() {}

    public Playlists(String href,
            Playlist[] playlists, int limit, String next, int offset, String previous, int total) {
        mHref = href;
        mPlaylists = playlists;
        mLimit = limit;
        mNext = next;
        mOffset = offset;
        mPrevious = previous;
        mTotal = total;
    }

    @JsonProperty("href")
    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    @JsonProperty("items")
    public Playlist[] getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(Playlist[] playlists) {
        mPlaylists = playlists;
    }

    @JsonProperty("limit")
    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    @JsonProperty("next")
    public String getNext() {
        return mNext;
    }

    public void setNext(String next) {
        mNext = next;
    }

    @JsonProperty("offset")
    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    @JsonProperty("previous")
    public String getPrevious() {
        return mPrevious;
    }

    public void setPrevious(String previous) {
        mPrevious = previous;
    }

    @JsonProperty("total")
    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }
}
