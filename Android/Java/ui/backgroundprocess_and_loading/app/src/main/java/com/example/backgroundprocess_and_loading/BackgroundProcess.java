package com.example.backgroundprocess_and_loading;

import android.util.Log;

import java.util.Random;

public class BackgroundProcess {

    private static String TAG  = BackgroundProcess.class.getName();;

    public static String run() throws InterruptedException {

        Random rd = new Random();
        String result = "";
        for (int i = 0; i<6; i++){
            if (i != 0) {
                 result += (", ");
            }
            result += rd.nextInt(45);
        }

        Thread.sleep(5000);
        Log.d(TAG, result);
        return result;
    }
}
