package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int splash_screen_time = 3500;
    Button skip_main;
    ImageView mainimage;
    TextView appname;
    Animation Topdown,BottomUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Topdown = AnimationUtils.loadAnimation(this,R.anim.top_downanim);
        BottomUp = AnimationUtils.loadAnimation(this,R.anim.bottom_upanim);
        mainimage = findViewById(R.id.imageView);
        appname = findViewById(R.id.appname);
        mainimage.setAnimation(Topdown);
        appname.setAnimation(BottomUp);
        skip_main = findViewById(R.id.skip_btn_main);
        skip_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,OnBoardingScreen.class);
                startActivity(i);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent a = new Intent(MainActivity.this,OnBoardingScreen.class);
                startActivity(a);
            }
        },splash_screen_time);
    }

}