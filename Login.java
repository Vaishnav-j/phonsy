package com.example.vaishnavj.phonesy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
String pass;
    EditText e;
    Button b;
    ImageView iv;
    TextView t;
    String s;
   // SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t=(TextView)findViewById(R.id.textView);
        e=(EditText)findViewById(R.id.editText6);
        b=(Button)findViewById(R.id.button6);
        iv=(ImageView)findViewById(R.id.imageView4);
        //sharedpreferences = this.getSharedPreferences("Phone", Context.MODE_PRIVATE);
        //final SharedPreferences.Editor editor = sharedpreferences.edit();
        //editor.putString(pass,"000");
        //editor.commit();
        //initialze();
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                   /* s=e.getText().toString();
                    if (s.equals("999")) {
                        t.setText("Not a member ? SignUp");
                        t.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(getApplicationContext(), Nmember.class);
                                startActivity(in);
                            }

                        });
                    }
*/

                //if(s.equals("000"))
                {
                    Intent intent = new Intent(Login.this,Start_page.class);
                    startActivity(intent);
                }
                //else
                {
                  //  e.setError("Invalid password");
                }

            }
        });
    }
}
