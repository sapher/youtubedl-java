package com.sapher.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoThumbnail;
import com.sapher.youtubedl.utils.StreamGobbler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>Provide an interface for youtube-dl executable</p>
 *
 * <p>
 *     For more information on youtube-dl, please see
 *     <a href="https://github.com/rg3/youtube-dl/blob/master/README.md">YoutubeDL Documentation</a>
 * </p>
 */
public class YoutubeDL {

    /**
     * Youtube-dl executable name
     */
    protected static final String executableName = "youtube-dl";

    /**
     * Append executable name to command
     * @param command Command string
     * @return Command string
     */
    protected static String buildCommand(String command) {
        return String.format("%s %s", executableName, command);
    }

    /**
     * Execute youtube-dl request
     * @param request request object
     * @return response object
     * @throws YoutubeDLException
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {

        String command = buildCommand(request.buildOptions());
        String directory = request.getDirectory();
        Map<String, String> options = request.getOption();

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

        String out = outBuffer.toString();
        String err = errBuffer.toString();

        if(exitCode > 0) {
            throw new YoutubeDLException(err);
        }

        int elapsedTime = (int) ((System.nanoTime() - startTime) / 1000000);

        youtubeDLResponse = new YoutubeDLResponse(command, options, directory, exitCode , elapsedTime, out, err);

        return youtubeDLResponse;
    }


    /**
     * Get youtube-dl executable version
     * @return version string
     * @throws YoutubeDLException
     */
    public static String getVersion() throws YoutubeDLException {
        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("version");
        return YoutubeDL.execute(request).getOut();
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
        request.setOption("dump-json");
        request.setOption("no-playlist");
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

    /**
     * List formats
     * @param url Video url
     * @return list of formats
     * @throws YoutubeDLException
     */
    public static List<VideoFormat> getFormats(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.formats;
    }

    /**
     * List thumbnails
     * @param url Video url
     * @return list of thumbnail
     * @throws YoutubeDLException
     */
    public static List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.thumbnails;
    }

    /**
     * List categories
     * @param url Video url
     * @return list of category
     * @throws YoutubeDLException
     */
    public static List<String> getCategories(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.categories;
    }

    /**
     * List tags
     * @param url Video url
     * @return list of tag
     * @throws YoutubeDLException
     */
    public static List<String> getTags(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.tags;
    }
}
