package com.plepler.remote;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;

public class WriterHandler extends Thread
{
    private byte message = 0;
    private PrintWriter pw;
    private Socket sock;

    public WriterHandler(Socket s)
    {
        this.sock = s;
    }

    @Override
    public void run()
    {

        try
        {
            pw = new PrintWriter(sock.getOutputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        while (true)
        {
            if(message != 0)
            {
                pw.write(new String(String.valueOf(message)));
                pw.flush();
                message = 0;
            }
        }
    }

    public void setMessage(byte message)
    {
        this.message = message;
    }
}
