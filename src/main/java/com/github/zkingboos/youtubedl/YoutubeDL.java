package com.github.zkingboos.youtubedl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zkingboos.youtubedl.callback.DownloadProgressCallback;
import com.github.zkingboos.youtubedl.entry.playlist.PlaylistInfo;
import com.github.zkingboos.youtubedl.entry.video.VideoFormat;
import com.github.zkingboos.youtubedl.entry.video.VideoInfo;
import com.github.zkingboos.youtubedl.entry.video.VideoThumbnail;
import com.github.zkingboos.youtubedl.exception.YoutubeDLException;
import com.github.zkingboos.youtubedl.stream.StreamGobbler;
import com.github.zkingboos.youtubedl.stream.StreamProcessExtractor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author shaper zkingboos
 */
@Getter
@UtilityClass
public class YoutubeDL {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Youtube-dl executable name
     */
    private String[] executablePath = new String[]{"youtube-dl"};
    private Consumer<Map<String, String>> environmentConsumer;

    public YoutubeRequest from(String url, String directory) {
        return new YoutubeRequest(url, directory);
    }

    public YoutubeRequest from(String url) {
        return from(url, null);
    }

    public YoutubeRequest search(String search, String directory) {
        return from(String.format("ytsearch1:%s", search), directory);
    }

    public YoutubeRequest search(String search) {
        return search(search, null);
    }

    public YoutubeRequest empty(String directory) {
        return from(null, directory);
    }

    public YoutubeRequest empty() {
        return empty(null);
    }

    /**
     * Execute youtube-dl request
     *
     * @param request request object
     * @return response object
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public YoutubeResponse execute(YoutubeRequest request) throws YoutubeDLException {
        return execute(request, null);
    }

    /**
     * Execute youtube-dl request
     *
     * @param request  request object
     * @param callback callback
     * @return response object
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public YoutubeResponse execute(YoutubeRequest request, DownloadProgressCallback callback) throws YoutubeDLException {
        final String[] command = request.buildCommandLine(executablePath);
        final String directory = request.getDirectory();
        final Map<String, String> options = request.getOptions();

        final Process process;
        final int exitCode;
        final StringBuffer outBuffer = new StringBuffer(); //stdout
        final StringBuffer errBuffer = new StringBuffer(); //stderr
        long startTime = System.nanoTime();

        final ProcessBuilder processBuilder = new ProcessBuilder(command);

        // Define directory if one is passed
        if (directory != null) {
            processBuilder.directory(new File(directory));
        }

        if (environmentConsumer != null) {
            environmentConsumer.accept(processBuilder.environment());
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
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            throw new YoutubeDLException(exception);
        }

        String out = outBuffer.toString();
        String err = errBuffer.toString();

        if (exitCode > 0 && !err.isEmpty()) {
            throw new YoutubeDLException(err);
        }

        int elapsedTime = (int) ((System.nanoTime() - startTime) / 1000000);
        return new YoutubeResponse(command, options, directory, exitCode, elapsedTime, out, err);
    }

    public PlaylistInfo getPlaylistInfo(@NonNull YoutubeRequest request) throws YoutubeDLException {
        try {
            final YoutubeResponse response = request.yesPlaylist().printJson().build();
            final String[] strings = response.getOut().trim().split("\n");

            if (strings.length == 0) return null;
            final List<VideoInfo> videoInfoList = new LinkedList<>();

            for (String out : strings) {
                videoInfoList.add(
                  mapper.readValue(out, VideoInfo.class).setRequest(request).setResponse(response)
                );
            }

            return new PlaylistInfo(videoInfoList);
        } catch (IOException exception) {
            throw new YoutubeDLException(exception);
        }
    }

    /**
     * Get youtube-dl executable version
     *
     * @return version string
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public String getVersion() throws YoutubeDLException {
        return empty()
          .version()
          .build()
          .getOut();
    }

    /**
     * Retrieve all information available on a video
     *
     * @param url Video url
     * @return Video info
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public VideoInfo getVideoInfo(@NonNull String url) throws YoutubeDLException {
        try {
            return mapper.readValue(from(url)
              .dumpJson()
              .noPlaylist()
              .build()
              .getOut(), VideoInfo.class
            );
        } catch (IOException exception) {
            throw new YoutubeDLException("Unable to parse video information: %s", exception.getMessage());
        }
    }

    /**
     * List formats
     *
     * @param url Video url
     * @return list of formats
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public List<VideoFormat> getFormats(String url) throws YoutubeDLException {
        return getVideoInfo(url).getFormats();
    }

    /**
     * List thumbnails
     *
     * @param url Video url
     * @return list of thumbnail
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
        return getVideoInfo(url).getThumbnails();
    }

    /**
     * List categories
     *
     * @param url Video url
     * @return list of category
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public List<String> getCategories(String url) throws YoutubeDLException {
        return getVideoInfo(url).getCategories();
    }

    /**
     * List tags
     *
     * @param url Video url
     * @return list of tag
     * @throws YoutubeDLException throws an exception if youtube-dl exit code results in a error
     */
    public List<String> getTags(String url) throws YoutubeDLException {
        return getVideoInfo(url).getTags();
    }

    /**
     * Set path to use for the command
     *
     * @param path String path to the executable
     */
    public void setExecutablePath(String... path) {
        executablePath = path;
    }

    /**
     * Set environment variables parser
     *
     * @param environment consumer to set env variables
     */
    public void setEnvironmentConsumer(Consumer<Map<String, String>> environment) {
        environmentConsumer = environment;
    }
}
