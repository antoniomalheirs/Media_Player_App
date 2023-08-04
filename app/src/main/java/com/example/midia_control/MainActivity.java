package com.example.midia_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Barravolume();
    }

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer player)
        {
            Liberarec();
        }
    };

    @Override
    protected void onStop()
    {
        Liberarec();
        super.onStop();
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

    private void Liberarec()
    {
        if (player != null)
        {
            player.stop();
            player.release();
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();

        if (player != null && player.isPlaying())
        {
            player.stop();
            player.release();
        }
    }

    protected void Barravolume()
    {
        int volumeMax = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int volumeAt = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        prog.setMax(volumeMax);
        prog.setProgress(volumeAt);

        prog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });
    }
}