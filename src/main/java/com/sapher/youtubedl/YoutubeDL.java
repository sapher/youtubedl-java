package com.sapher.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoThumbnail;
import com.sapher.youtubedl.utils.StreamGobbler;
import com.sapher.youtubedl.utils.StreamProcessExtractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
    private static String[] EXECUTABLE_PATH = new String[]{"youtube-dl"};
    private static Consumer<Map<String, String>> ENVIRONMENT_CONSUMER;

    /**
     * Execute youtube-dl request
     * @param request request object
     * @return response object
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
        return execute(request, null);
    }

    /**
     * Execute youtube-dl request
     * @param request request object
     * @param callback callback
     * @return response object
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request, DownloadProgressCallback callback) throws YoutubeDLException {
        final String[] command = request.buildCommandLine(EXECUTABLE_PATH);
        final String directory = request.getDirectory();
        final Map<String, String> options = request.getOption();

        final YoutubeDLResponse youtubeDLResponse;
        final Process process;
        final int exitCode;
        final StringBuffer outBuffer = new StringBuffer(); //stdout
        final StringBuffer errBuffer = new StringBuffer(); //stderr
        long startTime = System.nanoTime();

        final ProcessBuilder processBuilder = new ProcessBuilder(command);

        // Define directory if one is passed
        if(directory != null){
            processBuilder.directory(new File(directory));
        }

        if(ENVIRONMENT_CONSUMER != null) {
            ENVIRONMENT_CONSUMER.accept(processBuilder.environment());
        }

        try {
            process = processBuilder.start();
        } catch (IOException exception) {
            throw new YoutubeDLException(exception);
        }

        final InputStream outStream = process.getInputStream();
        final InputStream errStream = process.getErrorStream();

        final StreamProcessExtractor stdOutProcessor = new StreamProcessExtractor(outBuffer, outStream, callback);
        final StreamGobbler stdErrProcessor = new StreamGobbler(errBuffer, errStream);

        try {
            stdOutProcessor.join();
            stdErrProcessor.join();
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
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
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
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static VideoInfo getVideoInfo(String url) throws YoutubeDLException  {

        // Build request
        YoutubeDLRequest request = new YoutubeDLRequest(url);
        request.setOption("dump-json");
        request.setOption("no-playlist");
        YoutubeDLResponse response = YoutubeDL.execute(request);

        // Parse result
        ObjectMapper objectMapper = new ObjectMapper();
        VideoInfo videoInfo;

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
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static List<VideoFormat> getFormats(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.formats;
    }

    /**
     * List thumbnails
     * @param url Video url
     * @return list of thumbnail
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.thumbnails;
    }

    /**
     * List categories
     * @param url Video url
     * @return list of category
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static List<String> getCategories(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.categories;
    }

    /**
     * List tags
     * @param url Video url
     * @return list of tag
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public static List<String> getTags(String url) throws YoutubeDLException {
        VideoInfo info = getVideoInfo(url);
        return info.tags;
    }

    /**
     * Get command executable or path to the executable
     * @return path string
     */
    public static String[] getExecutablePath(){
        return EXECUTABLE_PATH;
    }

    /**
     * Set environment variables parser
     *
     * @param environment consumer to set env variables
     */
    public static void setEnvironmentConsumer(Consumer<Map<String, String>> environment) {
        ENVIRONMENT_CONSUMER = environment;
    }

    /**
     * Set path to use for the command
     * @param path String path to the executable
     */
    public static void setExecutablePath(String... path){
        EXECUTABLE_PATH = path;
    }
}
