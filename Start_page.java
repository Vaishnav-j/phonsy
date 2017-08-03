package com.example.vaishnavj.phonesy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import static android.R.attr.button;

public class Start_page extends AppCompatActivity {
    ImageButton b1,b2,b3,b4;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        sharedpreferences = this.getSharedPreferences( "Phone", Context.MODE_PRIVATE);
        final AudioManager audio = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        final SharedPreferences.Editor editor = sharedpreferences.edit();
        final Switch s1 = (Switch) findViewById(R.id.switch1);
        b1 = (ImageButton) findViewById(R.id.accident);
        b2 = (ImageButton) findViewById(R.id.silent);
        b3 = (ImageButton) findViewById(R.id.battery);
        b4 = (ImageButton) findViewById(R.id.help);
        //editor.putString("State","on");
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    editor.putString("State","on");
                    editor.commit();

                    audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Toast.makeText(getApplicationContext()," "+sharedpreferences.getString("State",null),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SensorDetect.class);
                    //audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    //intent.putExtra("nos",num[0]);
                    startService(intent);


                }
                else
                {
                    editor.putString("State","off");
                    editor.commit();
                   // audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }

            }
            });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
    });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),Main_Activity.class);
                startActivity(intent);
            }
        });
       b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),Acemersms.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),help.class);
                startActivity(intent);
            }
        });

    }
}
