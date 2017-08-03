package com.example.vaishnavj.phonesy;

import android.app.Application;

/**
 * Created by Vaishnav J on 13-03-2017.
 */

public class Number extends Application {

    public String num1;
    public String num2;
    public String num3;
    public String num4;
    public String num5;
    boolean on ;
    public void setter(boolean b)
    {
        on = b;
    }
    public boolean getter()
    {
        return on;
    }

    public String getnum1() {
        return num1;
    }
    public String getnum2() {
        return num2;
    }
    public String getnum3() {
        return num3;
    }
    public String getnum4() {
        return num4;
    }
    public String getnum5() {
        return num5;
    }

    public void setnum1(String someVariable) {
        this.num1 = someVariable;
    }
    public void setnum2(String someVariable) {
        this.num2 = someVariable;
    }
    public void setnum3(String someVariable) {
        this.num3 = someVariable;
    }
    public void setnum4(String someVariable) {
        this.num4 = someVariable;
    }
    public void setnum5(String someVariable) {
        this.num5 = someVariable;
    }
}
