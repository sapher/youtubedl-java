package com.sapher.youtubedl;

import com.sapher.youtubedl.utils.StreamGobbler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YoutubeDL {
    public static final String executableName = "youtube-dl";

    public static YoutubeDLResponse execute(String command, String directory) throws YoutubeDLException {

        YoutubeDLResponse youtubeDLResponse;
        Process process;
        int exitCode;
        StringBuffer outBuffer = new StringBuffer();
        StringBuffer errBuffer = new StringBuffer();

        String cmd = String.format("%s %s", executableName, command);

        // TODO Fuck you
        String[] split = cmd.split(" ");

        ProcessBuilder processBuilder = new ProcessBuilder(split);

        // Define directory if one is passed
        if(directory != null)
            processBuilder.directory(new File(directory));

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new YoutubeDLException(e);
        }

        InputStream outStream = process.getInputStream();
        InputStream errStream = process.getErrorStream();

        new StreamGobbler(outBuffer, outStream);
        new StreamGobbler(errBuffer, errStream);

        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {

            // process exited for some reason
            throw new YoutubeDLException(e);
        }

        youtubeDLResponse = new YoutubeDLResponse(command, exitCode, outBuffer.toString(), errBuffer.toString());

        return youtubeDLResponse;
    }

    public static YoutubeDLResponse execute(String command) throws YoutubeDLException {
        return execute(command, null);
    }

    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
        String command = request.buildCommandString();
        return execute(command, request.getDirectory());
    }
}
