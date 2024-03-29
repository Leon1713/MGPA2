package com.sidm.mgp2021;

import android.app.Activity;
import android.app.PendingIntent;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

// Created by TanSiewLan2021

public class Shop extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //Define buttons
//    private Button btn_start;
      private Button btn_back_shop;
//    private Button btn_setting;
//    private Button btn_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.shop);



        btn_back_shop = (Button)findViewById(R.id.btn_back_shop);
        btn_back_shop.setOnClickListener(this); //Set Listener to this button --> Back Button



        StateManager.Instance.AddState(new Mainmenu());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();


        if (v == btn_back_shop)
        {
            StateManager.Instance.ChangeState("Mainmenu");
            super.finish();
        }

        if(super.isFinishing())
        {
            StateManager.Instance.ChangeState("Mainmenu");
        }
        //startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        // your code.
        StateManager.Instance.ChangeState("Mainmenu");
        super.finish();
    }

    @Override
    public void Render(Canvas _canvas) {
    }

    @Override
    public void OnEnter(SurfaceView _view) {
    }

    @Override
    public void OnExit() {
    }

    @Override
    public void Update(float _dt) {
    }

    @Override
    public String GetName() {
        return "Shop";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
