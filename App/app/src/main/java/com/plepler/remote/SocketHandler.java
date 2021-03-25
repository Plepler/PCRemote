package com.plepler.remote;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.util.Stack;
import java.util.concurrent.Callable;

public class SocketHandler extends Thread
{
    private final String IP = "192.168.0.131";


    private final static SocketHandler socketHandler = new SocketHandler();
    private Socket sock;
    private WriterHandler writerHandler;
    private Stack<Callable<String>> calls = new Stack<Callable<String>>();
    private boolean hasHandlers = false;

    private SocketHandler()
    {

    }

    // Getters
    public Callable<String> getCall()
    {
        return calls.peek();
    }
    public static SocketHandler getInstance()
    {
        return socketHandler;
    }

    // Setters
    public void setCall(Callable<String> call)
    {
        this.calls.push(call);
    }

    @Override
    public void run()
    {

        try
        {
            Log.d("MyDebug", "Trying to reconnect..");
            // Create and connect socket, create writer to socket
            sock = new Socket(IP, 9000);
            Log.d("MyDebug", "Connected");

            writerHandler = new WriterHandler(sock);
            writerHandler.start();
        }
        catch (IOException e)
        {
            Log.d("MyDebug", "Connection error");
            e.printStackTrace();
            run();
        }
        catch (Exception e)
        {
            Log.d("MyDebug", "Connection error");
            e.printStackTrace();
            run();
        }

    }


    public synchronized void sendMessage(byte message)
    {
        writerHandler.setMessage(message);
    }

    public void popCall()
    {
        if (!calls.empty())
        {
            this.calls.pop();
        }
    }
}
