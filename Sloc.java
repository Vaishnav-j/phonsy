package com.example.vaishnavj.phonesy;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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

import static android.R.attr.level;

public class Sloc extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{


    SharedPreferences sharedpreferences;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private static final String LOGSERVICE = "#######";



    int counter = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedpreferences = this.getSharedPreferences("Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        buildGoogleApiClient();
        Log.i(LOGSERVICE, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOGSERVICE, "onStartCommand");
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
                String num1 = sharedpreferences.getString("Num1",null);
                String num2 = sharedpreferences.getString("Num2",null);
                String num3 = sharedpreferences.getString("Num3",null);
                try {


                    if (!(num1.equals("") || num1.matches(".*[a-zA-Z]+.*"))) {
                        smsManager.sendTextMessage("" + num1, null, "battery low.last found at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                        Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
                    }
                    if (!(num2.equals("") || num2.matches(".*[a-zA-Z]+.*"))) {
                        smsManager.sendTextMessage("" + num2, null, "battery low.last found at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                        //Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
                    }
                    if (!(num3.equals("") || num3.matches(".*[a-zA-Z]+.*"))) {
                        smsManager.sendTextMessage("" + num3, null, "battery low.last found at http://maps.google.com/?q="+l.getLatitude()+ ","+l.getLongitude(), null, null);
                        //Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NullPointerException e1)
                {

                }

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
        // LatLng mLocation = (new LatLng(location.getLatitude(), location.getLongitude()));
        //Toast.makeText(this,"shiitttt", Toast.LENGTH_LONG).show();
       // PlacesTask placesTask = new PlacesTask(this);
      //  placesTask.execute(sbMethod(location.getLatitude(),location.getLongitude()).toString());

        // sbMethod(location.getLatitude(),location.getLongitude());

    }
    public StringBuilder sbMethod(double mLatitude,double mLongitude) {

        //use your current location here


        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + mLatitude + "," + mLongitude);
        sb.append("&radius=20");
        sb.append("&types=" + "restaurant|hospital|library|court|college|school|hostel|point_of_interest");
        sb.append("&sensor=true");

        sb.append("&key=AIzaSyDgX8HdYs7M73sHMA_VGY__gImySWrJd-M ");

        Log.i("Map", "url: " + sb.toString());

        return sb;



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOGSERVICE, "onDestroy - Estou sendo destruido ");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LOGSERVICE, "onConnectionFailed ");

    }

    private void initLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(10);

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
