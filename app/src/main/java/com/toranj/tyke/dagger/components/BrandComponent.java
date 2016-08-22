package com.toranj.tyke.dagger.components;

import com.toranj.tyke.restApi.BrandApiInterface;
import com.toranj.tyke.ui.fragments.BrandsFragment;

/**
 * Created by arash on 8/13/16.
 */

//@PerTest
//@Component(
//        modules = BrandModule.class)
public interface BrandComponent {

    void inject(BrandsFragment fragment);

    BrandApiInterface brandApiInterface();
}
