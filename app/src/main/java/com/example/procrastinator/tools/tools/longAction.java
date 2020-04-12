package com.example.procrastinator.tools.tools;

import android.util.Log;

public class longAction {


    public static Long executeLongActionDuring7seconds(){

        Log.e("TAG", "Long action is starting...");

        Long endTime = System.currentTimeMillis() + 7000;
        while (System.currentTimeMillis() <  endTime) {
        System.out.println("...........longAction en cours");
        }

        Log.e("TAG", "Long action is finished !");

        return endTime;
    }
}
