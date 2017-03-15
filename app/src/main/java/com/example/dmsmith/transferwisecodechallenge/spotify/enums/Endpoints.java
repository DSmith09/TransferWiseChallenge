package com.example.dmsmith.transferwisecodechallenge.spotify.enums;

import com.example.dmsmith.transferwisecodechallenge.spotify.model.SpotifyEndpoint;

import org.springframework.http.HttpMethod;

// ENDPOINTS enum - Endpoints for Spotify API
public enum Endpoints {
    GET_ALBUMS(new SpotifyEndpoint(HttpMethod.GET, "/v1/me/albums")),
    GET_PLAYLIST(new SpotifyEndpoint(HttpMethod.GET, "/v1/me/playlists")),
    GET_TRACKS(new SpotifyEndpoint(HttpMethod.GET, "/v1/me/tracks"));

    private SpotifyEndpoint mSpotifyEndpoint;

    Endpoints(SpotifyEndpoint spotifyEndpoint) {
        this.mSpotifyEndpoint = spotifyEndpoint;
    }

    public SpotifyEndpoint getEndpoint() {
        return mSpotifyEndpoint;
    }
}
