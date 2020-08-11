package com.example.backgroundprocess_and_loading;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();
    private TextView tv_today_number;
    private ImageView iv_tremble_bracelet_left;
    private ImageView iv_tremble_bracelet_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        init_button();

    }

    public void init_button(){
        tv_today_number = findViewById(R.id.tv_today_number);
    }

    public void onClick(View view) throws InterruptedException {

        LoadingThrembleBracelet();
        provoke_thread_get_number();
    }

    public void LoadingThrembleBracelet(){
        iv_tremble_bracelet_left = (ImageView) findViewById(R.id.tremble_bracelet_left);
        iv_tremble_bracelet_right = (ImageView) findViewById(R.id.tremble_bracelet_right);

        GlideDrawableImageViewTarget gifImage_tremble_bracelet_left = new GlideDrawableImageViewTarget(iv_tremble_bracelet_left);
        Glide.with(this).load(R.drawable.left1).into(gifImage_tremble_bracelet_left);

        GlideDrawableImageViewTarget gifImage_tremble_bracelet_right = new GlideDrawableImageViewTarget(iv_tremble_bracelet_right);
        Glide.with(this).load(R.drawable.right1).into(gifImage_tremble_bracelet_right);
    }

    public void provoke_thread_get_number() throws InterruptedException {

        BackgroundProcessRunnable background_runnable = new BackgroundProcessRunnable();
        Thread background_thread = new Thread(background_runnable, "BackgroundProcessRunnable");
        background_thread.start();

    }

    public void ThreadResult(final String result){

        Log.d(TAG, "ThreadResult: "+ result);

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        tv_today_number.setText(result);
                        iv_tremble_bracelet_left.setVisibility(View.GONE);
                        iv_tremble_bracelet_right.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public class BackgroundProcessRunnable implements Runnable{

        private String BackgroundProcessResult = "";

        @Override
        public void run() {
            try {
                BackgroundProcessResult = BackgroundProcess.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                Log.d(TAG, "BackgroundProcessRunnable: "+ BackgroundProcessResult);
                ThreadResult(BackgroundProcessResult);
            }
        }

    }


}
