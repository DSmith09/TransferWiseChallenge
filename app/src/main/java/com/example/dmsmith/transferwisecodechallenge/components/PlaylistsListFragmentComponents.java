package com.example.dmsmith.transferwisecodechallenge.components;


import com.example.dmsmith.transferwisecodechallenge.fragment.PlaylistsListFragment;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SpotifyServiceModule.class})
public interface PlaylistsListFragmentComponents {

    void inject(PlaylistsListFragment playlistsListFragment);
}
