package com.example.vaishnavj.phonesy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by user on 3/5/2017.
 */

public class ServiceReceiver extends BroadcastReceiver {

    SharedPreferences sharedpreferences;

    @Override
    public void onReceive(final Context context, Intent intent) {

        sharedpreferences = context.getSharedPreferences("Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        final Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        final AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);


                System.out.println("Incomming number " + sharedpreferences.getString("state", null));


                if (sharedpreferences.getString("state", null).equals("on") && audio.getRingerMode() == 0) {
                    // cursor.moveToNext();
                    //cursor.moveToNext();

                    if (sharedpreferences.getString("ph1", null).equals(incomingNumber)) {
                        //long[] pattern = {0,400,700,600,300};
                        v.vibrate(new long[]{0,200,100,200,275,425,100,200,100,200,275,425,100,75,25,75,125,75,25,75,125,100,100}, 0);   //jamesbond
                        Log.i("Vibrate", "done");
                        Log.i("No", "" + sharedpreferences.getString("ph1", null));
                    }
                    if ( sharedpreferences.getString("ph2", null).equals(incomingNumber)) {
                        //long[] pattern = {0,400,700,600,300};
                        v.vibrate(new long[]{0,125,75,125,275,200,275,125,75,125,275,200,600,200,600}, 0); //supermario
                        System.out.println("called no " + incomingNumber);
                    }
                    if (sharedpreferences.getString("ph3", null).equals(incomingNumber)) {
                        //long[] pattern = {0,400,700,600,300};
                        v.vibrate(new long[]{0,150,150,150,150,75,75,150,150,150,150,450}, 0);//power
                        Log.i("Vibrate", "done");
                    }
                    if (sharedpreferences.getString("ph4", null).equals(incomingNumber)) {
                        //long[] pattern = {0,400,700,600,300};
                        v.vibrate(new long[]{0,0,300,100,50,100,50,100,50,100,50,100,50,100,50,150,150,150,450,100,50,100,50,150,150,150,450,100,50,100,50,150,150,150,450,150,150}, 0); //jackson
                        Log.i("Vibrate", "done");
                    }
                    if (sharedpreferences.getString("ph5", null).equals(incomingNumber)) {
                        //long[] pattern = {0,400,700,600,300};
                        v.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, 0); //starwars
                        Log.i("Vibrate", "done");
                    }
                }


               // if(i == count)
                //{
                 //   v.vibrate(new long[]{20,40,50,10,30,40},-1);
                //}

                if(state != 1)
                {
                    v.cancel();
                }

                   // System.out.println("databse no : "+num);


                Log.i("state",""+sharedpreferences.getString("state",null));
                System.out.println("incomingNo : "+incomingNumber);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
