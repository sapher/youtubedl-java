package com.sapher.youtubedl;

public interface DownloadProgressCallback {
    void onVideoUpdate(int videoNumber, int videoCount);
    void onProgressUpdate(float progress, long etaInSeconds);
}
