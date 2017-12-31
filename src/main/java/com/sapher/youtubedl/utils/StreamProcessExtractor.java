package com.sapher.youtubedl.utils;

import com.sapher.youtubedl.DownloadProgressCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessExtractor extends Thread {
    private static final String GROUP_PERCENT = "percent";
    private static final String GROUP_MINUTES = "minutes";
    private static final String GROUP_SECONDS = "seconds";

    private static final String GROUP_VIDEO_NUMBER = "videoNumber";
    private static final String GROUP_VIDEO_COUNT = "videoCount";

    private BufferedReader streamReader;
    private StringBuffer buffer;
    private final DownloadProgressCallback callback;

    private Pattern p = Pattern.compile("\\[download\\]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)");
    private Pattern p2 = Pattern.compile("\\[download\\]\\s+Downloading\\s+video\\s+(?<videoNumber>\\d+)\\s+of\\s+(?<videoCount>\\d+)");

    public StreamProcessExtractor(StringBuffer buffer, InputStream stream, DownloadProgressCallback callback) {
        this.streamReader = new BufferedReader(new InputStreamReader(stream));
        this.buffer = buffer;
        this.callback = callback;
        this.start();
    }

    public void run() {
        try {
            String tmp;

            while((tmp = streamReader.readLine()) != null){
                buffer.append(tmp);
                
                if(callback != null){
                    processOutputLine(tmp);
                }
            }
        } catch (IOException ignored) {
        }
    }

    private void processOutputLine(String line) {
        Matcher m = p.matcher(line);
        Matcher m2 = p2.matcher(line);

        if (m.matches()) {
            float progress = Float.parseFloat(m.group(GROUP_PERCENT));
            long eta = convertToSeconds(m.group(GROUP_MINUTES), m.group(GROUP_SECONDS));
            callback.onProgressUpdate(progress, eta);
        }

        if(m2.matches()){
            callback.onVideoUpdate(Integer.parseInt(m2.group(GROUP_VIDEO_NUMBER)), Integer.parseInt(m2.group(GROUP_VIDEO_COUNT)));
        }
    }

    private int convertToSeconds(String minutes, String seconds) {
        return Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds);
    }
}
