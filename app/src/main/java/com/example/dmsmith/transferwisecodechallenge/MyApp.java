package com.example.dmsmith.transferwisecodechallenge;

import android.accounts.AccountManager;
import android.app.Application;

import com.example.dmsmith.transferwisecodechallenge.components.DaggerLoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.components
        .DaggerPlaylistsListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerRestInvokerComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerSpotifyServiceComponents;
import com.example.dmsmith.transferwisecodechallenge.components.DaggerTracksListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.LoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.components.PlaylistsListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.components.RestInvokerComponents;
import com.example.dmsmith.transferwisecodechallenge.components.SpotifyServiceComponents;
import com.example.dmsmith.transferwisecodechallenge.components.TracksListFragmentComponents;
import com.example.dmsmith.transferwisecodechallenge.modules.AccountManagerModule;
import com.example.dmsmith.transferwisecodechallenge.modules.ApplicationModule;
import com.example.dmsmith.transferwisecodechallenge.modules.RestTemplateModule;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;
import com.example.dmsmith.transferwisecodechallenge.network.RestInvoker;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;

import net.danlew.android.joda.JodaTimeAndroid;

import org.springframework.web.client.RestTemplate;

public class MyApp extends Application {
    private LoginActivityComponents mLoginActivityComponents;
    private SpotifyServiceComponents mSpotifyServiceComponents;
    private RestInvokerComponents mRestInvokerComponents;
    private PlaylistsListFragmentComponents mPlaylistsListFragmentComponents;
    private TracksListFragmentComponents mTracksListFragmentComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        RestInvoker restInvoker = new RestInvoker(new RestTemplate());
        SpotifyService spotifyService = new SpotifyService(AccountManager.get(this), restInvoker);

        mLoginActivityComponents = DaggerLoginActivityComponents.builder().spotifyServiceModule(
                new SpotifyServiceModule(spotifyService))
                .applicationModule(new ApplicationModule(this))
                .build();

        mSpotifyServiceComponents = DaggerSpotifyServiceComponents.builder().accountManagerModule(
                new AccountManagerModule(AccountManager.get(this))).build();

        mRestInvokerComponents = DaggerRestInvokerComponents.builder()
                .restTemplateModule(new RestTemplateModule(new RestTemplate())).build();

        mPlaylistsListFragmentComponents = DaggerPlaylistsListFragmentComponents.builder()
                .spotifyServiceModule(new SpotifyServiceModule(spotifyService)).build();
        mTracksListFragmentComponents = DaggerTracksListFragmentComponents.builder()
                .spotifyServiceModule(new SpotifyServiceModule(spotifyService)).build();
    }

    public LoginActivityComponents getLoginActivityComponents() {
        return mLoginActivityComponents;
    }

    public SpotifyServiceComponents getSpotifyServiceComponents() {
        return mSpotifyServiceComponents;
    }

    public RestInvokerComponents getRestInvokerComponents() {
        return mRestInvokerComponents;
    }

    public PlaylistsListFragmentComponents getPlaylistsListFragmentComponents() {
        return mPlaylistsListFragmentComponents;
    }

    public TracksListFragmentComponents getTracksListFragmentComponents() {
        return mTracksListFragmentComponents;
    }
}
