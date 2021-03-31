package com.plepler.remote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    private SocketHandler sh;
    private boolean isPlaying = true;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (ImageButton)findViewById(R.id.play);
        pauseBtn = (ImageButton)findViewById(R.id.pause);
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.alert_text);
        alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                shutdown();
            }
        });
        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });


        pauseBtn.setVisibility(View.GONE);

        // Create socket handler to connect to pc
        sh = SocketHandler.getInstance();
        sh.start();
    }



    public void playPause(View view)
    {
        if(!sendMessage((byte)RequestCodes.PLAYPAUSE))
        {
            return;
        }

        if (isPlaying)
        {
            pauseBtn.setVisibility(View.VISIBLE);
            playBtn.setVisibility(View.GONE);
        }
        else
        {
            pauseBtn.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
        }
        isPlaying = !isPlaying;
    }
    public void nexttrack(View view)
    {
        sendMessage((byte)RequestCodes.NEXTTRACK);
    }
    public void prevtrack(View view)
    {
        sendMessage((byte)RequestCodes.PREVTRACK);
    }
    public void rewind(View view)
    {
        sendMessage((byte)RequestCodes.REWIND);
    }
    public void volup(View view)
    {
        sendMessage((byte)RequestCodes.VOLUP);
    }
    public void voldown(View view)
    {
        sendMessage((byte)RequestCodes.VOLDOWN);
    }
    public void shutdown(View view)
    {
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void shutdown()
    {
        sendMessage((byte)RequestCodes.SHUTDOWN);
    }

    private boolean sendMessage(byte code)
    {
        try
        {
            sh.sendMessage(code);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


}