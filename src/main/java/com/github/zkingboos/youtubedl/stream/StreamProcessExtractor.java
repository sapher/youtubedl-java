package com.github.zkingboos.youtubedl.stream;

import com.github.zkingboos.youtubedl.callback.DownloadProgressCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamProcessExtractor extends Thread {

    private final static Pattern PATTERN = Pattern.compile("\\[download]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)");
    private static final String GROUP_PERCENT = "percent";
    private static final String GROUP_MINUTES = "minutes";
    private static final String GROUP_SECONDS = "seconds";

    private final InputStream stream;
    private final StringBuffer buffer;
    private final DownloadProgressCallback callback;


    public StreamProcessExtractor(StringBuffer buffer, InputStream stream, DownloadProgressCallback callback) {
        this.stream = stream;
        this.buffer = buffer;
        this.callback = callback;
        this.start();
    }

    public void run() {
        try {
            StringBuilder currentLine = new StringBuilder();
            int nextChar;
            while ((nextChar = stream.read()) != -1) {
                buffer.append((char) nextChar);
                if (nextChar == '\r' && callback != null) {
                    processOutputLine(currentLine.toString());
                    currentLine.setLength(0);
                    continue;
                }
                currentLine.append((char) nextChar);
            }
        } catch (IOException ignored) {
        }
    }

    private void processOutputLine(String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) return;
        float progress = Float.parseFloat(matcher.group(GROUP_PERCENT));
        long eta = convertToSeconds(matcher.group(GROUP_MINUTES), matcher.group(GROUP_SECONDS));
        callback.onProgressUpdate(progress, eta);
    }

    private int convertToSeconds(String minutes, String seconds) {
        return Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds);
    }
}
