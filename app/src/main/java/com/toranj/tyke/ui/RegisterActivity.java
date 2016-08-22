package com.toranj.tyke.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.models.Tag;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.ui.fragments.Register1Fragment;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;


public class RegisterActivity extends BaseActivity implements RegisterFragmentListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private FragmentManager fragmentManager;

    private final String TAG_STEP_1 = "step_1";
    private final String TAG_STEP_2 = "step_2";
    private String currentStep;

    @Inject
    Retrofit retrofit;

    @Inject
    UserApiInterface userApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fragmentManager = getSupportFragmentManager();

        ((TykeApp)getApplication()).getComponentProvider().inject(this);
        initiateStep1();
        Location location = getLastKnownLocation();
        if(location == null) {
            Toast.makeText(this, "location is null", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Lat is >> " + location.getLatitude() + " ^^^ Long is >> " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(currentStep.equals(TAG_STEP_1)) {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        else if(currentStep.equals(TAG_STEP_2)) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onStep1Finished(String sex, int date, int month, int year) {

    }

    @Override
    public void onStep2Finished(String firstName, String lastName, String mobileNumber, List<Tag> tags) {

    }

    private void initiateStep1() {
        currentStep = TAG_STEP_1;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.content_frame, Register1Fragment.newInstance(), TAG_STEP_1);
        ft.addToBackStack(TAG_STEP_1);
        ft.commit();
    }

    private void initiateStep2() {
        currentStep = TAG_STEP_2;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
