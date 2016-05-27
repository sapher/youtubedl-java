package com.sapher.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoSubtitle;
import com.sapher.youtubedl.mapper.VideoThumbnail;
import com.sapher.youtubedl.utils.StreamGobbler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class YoutubeDL {
    public static final String executableName = "youtube-dl";

    private static String buildCommand(String command) {
        return String.format("%s %s", executableName, command);
    }

    public static YoutubeDLResponse execute(String command, String directory) throws YoutubeDLException {

        YoutubeDLResponse youtubeDLResponse;
        Process process;
        int exitCode;
        StringBuffer outBuffer = new StringBuffer();
        StringBuffer errBuffer = new StringBuffer();
        long startTime = System.nanoTime();

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

        int elapsedTime = (int) ((System.nanoTime() - startTime) / 1000000);

        youtubeDLResponse = new YoutubeDLResponse(command, directory, exitCode , elapsedTime, outBuffer.toString(), errBuffer.toString());

        return youtubeDLResponse;
    }

    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
        String command = request.buildOptions();
        return execute(buildCommand(command), request.getDirectory());
    }

    /**
     * Retrieve all information available on a video
     * @param url Video url
     * @return Video info
     * @throws YoutubeDLException
     */
    public static VideoInfo getVideoInfo(String url) throws YoutubeDLException  {

        // Build request
        YoutubeDLRequest request = new YoutubeDLRequest(url);
        request.setDumpJson(true);
        YoutubeDLResponse response = YoutubeDL.execute(request);

        // Parse result
        ObjectMapper objectMapper = new ObjectMapper();
        VideoInfo videoInfo = null;

        try {
            videoInfo = objectMapper.readValue(response.getOut(), VideoInfo.class);
        } catch (IOException e) {
            throw new YoutubeDLException("Unable to parse video information: " + e.getMessage());
        }

        return videoInfo;
    }

    public static List<VideoFormat> getFormats(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.formats;
    }

    public static List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.thumbnails;
    }

    public static List<String> getCategories(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.categories;
    }

    public static List<String> getTags(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.tags;
    }

    /**public static List<VideoSubtitle> getSubtitles(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.subtitles;
    }**/

    /**public static void d(String url, String dir, String format, int quality, String output) throws YoutubeDLException {
        YoutubeDLRequest request = new YoutubeDLRequest(dir, url);
        request.setDirectory(dir);
        request.setExtractAudio(true);
        request.setFormat(format);
        request.setAudioQuality(quality);
        request.setOutput(output);

        YoutubeDL.execute(request);
    }**/
}
