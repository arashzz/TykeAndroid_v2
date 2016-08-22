package com.toranj.tyke.dagger.components;

import android.content.SharedPreferences;

import com.toranj.tyke.TykeApp;
import com.toranj.tyke.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arash on 8/13/16.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(TykeApp application);

    SharedPreferences sharedPreferences();
}
