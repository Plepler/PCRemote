package com.plepler.remote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private SocketHandler sh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = SocketHandler.getInstance();
        sh.start();
    }

    public void playPause(View view)
    {
        byte code = (byte)RequestCodes.PLAYPAUSE;
        sh.sendMessage(code);
    }
}