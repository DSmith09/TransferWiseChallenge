package com.example.dmsmith.transferwisecodechallenge.modules;

import android.accounts.AccountManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountManagerModule {
    private AccountManager mAccountManager;

    public AccountManagerModule(AccountManager accountManager) {
        this.mAccountManager = accountManager;
    }

    @Provides
    @Singleton
    public AccountManager provideAccountManager() {
        return mAccountManager;
    }
}
