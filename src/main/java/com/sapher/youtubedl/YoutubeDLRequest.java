package com.sapher.youtubedl;

import java.util.*;

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
    private Map<String, String> options = new HashMap<String, String>();

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

    public void setOption(String key) {
        options.put(key, null);
    }

    public void setOption(String key, String value) {
        options.put(key, value);
    }

    public void setOption(String key, int value) {
        options.put(key, String.valueOf(value));
    }

    /**
     * Constructor
     */
    public YoutubeDLRequest() {

    }

    /**
     * Construct a request with a videoUrl
     * @param url
     */
    public YoutubeDLRequest(String url) {
        this.url = url;
    }

    /**
     * Construct a request with a videoUrl and working directory
     * @param url
     * @param directory
     */
    public YoutubeDLRequest(String url, String directory) {
        this.url = url;
        this.directory = directory;
    }

    /**
     * Transform options to a string that the executable will execute
     * @return Command string
     */
    protected String buildOptions() {

        StringBuilder builder = new StringBuilder();

        // Set Url
        if(url != null)
            builder.append(url + " ");

        // Build options strings
        Iterator it = options.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry option = (Map.Entry) it.next();

            String name = (String) option.getKey();
            String value = (String) option.getValue();

            if(value == null) value = "";

            String optionFormatted = String.format("--%s %s", name, value).trim();
            builder.append(optionFormatted + " ");

            it.remove();
        }

        return builder.toString().trim();
    }
}
