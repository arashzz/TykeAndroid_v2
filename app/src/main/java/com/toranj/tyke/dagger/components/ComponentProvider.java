package com.toranj.tyke.dagger.components;

import com.toranj.tyke.dagger.modules.BrandModule;
import com.toranj.tyke.dagger.modules.LotteryModule;
import com.toranj.tyke.dagger.modules.SpendingModule;
import com.toranj.tyke.dagger.modules.UserModule;
import com.toranj.tyke.dagger.scopes.PerActivity;
import com.toranj.tyke.restApi.BrandApiInterface;
import com.toranj.tyke.restApi.LotteryApiInterface;
import com.toranj.tyke.restApi.SpendingApiInterface;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.ui.RegisterActivity;
import com.toranj.tyke.ui.fragments.BrandsFragment;
import com.toranj.tyke.ui.fragments.DashboardBrandsFragment;
import com.toranj.tyke.ui.fragments.DashboardLotteriesFragment;
import com.toranj.tyke.ui.fragments.LotteryDetailsFragment;
import com.toranj.tyke.ui.fragments.Register1Fragment;
import com.toranj.tyke.ui.fragments.SpendingFragment;

import dagger.Component;

/**
 * Created by arash on 8/5/16.
 */

@PerActivity
@Component(
        dependencies = NetworkComponent.class,
        modules = {
                LotteryModule.class,
                SpendingModule.class,
                BrandModule.class,
                UserModule.class
        }
)
public interface ComponentProvider {
    void inject(DashboardLotteriesFragment fragment);
    void inject(DashboardBrandsFragment fragment);
    void inject(RegisterActivity activity);
    void inject(SpendingFragment fragment);
    void inject(LotteryDetailsFragment fragment);
    void inject(BrandsFragment fragment);
    void inject(Register1Fragment fragment);

    BrandApiInterface brandApiInterface();
    LotteryApiInterface lotteryApiInterface();
    SpendingApiInterface spendingApiInterface();
    UserApiInterface userApiInterface();
}
