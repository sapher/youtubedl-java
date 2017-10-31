package com.sapher.youtubedl;

public interface DownloadProgressCallback {
    void onProgressUpdate(float progress, long etaInSeconds);
}
