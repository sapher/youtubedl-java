package com.sapher.youtubedl;

@FunctionalInterface
public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}
