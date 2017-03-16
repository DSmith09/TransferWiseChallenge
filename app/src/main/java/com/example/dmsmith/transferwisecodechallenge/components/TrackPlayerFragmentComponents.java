package com.example.dmsmith.transferwisecodechallenge.components;

import com.example.dmsmith.transferwisecodechallenge.fragment.TrackPlayerFragment;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SpotifyServiceModule.class})
public interface TrackPlayerFragmentComponents {

    void inject(TrackPlayerFragment trackPlayerFragment);
}
