package com.example.dmsmith.transferwisecodechallenge.components;

import com.example.dmsmith.transferwisecodechallenge.activity.LoginActivity;
import com.example.dmsmith.transferwisecodechallenge.modules.ApplicationModule;
import com.example.dmsmith.transferwisecodechallenge.modules.SpotifyServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, SpotifyServiceModule.class})
public interface LoginActivityComponents {

    void inject(LoginActivity activity);
}
