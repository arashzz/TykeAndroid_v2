package com.toranj.tyke;

import android.app.Application;

import com.toranj.tyke.dagger.components.AppComponent;
import com.toranj.tyke.dagger.components.ComponentProvider;
import com.toranj.tyke.dagger.components.DaggerAppComponent;
import com.toranj.tyke.dagger.components.DaggerComponentProvider;
import com.toranj.tyke.dagger.components.DaggerNetworkComponent;
import com.toranj.tyke.dagger.components.NetworkComponent;
import com.toranj.tyke.dagger.modules.FragmentModule;
import com.toranj.tyke.dagger.modules.LotteryModule;
import com.toranj.tyke.dagger.modules.NetworkModule;
import com.toranj.tyke.dagger.modules.SpendingModule;
import com.toranj.tyke.ui.MainActivity;
import com.toranj.tyke.utility.FontsOverride;

/**
 * Created by arash on 7/25/16.
 */
public class TykeApp extends Application {

    private NetworkComponent networkComponent;
    private ComponentProvider componentProvider;

    @Override
    public void onCreate() {
        super.onCreate();


        FontsOverride.setDefaultFont(this, "DEFAULT", "iransans.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "iransans.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "iransans.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "iransans.ttf");

//        appComponent = DaggerAppComponent.builder()
//                .build();

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule("http://192.168.0.4:8080"))
                .build();

        componentProvider = DaggerComponentProvider.builder()
                .networkComponent(networkComponent)
                .lotteryModule(new LotteryModule())
                .spendingModule(new SpendingModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public ComponentProvider getComponentProvider() {
        return componentProvider;
    }
}
