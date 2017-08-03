package com.example.vaishnavj.phonesy;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public TextView t1, t2;
    public EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    public String Phone1 ;


    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // t2=(TextView)findViewById(R.id.y);               // initializing views
        b2=(Button) findViewById(R.id.button2);
        ed1=(EditText) findViewById(R.id.editText);
        ed2=(EditText) findViewById(R.id.editText2);
        ed3=(EditText) findViewById(R.id.editText3);
        ed4=(EditText) findViewById(R.id.editText4);
        ed5=(EditText) findViewById(R.id.editText5);

        sharedpreferences = this.getSharedPreferences( "Phone",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        String phone  = sharedpreferences.getString("phoneKey1",null);
        String num[] = new String[5];
        num[0]=" ";
        num[1]=" ";
        num[2]=" ";
        num[3]=" ";
        num[4]=" ";
        num[0] = sharedpreferences.getString("phoneKey1",null);                              //retrieving numbers from database
        num[1] = sharedpreferences.getString("phoneKey2",null);
        num[2] = sharedpreferences.getString("phoneKey3",null);
        num[3] = sharedpreferences.getString("phoneKey4",null);
        num[4] = sharedpreferences.getString("phoneKey5",null);

          ed1.setText(num[0]);
          ed2.setText(num[1]);
          ed3.setText(num[2]);
          ed4.setText(num[3]);
          ed5.setText(num[4]);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){                                                       // on save button click
                editor.putString("phoneKey1", ed1.getText().toString());                     //saves numbers to database
                editor.putString("phoneKey2", ed2.getText().toString());
                editor.putString("phoneKey3", ed3.getText().toString());
                editor.putString("phoneKey4", ed4.getText().toString());
                editor.putString("phoneKey5", ed5.getText().toString());
                editor.commit();

                Toast.makeText(getApplicationContext(),"saved", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
