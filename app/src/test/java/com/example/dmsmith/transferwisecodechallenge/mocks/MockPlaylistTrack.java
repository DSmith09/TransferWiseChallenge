package com.example.dmsmith.transferwisecodechallenge.mocks;

import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.model.Track;
import com.example.dmsmith.transferwisecodechallenge.model.User;

public class MockPlaylistTrack extends PlaylistTrack {
    private static final String MOCK_ADDEDAT = "mock_added_at";
    private static final User MOCK_ADDEDBY = null;
    private static final boolean MOCK_IS_LOCAL = true;
    private static final Track MOCK_TRACK = createMockTrack();


    public MockPlaylistTrack() {
        this.setAddedAt(MOCK_ADDEDAT);
        this.setAddedBy(MOCK_ADDEDBY);
        this.setLocal(MOCK_IS_LOCAL);
        this.setTrack(MOCK_TRACK);
    }

    private static Track createMockTrack() {
        return new MockTrack();
    }

    public PlaylistTrack[] getPlaylistTracks() {
        return new PlaylistTrack[]{this, this, this};
    }
}
