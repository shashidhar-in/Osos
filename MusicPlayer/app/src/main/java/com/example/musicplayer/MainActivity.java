package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private Button nButton;
    private Button stopButton;

    private TextView forwardtime;
    private TextView backwardtime;

    private Handler myHandler = new Handler();


    private MediaPlayer mMediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaPlayer = MediaPlayer.create(this, R.raw.sample);
        mSeekBar = (SeekBar)findViewById(R.id.seekBar);

        mButton = findViewById(R.id.play);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"playing song", LENGTH_SHORT).show();

                mMediaPlayer.start();
                finalTime = mMediaPlayer.getDuration();
                startTime = mMediaPlayer.getCurrentPosition();
                int oneTimeOnly = 0;
                if (oneTimeOnly == 0) {
                    mSeekBar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }


                forwardtime=(TextView) findViewById(R.id.start_time);
                forwardtime.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );


                backwardtime=(TextView) findViewById(R.id.end_time);
                backwardtime.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                mSeekBar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);

            }
        });


        nButton =findViewById(R.id.pause);
        nButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this,"pause", LENGTH_SHORT).show();
            mMediaPlayer.pause();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mMediaPlayer) {
              //  finish(); // finish current activity
                Toast.makeText(MainActivity.this,"i'm done!",LENGTH_SHORT).show();
            }
        });




        mSeekBar.setClickable(false);
        







    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mMediaPlayer.getCurrentPosition();
            forwardtime.setText(String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            mSeekBar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };




}