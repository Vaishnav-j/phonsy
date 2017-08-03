package com.example.vaishnavj.phonesy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Acemersms extends Activity
{
    Switch s;
    EditText eph1,eph2,eph3;
    Button b;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        sharedpreferences = this.getSharedPreferences( "Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.emerms);


        //Intent in=new Intent(this,Battery.class);
        //startService(in);
        eph1=(EditText)findViewById(R.id.ph1);
        eph2=(EditText)findViewById(R.id.ph2);
        eph3=(EditText)findViewById(R.id.ph3);
        eph1.setText(sharedpreferences.getString("Num1",null));
        eph2.setText(sharedpreferences.getString("Num2",null));
        eph3.setText(sharedpreferences.getString("Num3",null));
        s=(Switch)findViewById(R.id.switch1);
        b=(Button)findViewById(R.id.button1);
        final Intent in=new Intent(this,Battery.class);
        //nputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //Toast.makeText(this, "vhhj", Toast.LENGTH_LONG).show();



        //float batteryPct = level / (float)scale;
        //Toast.makeText(getApplicationContext(),"Current battery level is"+level,1000).show();



        b.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //if(swstate==true){Toast.makeText(getApplicationContext(),"True",1000).show();}
                boolean swstate;
                swstate=s.isChecked();
                if(swstate==false){}
                else	{
                    String smess;
                    String snum1;
                    String snum2;
                    String snum3;
                    System.out.println("done");
                    snum1=eph1.getText().toString();
                    snum2=eph1.getText().toString();
                    snum3=eph1.getText().toString();
                    editor.putString("Num1",snum1);
                    editor.putString("Num2",snum2);
                    editor.putString("Num3",snum3);
                    System.out.println("done");
                    editor.commit();

                    startService(in);

                }
            }
        });
    }
}
