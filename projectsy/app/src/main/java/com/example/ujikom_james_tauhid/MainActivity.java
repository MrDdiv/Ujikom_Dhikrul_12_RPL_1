package com.example.ujikom_james_tauhid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.app.Activity;


public class MainActivity extends AppCompatActivity {
    public MediaPlayer musik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playsoun();
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }finally {
                    startActivity(new Intent(MainActivity.this,login.class));
                    finish();
                }
            }
        };
        thread.start();
    }

    private void playsoun() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        musik = MediaPlayer.create(this,R.raw.music);
        musik.setLooping(false);
        musik.start();
    }
}