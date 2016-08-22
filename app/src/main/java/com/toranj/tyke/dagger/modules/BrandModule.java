package com.toranj.tyke.dagger.modules;

import com.toranj.tyke.dagger.scopes.PerActivity;
import com.toranj.tyke.dagger.scopes.PerTest;
import com.toranj.tyke.restApi.BrandApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by arash on 8/13/16.
 */

@Module
public class BrandModule {

    @Provides
    @PerActivity
    public BrandApiInterface providesBrandApiInterface(Retrofit retrofit) {
        return retrofit.create(BrandApiInterface.class);
    }
}
