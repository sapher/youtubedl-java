package com.sapher.youtubedl;

public class YoutubeDLResponse {
    private String command;
    private int exitCode;
    private String out;
    private String err;


    public YoutubeDLResponse(String command, int exitCode, String out, String err) {
        this.command = command;
        this.exitCode = exitCode;
        this.out = out;
        this.err = err;
    }
}
