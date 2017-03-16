package com.example.dmsmith.transferwisecodechallenge.mocks;

import com.example.dmsmith.transferwisecodechallenge.model.Image;
import com.example.dmsmith.transferwisecodechallenge.model.Playlist;
import com.example.dmsmith.transferwisecodechallenge.model.User;
import com.example.dmsmith.transferwisecodechallenge.model.external_urls.PlaylistUrl;
import com.example.dmsmith.transferwisecodechallenge.model.href.TrackHref;


public class MockPlaylist extends Playlist {
    private static final boolean MOCK_COLLABORATIVE = false;
    private static final PlaylistUrl MOCK_EXTERNAL_URLS = null;
    private static final String MOCK_HREF = "mock_href";
    private static final String MOCK_ID = "mock_id";
    private static final Image[] MOCK_IMAGES = null;
    private static final String MOCK_NAME = "dmsmith";
    private static final User MOCK_USER = null;
    private static final boolean MOCK_PUBLIC = true;
    private static final String MOCK_SNAPSHOT = "mock_snapshot";
    private static final TrackHref MOCK_TRACK_HREF = null;
    private static final String MOCK_TYPE = "mock_type";
    private static final String MOCK_URI = "mock_uri";

    public MockPlaylist() {
        this.setCollaborative(MOCK_COLLABORATIVE);
        this.setExternalUrls(MOCK_EXTERNAL_URLS);
        this.setHref(MOCK_HREF);
        this.setId(MOCK_ID);
        this.setImages(MOCK_IMAGES);
        this.setName(MOCK_NAME);
        this.setOwner(MOCK_USER);
        this.setPublic(MOCK_PUBLIC);
        this.setSnapshotId(MOCK_SNAPSHOT);
        this.setTracks(MOCK_TRACK_HREF);
        this.setType(MOCK_TYPE);
        this.setUri(MOCK_URI);
    }

    public Playlist[] getPlaylists() {
        return new MockPlaylist[]{this, this, this};
    }
}
