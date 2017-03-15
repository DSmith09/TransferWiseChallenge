package com.example.dmsmith.transferwisecodechallenge.store;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class PlaylistTrackStore {
    private static PlaylistTrackStore INSTANCE;
    private List<PlaylistTrack> mPlaylistTracks;

    public static PlaylistTrackStore getInstance() {
        if (INSTANCE == null) {
            synchronized (PlaylistTrackStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PlaylistTrackStore();
                }
            }
        }
        return INSTANCE;
    }

    // Private Ctor for Singleton Instance
    private PlaylistTrackStore() {

    }

    public List<PlaylistTrack> getPlaylistTracks() {
        return mPlaylistTracks;
    }

    public void setPlaylistTracks(@Nullable List playlistTracks) {
        if (playlistTracks == null) {
            this.mPlaylistTracks = null;
        } else {
            this.mPlaylistTracks = new ArrayList<>(playlistTracks);
        }
    }

    public void addPlaylistTrack(@NonNull PlaylistTrack track) {
        this.mPlaylistTracks.add(track);
    }

    @Nullable
    public PlaylistTrack getPlaylistTrack(final String id) {
        if (id == null) {
            return null;
        }
        for (PlaylistTrack playlistTrack : mPlaylistTracks) {
            if (playlistTrack.getTrack().getId().equals(id)) {
                return playlistTrack;
            }
        }
        return null;
    }

}
