package com.example.dmsmith.transferwisecodechallenge;

import android.app.Application;

import com.example.dmsmith.transferwisecodechallenge.components.DaggerLoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.components
        .DaggerPlaylistsListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerRestInvokerComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerSpotifyServiceComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerTrackPlayerFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerTracksListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.LoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.components.PlaylistsListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.RestInvokerComponents;
import com.example.dmsmith.transferwisecodechallenge.components.SpotifyServiceComponents;
import com.example.dmsmith.transferwisecodechallenge.components.TrackPlayerFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.TracksListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.modules.ApplicationModule;
import com.example.dmsmith.transferwisecodechallenge.modules.RestTemplateModule;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;
import com.example.dmsmith.transferwisecodechallenge.network.RestInvokerClient;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.example.dmsmith.transferwisecodechallenge.store.CacheStore;
import com.spotify.sdk.android.player.Spotify;

import net.danlew.android.joda.JodaTimeAndroid;

import org.springframework.web.client.RestTemplate;

public class MyApp extends Application {
    private LoginActivityComponents mLoginActivityComponents;
    private SpotifyServiceComponents mSpotifyServiceComponents;
    private RestInvokerComponents mRestInvokerComponents;
    private PlaylistsListFragmentComponents mPlaylistsListFragmentComponents;
    private TracksListFragmentComponents mTracksListFragmentComponents;
    private TrackPlayerFragmentComponents mTrackPlayerFragmentComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        RestInvokerClient restInvokerClient = new RestInvokerClient(new RestTemplate());
        SpotifyService spotifyService = new SpotifyService(restInvokerClient);

        mLoginActivityComponents = DaggerLoginActivityComponents.builder().spotifyServiceModule(
                new SpotifyServiceModule(spotifyService))
                .applicationModule(new ApplicationModule(this))
                .build();

        mSpotifyServiceComponents = DaggerSpotifyServiceComponents.builder().build();

        mRestInvokerComponents = DaggerRestInvokerComponents.builder()
                .restTemplateModule(new RestTemplateModule(new RestTemplate())).build();

        mPlaylistsListFragmentComponents = DaggerPlaylistsListFragmentComponents.builder()
                .spotifyServiceModule(new SpotifyServiceModule(spotifyService)).build();
        mTracksListFragmentComponents = DaggerTracksListFragmentComponents.builder()
                .spotifyServiceModule(new SpotifyServiceModule(spotifyService)).build();

        mTrackPlayerFragmentComponents = DaggerTrackPlayerFragmentComponents.builder()
                .spotifyServiceModule(new SpotifyServiceModule(spotifyService)).build();

    }

    // Tries to Invalidate Cached Data to prevent Memory Leak
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        CacheStore.getInstance().invalidateCache();
    }

    // Destroy Spotify Player on Application Termination
    @Override
    public void onTerminate() {
        super.onTerminate();
        Spotify.destroyPlayer(this);
    }

    public LoginActivityComponents getLoginActivityComponents() {
        return mLoginActivityComponents;
    }

    public PlaylistsListFragmentComponents getPlaylistsListFragmentComponents() {
        return mPlaylistsListFragmentComponents;
    }

    public TracksListFragmentComponents getTracksListFragmentComponents() {
        return mTracksListFragmentComponents;
    }

    public TrackPlayerFragmentComponents getTrackPlayerFragmentComponents() {
        return mTrackPlayerFragmentComponents;
    }
}
