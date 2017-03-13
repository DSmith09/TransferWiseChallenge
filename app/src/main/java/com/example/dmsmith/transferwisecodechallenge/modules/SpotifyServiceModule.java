package com.example.dmsmith.transferwisecodechallenge.modules;

import com.example.dmsmith.transferwisecodechallenge.spotify.SpotifyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SpotifyServiceModule {

    SpotifyService mSpotifyService;

    public SpotifyServiceModule(SpotifyService spotifyService) {
        mSpotifyService = spotifyService;
    }

    @Provides
    @Singleton
    public SpotifyService provideSpotifyService() {
        return mSpotifyService;
    }
}
