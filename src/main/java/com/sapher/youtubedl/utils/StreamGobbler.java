package com.sapher.youtubedl.utils;

import com.sapher.youtubedl.callback.OutputCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread {
    private BufferedReader streamReader;
    private StringBuffer buffer;
    private OutputCallback outputCallback;
    private boolean errorCallback;

    public StreamGobbler(StringBuffer buffer, InputStream stream, OutputCallback outputCallback, boolean errorCallback) {
        this.streamReader = new BufferedReader(new InputStreamReader(stream));
        this.buffer = buffer;
        this.outputCallback = outputCallback;
        this.errorCallback = errorCallback;

        start();
    }

    public void run() {
        try {
            String tmp;

            while((tmp = streamReader.readLine()) != null){
                buffer.append(tmp);

                if(outputCallback != null){
                    if(errorCallback){
                        outputCallback.onStdErrLine(tmp);
                    }
                    else{
                        outputCallback.onStdOutLine(tmp);
                    }
                }
            }
        }
        catch (IOException e) {

        }
    }
}