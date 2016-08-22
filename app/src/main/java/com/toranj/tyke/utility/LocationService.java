package com.toranj.tyke.utility;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by arash on 8/19/16.
 */
public class LocationService implements LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 0;
    private final static boolean forceNetwork = false;
    private static LocationService instance = null;
    private LocationManager locationManager;
    public Location location;
    public double longitude;
    public double latitude;
    public boolean isGPSEnabled;
    public boolean isNetworkEnabled;
    public boolean locationServiceAvailable;
    private Context context;

    public static LocationService getLocationManager(Context context)     {
        if (instance == null) {
            instance = new LocationService(context);
        }
        return instance;
    }

    private LocationService( Context context )     {

        initLocationService(context);
        this.context = context;
//        LogService.log("LocationService created");
    }

    @TargetApi(23)
    private void initLocationService(Context context) {

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }

        try   {
            this.longitude = 0.0;
            this.latitude = 0.0;
            this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            // Get GPS and network status
            this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

//            if (forceNetwork) {
//                isGPSEnabled = false;
//            }

            if (!isNetworkEnabled && !isGPSEnabled)    {
                // cannot get location
//                this.locationServiceAvailable = false;
            }
            else {
//                this.locationServiceAvailable = true;

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            2000,
                            2, this);
                    if (locationManager != null)   {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        isGPSEnabled = false;
                    }
                }

                if (isGPSEnabled)  {

                    if (locationManager != null)  {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            2000,
                            2, this);

                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }
        } catch (Exception ex)  {
//            LogService.log( "Error creating location service: " + ex.getMessage() );

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d("ddd","ddd");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("ddd","eee");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("ddd","aaa");
    }

    @Override
    public void onLocationChanged(Location location)     {
        Toast.makeText(context,
                "Last: " + location.getLatitude() + "__" + "Long: " + location.getLongitude(),
                Toast.LENGTH_SHORT).show();
    }
}
