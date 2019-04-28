package com.test.ux.animation;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView circle;
    ImageView[] view=new ImageView[10];
    RelativeLayout relativeLayout;
    int i=0,k=0;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circle=findViewById(R.id.circ);
        relativeLayout=findViewById(R.id.myLayout);
        x=relativeLayout.getRight();
        final ScaleAnimation growAnim = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        final ScaleAnimation shrinkAnim = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        growAnim.setDuration(500);
        shrinkAnim.setDuration(500);

        circle.setAnimation(growAnim);
        growAnim.start();

        growAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                circle.setAnimation(shrinkAnim);
                shrinkAnim.start();
                view[i]=new ImageView(getApplicationContext());
                view[i].setImageDrawable(circle.getDrawable());
                view[i].setX(circle.getX());
                view[i].setY(circle.getY());
                relativeLayout.addView(view[i]);
                view[i].setVisibility(View.VISIBLE);
                    view[i].animate().x(getScreenWidth()-500).setDuration((getScreenWidth()-500)*3);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view[k].animate().scaleX(0.1f).setDuration(500).scaleY(0.1f).setDuration(500);
                                k++;
                                if(k==9){
                                    k=0;
                                }
                            }
                        },((getScreenWidth()-500)*25)/10);
                i++;
                if(i==9){
                    i=0;
                }
            }
        });
        shrinkAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                circle.setAnimation(growAnim);
                growAnim.start();

            }
        });

    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}

