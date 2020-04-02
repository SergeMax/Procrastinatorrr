package com.example.procrastinator;

import android.view.animation.AlphaAnimation;
import android.widget.TextView;



public class Animation {
    int i = 0;

    public static void animFonduOpacity(int delay, TextView textView){
        AlphaAnimation animation1 = new AlphaAnimation(0f, 1.0f);
        animation1.setDuration(1000);
        animation1.setStartOffset(delay);
        animation1.setFillAfter(true);
        textView.startAnimation(animation1);
    }


}
