package com.toranj.tyke.dagger.modules;

import com.toranj.tyke.dagger.scopes.PerActivity;
import com.toranj.tyke.restApi.TagApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by arash.jahromi on 23/8/2016.
 */

@Module
public class TagModule {

    @Provides
    @PerActivity
    public TagApiInterface ProvidestagApiInterface(Retrofit retrofit) {
        return retrofit.create(TagApiInterface.class);
    }
}
