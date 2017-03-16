package com.example.dmsmith.transferwisecodechallenge.mocks;


import com.example.dmsmith.transferwisecodechallenge.model.Playlist;
import com.example.dmsmith.transferwisecodechallenge.model.paging.Playlists;

public class MockPlaylists extends Playlists {
    private static final String MOCK_HREF = "mock_href";
    private static final Playlist[] MOCK_PLAYLISTS = createMockPlaylists();
    private static final int MOCK_LIMIT = 0;
    private static final String MOCK_NEXT = "mock_next";
    private static final int MOCK_OFFSET = 100;
    private static final String MOCK_PREVIOUS = "mock_previous";
    private static final int MOCK_TOTAL = 100;

    public MockPlaylists() {
        this.setHref(MOCK_HREF);
        this.setPlaylists(MOCK_PLAYLISTS);
        this.setLimit(MOCK_LIMIT);
        this.setNext(MOCK_NEXT);
        this.setOffset(MOCK_OFFSET);
        this.setPrevious(MOCK_PREVIOUS);
        this.setTotal(MOCK_TOTAL);
    }

    private static Playlist[] createMockPlaylists() {
        return new MockPlaylist().getPlaylists();
    }

}
