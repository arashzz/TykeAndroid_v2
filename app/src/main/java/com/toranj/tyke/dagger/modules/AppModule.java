package com.toranj.tyke.dagger.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.toranj.tyke.TykeApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arash on 7/24/16.
 */

@Module
public class AppModule {

    TykeApp application;

    public AppModule(TykeApp application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
