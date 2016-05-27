package com.sapher.youtubedl;

public class YoutubeDLException extends Exception {

    private String message;

    public YoutubeDLException(String message) {
        this.message = message;
    }

    public YoutubeDLException(Exception e) {
        message = e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
