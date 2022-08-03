package com.sidm.mgp2021;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Settingpage extends Activity implements View.OnClickListener
{
    private Button btn_back_settings;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.settingpage);


        btn_back_settings = (Button)findViewById(R.id.btn_back_settings);
        btn_back_settings.setOnClickListener(this); //Set Listener to this button --> Back Button



    }
    @Override
    //Invoke a callback event in the view
    public void onClick(View v) {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        //Intent intent = new Intent();
        if (v == btn_back_settings) {
         super.finish();
            //   intent.setClass(this, Mainmenu.class);
        }
        //startActivity(intent);
    }

        @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    //End here with the 3 methods for the android life cycle of an activity class
}
