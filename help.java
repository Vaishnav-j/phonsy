package com.example.vaishnavj.phonesy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView t1 ;
        t1 = (TextView) findViewById(R.id.help);
        t1.setText("Phonsy  is an intricate call manager and emergency service app.The app has 4 functionalities.\n" +
                "Battery mode,Accident mode,Silent mode and Loud speaker mode.\n\n" +
                "\t\t\t\t\t\t\t\t\t\tHOW TO USE\n" +
                "1.Battery Mode\n" +
                " To use this mode,important contacts must be provided in the fields provided.when the battery charge is  below a threshold value (minimum value),sms alerts along with current location will be send to the contacts provided.\n" +
                "\n2.Accident Mode\n" +
                "  Here also important contacts must be provided.The driving mode must be switched on while driving.When any accident oocurs,sms will be send on the shake along with current location.\n" +
                "\n3.Silent Mode\n" +
                "  The phone will be in silent mode in locations like library,school,colleges,courts etc automatically.The silent mode button must be switched on.\n" +
                "\n4.Loudspeaker Mode\n" +
                " To on this functionality,driving mode must switched on.All calls will be in loudspeaker mode and the name of the caller will be said while calling.");
    }
}
