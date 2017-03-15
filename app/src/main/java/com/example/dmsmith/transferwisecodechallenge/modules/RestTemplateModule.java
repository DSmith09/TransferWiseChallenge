package com.example.dmsmith.transferwisecodechallenge.modules;

import org.springframework.web.client.RestTemplate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestTemplateModule {
    private RestTemplate mRestTemplate;

    public RestTemplateModule(RestTemplate restTemplate) {
        this.mRestTemplate = restTemplate;
    }

    @Provides
    @Singleton
    public RestTemplate provideRestTemplate() {
        return mRestTemplate;
    }
}
