package com.example.dmsmith.transferwisecodechallenge.mocks;

import com.example.dmsmith.transferwisecodechallenge.model.Album;
import com.example.dmsmith.transferwisecodechallenge.model.Artist;
import com.example.dmsmith.transferwisecodechallenge.model.Track;
import com.example.dmsmith.transferwisecodechallenge.model.TrackLink;
import com.example.dmsmith.transferwisecodechallenge.model.external_ids.TrackId;
import com.example.dmsmith.transferwisecodechallenge.model.external_urls.TrackUrl;

public class MockTrack extends Track {
    private static final Album MOCK_ALBUM = null;
    private static final Artist[] MOCK_ARTISTS = null;
    private static final String[] MOCK_MARKETS = null;
    private static final int MOCK_DISC_NUMBER = 1;
    private static final int MOCK_DURATION = 12000;
    private static final boolean MOCK_IS_EXPLICIT = true;
    private static final TrackId MOCK_TRACK_ID = null;
    private static final TrackUrl MOCK_TRACK_URL = null;
    private static final String MOCK_HREF = "mock_href";
    private static final String MOCK_ID = "mock_id";
    private static final boolean MOCK_IS_PLAYABLE = true;
    private static final TrackLink MOCK_LINKED_FROM = null;
    private static final String MOCK_NAME = "dmsmith";
    private static final int MOCK_POPULARITY = 1;
    private static final String MOCK_PREVIEWURL = "test";
    private static final int MOCK_TRACK_NUMBER = 2;
    private static final String MOCK_TYPE = "mock_type";
    private static final String MOCK_URI = "mock_uri";

    public MockTrack() {
        this.setAlbum(MOCK_ALBUM);
        this.setArtists(MOCK_ARTISTS);
        this.setAvailableMarkets(MOCK_MARKETS);
        this.setDiscNumber(MOCK_DISC_NUMBER);
        this.setDuration(MOCK_DURATION);
        this.setExplicit(MOCK_IS_EXPLICIT);
        this.setExternalIds(MOCK_TRACK_ID);
        this.setExternalUrls(MOCK_TRACK_URL);
        this.setHref(MOCK_HREF);
        this.setId(MOCK_ID);
        this.setPlayable(MOCK_IS_PLAYABLE);
        this.setLinkedFrom(MOCK_LINKED_FROM);
        this.setName(MOCK_NAME);
        this.setPopularity(MOCK_POPULARITY);
        this.setPreviewUrl(MOCK_PREVIEWURL);
        this.setTrackNumber(MOCK_TRACK_NUMBER);
        this.setType(MOCK_TYPE);
        this.setUri(MOCK_URI);
    }
}
