package it.isig.fasogare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Runnable runnable3sec=new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };

        Handler myHandler=new Handler();
        myHandler.postDelayed(runnable3sec,1000);
    }

    public void nextActivity(){

        Intent myhome=new Intent(this,main.class);

        startActivity(myhome);
    }
}
