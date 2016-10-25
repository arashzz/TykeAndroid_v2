package com.toranj.tyke.ui;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.helpers.AccountHelper;
import com.toranj.tyke.models.User;
import com.toranj.tyke.ui.fragments.Register1Fragment;
import com.toranj.tyke.ui.fragments.Register2Fragment;
import com.toranj.tyke.ui.fragments.Register3Fragment;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;
import com.toranj.tyke.utility.CurrentUser;


public class RegisterActivity extends BaseActivity implements RegisterFragmentListener {

    private FragmentManager fragmentManager;

    private final String TAG_STEP_1 = "step_1";
    private final String TAG_STEP_2 = "step_2";
    private final String TAG_STEP_3 = "step_3";
    private String currentStep;
    private User registeringUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fragmentManager = getSupportFragmentManager();

        initiateStep1();
    }

    @Override
    public void onBackPressed() {
//        if(currentStep.equals(TAG_STEP_1)) {
//            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(i);
//            finish();
//        }
//        else if(currentStep.equals(TAG_STEP_2)) {
//            fragmentManager.popBackStack();
//        }
    }

    @Override
    public void onStep1Finished(User user) {
        initiateStep2(user);
    }

    @Override
    public void onStep2Finished(User user) {
        initiateStep3(user);
    }

    @Override
    public void onStep3Finished(User user) {
        AccountHelper ah = new AccountHelper(PreferenceManager.getDefaultSharedPreferences(getApplication()));
        //TODO: uncomment later
        //ah.setUserToken(user.getToken());
        CurrentUser.setInfo(user);
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterCancel() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment, TAG_STEP_1);
        ft.commit();
    }

    private void initiateStep1() {
        currentStep = TAG_STEP_1;
        replaceFragment(Register1Fragment.newInstance());
    }

    private void initiateStep2(User user) {
        currentStep = TAG_STEP_2;
        replaceFragment(Register2Fragment.newInstance(user));
    }

    private void initiateStep3(User user) {
        currentStep = TAG_STEP_3;
        replaceFragment(Register3Fragment.newInstance(user));
    }
}
