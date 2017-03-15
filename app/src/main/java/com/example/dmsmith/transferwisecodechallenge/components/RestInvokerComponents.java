package com.example.dmsmith.transferwisecodechallenge.components;

import com.example.dmsmith.transferwisecodechallenge.modules.RestTemplateModule;
import com.example.dmsmith.transferwisecodechallenge.network.RestInvoker;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RestTemplateModule.class})
public interface RestInvokerComponents {

    void inject(RestInvoker restInvoker);
}
