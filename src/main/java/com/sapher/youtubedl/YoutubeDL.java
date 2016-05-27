package com.sapher.youtubedl;

import com.sapher.youtubedl.utils.StreamGobbler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class YoutubeDL {
    public static final String executableName = "youtube-dl";

    public static String buildCommand(String command) {
        return String.format("%s %s", executableName, command);
    }

    public static YoutubeDLResponse execute(String command, String directory) throws YoutubeDLException {

        YoutubeDLResponse youtubeDLResponse;
        Process process;
        int exitCode;
        StringBuffer outBuffer = new StringBuffer();
        StringBuffer errBuffer = new StringBuffer();

        // TODO A nice place to break everything
        String[] split = command.split(" ");

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

        youtubeDLResponse = new YoutubeDLResponse(command, directory, exitCode, outBuffer.toString(), errBuffer.toString());

        return youtubeDLResponse;
    }

    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
        String command = request.buildOptions();
        return execute(buildCommand(command), request.getDirectory());
    }
}
