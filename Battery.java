package com.example.vaishnavj.phonesy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Battery extends Service {

    private long lastupdate=0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        //Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
          //Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_LONG).show();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Context context=this;
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);


        String smess;
        Toast.makeText(this,Integer.toString(level), Toast.LENGTH_LONG).show();
        //smsManager.sendTextMessage("9400977209", null,"assassin", null, null);
        long curtime=System.currentTimeMillis();
        if(curtime-lastupdate> 60000)
        {
            level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            if(level<=100)
            {
                Intent in=new Intent(this,Sloc.class);
                startService(in);
                //smess="I am at location:Model Engineering college.My battery is about die.Please dont panic.";
                //Toast.makeText(getApplicationContext(),intent.getStringExtra("eph1"), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"Sms send",1000).show();
                //smsManager.sendTextMessage("9400977209", null,"assassin", null, null);
            }
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}
