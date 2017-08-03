package com.example.vaishnavj.phonesy;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Vaishnav J on 05-03-2017.
 */

public class SLocation extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{


        // private final IBinder binder = new ServiceBinder();

        private boolean a;
        private LocationRequest mLocationRequest;
        private GoogleApiClient mGoogleApiClient;
        private static final String LOGSERVICE = "#######";
        public static final String BROADCAST_ACTION = "com.android.example.silentmode.sendlocation";
        public String lat,lon;


    @Override
    public void onCreate() {
        super.onCreate();
        buildGoogleApiClient();
        Log.i(LOGSERVICE, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOGSERVICE, "onStartCommand");
        Toast.makeText(this,"connected",Toast.LENGTH_SHORT).show();
        if (!mGoogleApiClient.isConnected())
            mGoogleApiClient.connect();
        return START_STICKY;
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i(LOGSERVICE, "onConnected" + bundle);
        try {
            Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (l != null) {
                Log.i(LOGSERVICE, "lat " + l.getLatitude());
                Log.i(LOGSERVICE, "lng " + l.getLongitude());

                SmsManager smsManager = SmsManager.getDefault();
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Phone",Context.MODE_PRIVATE);
                String num[] = new String[5];
                num[0] = sharedPref.getString("phoneKey1",null);                        // retrieving phone numbers from database
                num[1] = sharedPref.getString("phoneKey2",null);
                num[2] = sharedPref.getString("phoneKey3",null);
                num[3] = sharedPref.getString("phoneKey4",null);
                num[4] = sharedPref.getString("phoneKey5",null);

                if (!(num[0].matches(".*[a-zA-Z]+.*")||num[0]==""))                    //sending message if valid number
                smsManager.sendTextMessage(num[0],null, "accident occuered at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);          //senting message
                if (!(num[1].matches(".*[a-zA-Z]+.*")||num[1]==""))
                smsManager.sendTextMessage(num[1],null, "accident occuered at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                if (!(num[2].matches(".*[a-zA-Z]+.*")||num[2]==""))
                smsManager.sendTextMessage(num[2],null, "accident occuered at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                if (!(num[3].matches(".*[a-zA-Z]+.*")||num[3]==""))
                smsManager.sendTextMessage(num[3],null, "accident occuered at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                if (!(num[4].matches(".*[a-zA-Z]+.*")||num[4]==""))
                smsManager.sendTextMessage(num[4],null, "accident occuered at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                stopSelf();
               // Toast.makeText(this,num[0],Toast.LENGTH_SHORT).show();

            }
        }
        catch(SecurityException e)
        {

        }




        startLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(LOGSERVICE, "onConnectionSuspended " + i);

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOGSERVICE, "lat " + location.getLatitude());
        Log.i(LOGSERVICE, "lng " + location.getLongitude());
        lat=Double.toString(location.getLatitude());
        lon=Double.toString(location.getLongitude());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOGSERVICE, "onDestroy");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LOGSERVICE, "onConnectionFailed ");
        Toast.makeText(getApplicationContext(),"fail", Toast.LENGTH_SHORT).show();
    }

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }
        private void startLocationUpdate() {
            initLocationRequest();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    private void stopLocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    }
        protected synchronized void buildGoogleApiClient() {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addOnConnectionFailedListener(this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }
}
