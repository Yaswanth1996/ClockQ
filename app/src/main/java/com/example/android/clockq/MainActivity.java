package com.example.android.clockq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void checkInputs(View view){
        ClockDisplay cd = new ClockDisplay(this);
        //cd.getInputsEditText();
        cd.checkValues(view);
    }


   }
