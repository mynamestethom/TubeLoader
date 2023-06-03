package com.example.tubeloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.example.tubeloader.Activityapp.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen();
        setContentView(R.layout.splash_screen);

        motionLayout = findViewById(R.id.motion_layout);
        motionLayout.transitionToStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                motionLayout.transitionToEnd();
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 2000);
    }

    public void FullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}