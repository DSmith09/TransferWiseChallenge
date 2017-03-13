package com.example.dmsmith.transferwisecodechallenge;

import android.app.Application;

import com.example.dmsmith.transferwisecodechallenge.components.DaggerLoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.components.LoginActivityComponents;
import com.example.dmsmith.transferwisecodechallenge.modules.ApplicationModule;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;
import com.example.dmsmith.transferwisecodechallenge.spotify.SpotifyService;

public class MyApp extends Application {
    private LoginActivityComponents mComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponents = DaggerLoginActivityComponents.builder().spotifyServiceModule(new SpotifyServiceModule(new SpotifyService())).applicationModule(new ApplicationModule(this)).build();
    }

    public LoginActivityComponents getComponents() {
        return mComponents;
    }
}
