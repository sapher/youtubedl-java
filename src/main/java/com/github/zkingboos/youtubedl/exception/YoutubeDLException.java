package com.github.zkingboos.youtubedl.exception;

/**
 * YoutubeDL Exception
 */
public class YoutubeDLException extends Exception {

    /**
     * Exception message
     */
    private final String message;

    /**
     * Construct YoutubeDLException with a message
     *
     * @param message exception message error
     */
    public YoutubeDLException(String message) {
        this.message = message;
    }

    public YoutubeDLException(String message, Object... objects) {
        this(String.format(message, objects));
    }

    /**
     * Construct YoutubeDLException from another exception
     *
     * @param e Any exception
     */
    public YoutubeDLException(Exception e) {
        message = e.getMessage();
    }

    /**
     * Get exception message
     *
     * @return exception message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
