package com.github.zkingboos.youtubedl.callback;

@FunctionalInterface
public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}
