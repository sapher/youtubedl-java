package com.github.zkingboos.youtubedl;

import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class YoutubeOption {

    @Getter
    private final Map<String, String> options;

    public YoutubeOption() {
        this.options = new LinkedHashMap<>();
    }

    public YoutubeRequest option(@NonNull String key, String value) {
        if (value != null) value = value.trim();
        options.put(key.trim(), value);
        return (YoutubeRequest) this;
    }

    public YoutubeRequest option(@NonNull String key) {
        return option(key, null);
    }

    public YoutubeRequest option(@NonNull String key, int value) {
        return option(key, String.valueOf(value));
    }

    public YoutubeRequest help() {
        return option("help");
    }

    public YoutubeRequest version() {
        return option("version");
    }

    public YoutubeRequest update() {
        return option("update");
    }

    public YoutubeRequest abortOnError() {
        return option("abort-on-error");
    }

    public YoutubeRequest limitRate(int rate) {
        return option("limit-rate", rate);
    }

    public YoutubeRequest playlistRandom() {
        return option("playlist-random");
    }

    public YoutubeRequest playlistReverse() {
        return option("playlist-reverse");
    }

    public YoutubeRequest quiet() {
        return option("quiet");
    }

    public YoutubeRequest noWarnings() {
        return option("no-warnings");
    }

    public YoutubeRequest skipDownload() {
        return option("skip-download");
    }

    public YoutubeRequest noProgress() {
        return option("no-progress");
    }

    public YoutubeRequest encoding(@NonNull String encoding) {
        return option("encoding", encoding);
    }

    public YoutubeRequest keepVideo() {
        return option("keep-video");
    }

    public YoutubeRequest output(@NonNull String template) {
        return option("output", template);
    }

    public YoutubeRequest retries(int retries) {
        return option("retries", retries);
    }

    public YoutubeRequest flatPlaylist() {
        return option("flat-playlist");
    }

    public YoutubeRequest playlistStart(int start) {
        return option("playlist-start", start);
    }

    public YoutubeRequest playlistEnd(int end) {
        return option("playlist-end", end);
    }

    public YoutubeRequest id() {
        return option("id");
    }

    public YoutubeRequest audioQuality(int quality) {
        return option("audio-quality", quality);
    }

    public YoutubeRequest extractAudio() {
        return option("extract-audio");
    }

    public YoutubeRequest audioFormat(@NonNull String format) {
        return option("audio-format", format);
    }

    public YoutubeRequest format(@NonNull String format) {
        return option("format", format);
    }

    public YoutubeRequest noCacheDir() {
        return option("no-cache-dir");
    }

    public YoutubeRequest maxDownload(int maximum) {
        return option("max-download", maximum);
    }

    public YoutubeRequest noPlaylist() {
        return option("no-playlist");
    }

    public YoutubeRequest yesPlaylist() {
        return option("yes-playlist");
    }

    public YoutubeRequest printJson() {
        return option("print-json");
    }

    public YoutubeRequest dumpJson() {
        return option("dump-json");
    }
}
