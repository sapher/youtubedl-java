package com.sapher.youtubedl;

import java.util.*;
import java.util.Map.Entry;

/**
 * YoutubeDL request
 */
public class YoutubeDLRequest {

    /**
     * Executable working directory
     */
    private String directory;

    /**
     * Video Url
     */
    private String url;

    /**
     * List of executable options
     */
    private final Map<String, String> options = new HashMap<>();

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getOption() {
        return options;
    }

    public YoutubeDLRequest setOption(String key) {
        options.put(key, null);
        return this;
    }

    public YoutubeDLRequest setOption(String key, String value) {
        options.put(key, value);
        return this;
    }

    public YoutubeDLRequest setOption(String key, int value) {
        options.put(key, String.valueOf(value));
        return this;
    }

    /**
     * Empty request constructor
     */
    public YoutubeDLRequest() {
    }

    /**
     * Construct a request with a video url
     *
     * @param url youtube video url
     */
    public YoutubeDLRequest(String url) {
        this(url, null);
    }

    /**
     * Construct a request with a videoUrl and working directory
     *
     * @param url youtube video url
     * @param directory destination output video download and dump data information
     */
    public YoutubeDLRequest(String url, String directory) {
        this.url = url;
        this.directory = directory;
    }

    /**
     * Transform options to a string that the executable will execute
     *
     * @return Command string
     */
    protected String[] buildCommandLine(String... command) {
        final List<String> optionsCommandList = new LinkedList<>();
        if(command.length > 0) {
            optionsCommandList.addAll(Arrays.asList(command));
        }

        for (Entry<String, String> entry : options.entrySet()) {
            optionsCommandList.add("--".concat(entry.getKey().trim()));
            String value = entry.getValue();
            if (value != null) {
                optionsCommandList.add(value.trim());
            }
        }

        // Set url if it's present
        if (url != null) {
            optionsCommandList.add(url);
        }

        return optionsCommandList.toArray(new String[0]);
    }
}
