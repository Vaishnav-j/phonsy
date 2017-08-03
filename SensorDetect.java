package com.example.vaishnavj.phonesy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Vaishnav J on 04-03-2017.
 */

public class SensorDetect extends Service implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    public double gforce;
    SharedPreferences sharedpreferences;
    //public String num=null;

    @Override
    public void onDestroy() {
        super.onDestroy();
        senSensorManager.unregisterListener(this);                               //turn off sensor
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);             //opening sensor service
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL); //turning sensor detction on
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        sharedpreferences = this.getSharedPreferences( "Phone",Context.MODE_PRIVATE);         // turn off service if driving mode is off
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.getString("State",null).equals("off")) {
            stopSelf();
        }
        //Toast.makeText(getApplicationContext(), "service", Toast.LENGTH_SHORT).show();
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {                                 //obtaining accelartion from sensor
            float x = (int)sensorEvent.values[0];
            float y = (int)sensorEvent.values[1];
            float z = (int)sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {                                                //interval at which sensor detects accident
               // long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                double gforce = Math.abs(x+y+z)/ 9.8;                                          //calculating gforce
                Intent s=new Intent(this,SLocation.class);
                if(gforce>3) {                                          // condition for occuring accident
                    startService(s);                                    //starting the location service
                    stopSelf();                                         //stop sensordetect service
                }
                last_x = x;                                             //updating acceleration variables
                last_y = y;
                last_z = z;

            }

        }
    }
}