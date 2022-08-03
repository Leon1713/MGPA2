package com.sidm.mgp2021;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

public class Gameoverscreen extends Activity implements View.OnClickListener, StateBase {
    TextView gameovertext;
    Button btn_back_gameover;
    Typeface typeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gameoverscreen);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/digital-7.ttf");

        gameovertext = (TextView) findViewById(R.id.gameovertext);
        gameovertext.setTypeface(typeface);

        btn_back_gameover = (Button)findViewById(R.id.btn_back_gameover);
        btn_back_gameover.setOnClickListener(this); //Set Listener to this button --> Back Button


        StateManager.Instance.AddState(new Gameoverscreen());

        InputNameDialogFragment inputNameDialogFragment = new InputNameDialogFragment();
        inputNameDialogFragment.show(this.getFragmentManager(), "Instance");
        //ScoreManager.instance.addData(ScoreManager.instance.getName(),Integer.toString(ScoreManager.instance.score));


    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();


        if (v == btn_back_gameover)
        {
            //clean Entitymanager and reinit everything
            EntityManager.Instance.Clean();
            StateManager.Instance.ChangeState("Mainmenu");
            intent.setClass(this, Mainmenu.class);
        }
        startActivity(intent);

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
        return "Gameoverscreen";
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
