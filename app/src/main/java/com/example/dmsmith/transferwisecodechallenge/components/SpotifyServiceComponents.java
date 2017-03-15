package com.example.dmsmith.transferwisecodechallenge.components;

import com.example.dmsmith.transferwisecodechallenge.modules.AccountManagerModule;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AccountManagerModule.class})
public interface SpotifyServiceComponents {

    void inject(SpotifyService spotifyService);
}
