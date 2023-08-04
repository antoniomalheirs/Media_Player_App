package com.example.midia_control;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity
{
    MediaPlayer player;
    AudioManager manager;
    Button play,pause,stop;
    SeekBar prog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(getApplicationContext(),R.raw.american);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        prog = findViewById(R.id.seekBar);
    }
    public void onClick(View v)
    {
        if (v.getId() == R.id.play)
        {
            if (player != null)
            {
                player.start();
            }
        }

        if (v.getId() == R.id.pause)
        {
            if (player != null && player.isPlaying())
            {
                player.pause();
            }
        }

        if (v.getId() == R.id.stop)
        {
            if (player != null && player.isPlaying())
            {
                player.stop();
            }

            player = MediaPlayer.create(getApplicationContext(),R.raw.american);
        }
    }
}