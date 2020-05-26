package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingScreen extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsgetstarted,skip,next;
    Animation animation;
    int currentpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding_screen);
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsgetstarted = findViewById(R.id.get_started_btn);
        letsgetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(OnBoardingScreen.this,LoginActiivity.class);
                startActivity(a);

            }
        });
        skip = findViewById(R.id.skip_btn_onBoarding);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(OnBoardingScreen.this,LoginActiivity.class);
                startActivity(a);
            }
        });
        next = findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentpos + 1);

            }
        });

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position)
    {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i =0;i<dots.length;i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.colorred));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentpos = position;
            if(position == 0)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 1)
            {
                letsgetstarted.setVisibility(View.INVISIBLE);
            }
            else
            {
                animation = AnimationUtils.loadAnimation(OnBoardingScreen.this,R.anim.bottom_anim);
                letsgetstarted.setAnimation(animation);
                letsgetstarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}

