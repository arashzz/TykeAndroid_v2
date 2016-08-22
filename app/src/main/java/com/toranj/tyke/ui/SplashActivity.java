package com.toranj.tyke.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.helpers.AccountHelper;
import com.toranj.tyke.utility.CurrentUser;

import javax.inject.Inject;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 1000;

    @Inject
    AccountHelper accountHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //TODO: replace this part later with injection
                AccountHelper ah = new AccountHelper(PreferenceManager.getDefaultSharedPreferences((TykeApp)getApplication()));
                Intent i;
                if(ah.isUserExist()) {
                    //TODO: make a call to server with user token to receive user information
                    CurrentUser.setInfo(null);
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
