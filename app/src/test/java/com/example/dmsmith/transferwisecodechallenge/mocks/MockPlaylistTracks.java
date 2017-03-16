package com.example.dmsmith.transferwisecodechallenge.mocks;

import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.model.paging.PlaylistTracks;


public class MockPlaylistTracks extends PlaylistTracks {
    private static final String MOCK_HREF = "mock_href";
    private static final PlaylistTrack[] MOCK_PLAYLIST_TRACK = createMockPlaylistTracks();
    private static final int MOCK_LIMIT = 0;
    private static final String MOCK_NEXT = "mock_next";
    private static final int MOCK_OFFSET = 100;
    private static final String MOCK_PREVIOUS = "mock_previous";
    private static final int MOCK_TOTAL = 101;

    public MockPlaylistTracks() {
        this.setHref(MOCK_HREF);
        this.setPlaylistTracks(MOCK_PLAYLIST_TRACK);
        this.setLimit(MOCK_LIMIT);
        this.setNext(MOCK_NEXT);
        this.setOffset(MOCK_OFFSET);
        this.setPrevious(MOCK_PREVIOUS);
        this.setTotal(MOCK_TOTAL);
    }

    private static PlaylistTrack[] createMockPlaylistTracks() {
        return new MockPlaylistTrack().getPlaylistTracks();
    }
}
