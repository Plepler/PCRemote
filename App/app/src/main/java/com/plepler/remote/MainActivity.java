package com.plepler.remote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    private SocketHandler sh;
    private boolean isPlaying = true;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private ImageButton nexttrackBtn;
    private ImageButton prevtrackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (ImageButton)findViewById(R.id.play);
        pauseBtn = (ImageButton)findViewById(R.id.pause);
        ImageButton nexttrackBtn = (ImageButton)findViewById(R.id.nextrack);
        ImageButton prevtrackBtn = (ImageButton)findViewById(R.id.prevtrack);

        pauseBtn.setVisibility(View.GONE);


        sh = SocketHandler.getInstance();
        sh.start();
    }

    public void playPause(View view)
    {
        byte code = (byte)RequestCodes.PLAYPAUSE;
        sh.sendMessage(code);

        if (isPlaying)
        {
            pauseBtn.setVisibility(View.VISIBLE);
            playBtn.setVisibility(View.GONE);
            isPlaying = !isPlaying;
        }
        else
        {
            pauseBtn.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
            isPlaying = !isPlaying;
        }
    }


    public void nexttrack(View view)
    {
        byte code = (byte)RequestCodes.NEXTTRACK;
        sh.sendMessage(code);
    }

    public void prevtrack(View view)
    {
        byte code = (byte)RequestCodes.PREVTRACK;
        sh.sendMessage(code);
    }

}