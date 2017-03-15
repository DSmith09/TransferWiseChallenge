package com.example.dmsmith.transferwisecodechallenge.modules;

import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SpotifyServiceModule {

    private SpotifyService mSpotifyService;

    public SpotifyServiceModule(SpotifyService spotifyService) {
        mSpotifyService = spotifyService;
    }

    @Provides
    @Singleton
    public SpotifyService provideSpotifyService() {
        return mSpotifyService;
    }
}
