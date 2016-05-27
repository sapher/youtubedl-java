package com.sapher.youtubedl;

import java.util.List;

public class YoutubeDLResponse {

    private String command;
    private int exitCode;
    private String out;
    private String err;
    private List<String> filePaths;
    private String directory;

    public YoutubeDLResponse(String command, String directory, int exitCode, String out, String err) {
        this.command = command;
        this.directory = directory;
        this.exitCode = exitCode;
        this.out = out;
        this.err = err;
    }

    public String getCommand() {
        return command;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getOut() {
        return out;
    }

    public String getErr() {
        return err;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public String getDirectory() {
        return directory;
    }
}
