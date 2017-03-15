package com.example.dmsmith.transferwisecodechallenge.store;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.dmsmith.transferwisecodechallenge.model.Playlist;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class PlaylistStore {
    private static PlaylistStore INSTANCE;
    private List<Playlist> mPlaylists;

    public static PlaylistStore getInstance() {
        if (INSTANCE == null) {
            synchronized (PlaylistStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PlaylistStore();
                }
            }
        }
        return INSTANCE;
    }

    // Private Ctor for Singleton Instance
    private PlaylistStore() {

    }

    public List<Playlist> getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(@Nullable List playlists) {
        if (playlists == null) {
            this.mPlaylists = null;
        } else {
            this.mPlaylists = new ArrayList<>(playlists);
        }
    }

    public void addPlaylist(@NonNull Playlist playlist) {
        this.mPlaylists.add(playlist);
    }

    @Nullable
    public Playlist getPlaylist(final String id) {
        if (id == null) {
            return null;
        }
        for(Playlist playlist : mPlaylists) {
            if (playlist.getId().equals(id)) {
                return playlist;
            }
        }
        return null;
    }
}
