package com.example.simple_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private MusicListFragment music_list_fragment;
    private MovieListFragment movie_list_fragment;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        music_list_fragment = new MusicListFragment();
        movie_list_fragment = new MovieListFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, music_list_fragment).commitAllowingStateLoss();
    }

    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_fragment_music:
                transaction.replace(R.id.frameLayout, music_list_fragment).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment_movie:
                transaction.replace(R.id.frameLayout, movie_list_fragment).commitAllowingStateLoss();
                break;
        }
    }
}
