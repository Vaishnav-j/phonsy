package com.example.vaishnavj.phonesy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.id.edit;

public class Nmember extends AppCompatActivity {

    EditText e1,e2;
    Button b;
    String s1,s2;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nmember);
        e1=(EditText)findViewById(R.id.editText7);
        e2=(EditText)findViewById(R.id.editText8);
        b=(Button)findViewById(R.id.button7);
       // sharedpreferences = this.getSharedPreferences("Phone", Context.MODE_PRIVATE);
      // final SharedPreferences.Editor editor = sharedpreferences.edit();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                if(s1.equals(s2))
                {
                  //  editor.putString("pass",s1);
                   // editor.commit();
                }
                else
                    e2.setError("Password Mismatch");
            }

        });
    }
}
