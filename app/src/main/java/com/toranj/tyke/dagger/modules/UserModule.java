package com.toranj.tyke.dagger.modules;

import com.toranj.tyke.dagger.scopes.PerActivity;
import com.toranj.tyke.restApi.UserApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by arash on 8/18/16.
 */

@Module
public class UserModule {

    @Provides
    @PerActivity
    public UserApiInterface providesUserApiInterface(Retrofit retrofit) {
        return retrofit.create(UserApiInterface.class);
    }
}
