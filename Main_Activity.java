package com.example.vaishnavj.phonesy;

import android.net.ConnectivityManager;


        import android.app.Activity;
        import android.app.ActivityManager;
        import android.content.BroadcastReceiver;
        import android.content.ComponentName;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.ServiceConnection;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.media.AudioManager;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.IBinder;
        import android.os.Messenger;
        import android.support.v4.content.LocalBroadcastManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.IOException;
        import java.util.Date;
        import java.util.List;
        import java.util.Locale;

        import static android.R.attr.value;
        import static android.content.ContentValues.TAG;

public class Main_Activity extends Activity {


    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        sharedpreferences = this.getSharedPreferences("Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        initialze();
        Switch s1 = (Switch) findViewById(R.id.switch1);
        String state = sharedpreferences.getString("state",null);
       // if(state.equals("on"))
        //{
         //   s1.setChecked(true);
        //}
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.edit1);
                e1.setEnabled(true);
                editor.putString("ph1",e1.getText().toString());
                editor.commit();


            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.edit2);
                e1.setEnabled(true);
                editor.putString("ph2",e1.getText().toString());
                editor.commit();


            }
        });
        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.edit3);
                e1.setEnabled(true);
                editor.putString("ph3",e1.getText().toString());
                editor.commit();

            }
        });
        Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.edit4);
                e1.setEnabled(true);
                editor.putString("ph4",e1.getText().toString());
                editor.commit();

            }
        });
        Button b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1 = (EditText) findViewById(R.id.edit5);
                e1.setEnabled(true);
                editor.putString("ph5",e1.getText().toString());
                editor.commit();

            }
        });

        // mDbhelper.delete(db);



        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    if(isNetworkAvailable()) {
                        editor.putString("state","on");
                        editor.commit();

                        //startService(locationService.class);

                        Thread t = new Thread() {
                            public void run() {
                                Intent serviceIntent = new Intent(getApplicationContext(), locationService.class);

                                startService(serviceIntent);

                            }
                        };
                        t.start();


                    }
                    else
                        editor.putString("state","on");
                    editor.commit();
                   // Toast.makeText(getApplicationContext(),"No network",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    editor.putString("state","on");
                    editor.commit();
                    Intent serviceIntent = new Intent(getApplicationContext(), locationService.class);
                    stopService(serviceIntent);
                    AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    audio.setRingerMode(2);
                    Log.i("Normal","done");

                }
            }
        });



    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




    public void initialze()
    {
        EditText e1 = (EditText) findViewById(R.id.edit1);
        EditText e2 = (EditText) findViewById(R.id.edit2);
        EditText e3 = (EditText) findViewById(R.id.edit3);
        EditText e4 = (EditText) findViewById(R.id.edit4);
        EditText e5 = (EditText) findViewById(R.id.edit5);

        e1.setText(sharedpreferences.getString("ph1",null));
        e2.setText(sharedpreferences.getString("ph2",null));
        e3.setText(sharedpreferences.getString("ph3",null));
        e4.setText(sharedpreferences.getString("ph4",null));
        e5.setText(sharedpreferences.getString("ph5",null));


    }


}
