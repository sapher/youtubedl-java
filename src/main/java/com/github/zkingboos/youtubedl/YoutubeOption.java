package com.github.zkingboos.youtubedl;

import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see <a href="https://github.com/ytdl-org/youtube-dl#OPTIONS">youtube-dl options</a>
 */
@SuppressWarnings("unused")
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

    /**
     * Print this help text and exit
     *
     * @return youtube request builder
     */
    public YoutubeRequest help() {
        return option("help");
    }

    /**
     * Print program version and exit
     *
     * @return youtube request builder
     */
    public YoutubeRequest version() {
        return option("version");
    }

    /**
     * Update this program to latest version. Make sure that you have sufficient permissions (run with sudo if needed)
     *
     * @return youtube request builder
     */
    public YoutubeRequest update() {
        return option("update");
    }

    /**
     * Continue on download errors, for example to skip unavailable videos in a playlist
     *
     * @return youtube request builder
     */
    public YoutubeRequest ignoreErrors() {
        return option("ignore-errors");
    }

    /**
     * Abort downloading of further videos (in the playlist or the command line) if an error occurs
     *
     * @return youtube request builder
     */
    public YoutubeRequest abortOnError() {
        return option("abort-on-error");
    }

    /**
     * Maximum download rate in bytes per second (e.g. 50K or 4.2M)
     *
     * @param rate maximum download rate
     * @return youtube request builder
     */
    public YoutubeRequest limitRate(int rate) {
        return option("limit-rate", rate);
    }

    /**
     * Download playlist videos in random order
     *
     * @return youtube request builder
     */
    public YoutubeRequest playlistRandom() {
        return option("playlist-random");
    }

    /**
     * Download playlist videos in reverse order
     *
     * @return youtube request builder
     */
    public YoutubeRequest playlistReverse() {
        return option("playlist-reverse");
    }

    /**
     * Activate quiet mode
     *
     * @return youtube request builder
     */
    public YoutubeRequest quiet() {
        return option("quiet");
    }

    /**
     * Ignore warnings
     *
     * @return youtube request builder
     */
    public YoutubeRequest noWarnings() {
        return option("no-warnings");
    }

    /**
     * Do not download the video
     *
     * @return youtube request builder
     */
    public YoutubeRequest skipDownload() {
        return option("skip-download");
    }

    /**
     * Do not download the video and do not write anything to disk
     *
     * @return youtube request builder
     */
    public YoutubeRequest simulate() {
        return option("simulate");
    }

    /**
     * Do not print progress bar
     *
     * @return youtube request builder
     */
    public YoutubeRequest noProgress() {
        return option("no-progress");
    }

    /**
     * Force the specified encoding (experimental)
     *
     * @param encoding charset encoding
     * @return youtube request builder
     */
    public YoutubeRequest encoding(@NonNull String encoding) {
        return option("encoding", encoding);
    }

    /**
     * Keep the video file on disk after the post-processing the video is erased by default
     *
     * @return youtube request builder
     */
    public YoutubeRequest keepVideo() {
        return option("keep-video");
    }

    /**
     * Output filename template, see the "OUTPUT TEMPLATE" for all the info
     *
     * @param template output template
     * @return youtube request builder
     */
    public YoutubeRequest output(@NonNull String template) {
        return option("output", template);
    }

    /**
     * Number of retries (default is 10), or "infinite" aka Integer.MAX_VALUE
     *
     * @param retries number of retries
     * @return youtube request builder
     */
    public YoutubeRequest retries(int retries) {
        return option("retries", retries);
    }

    /**
     * Do not extract the videos of a playlist, only list them.
     *
     * @return youtube request builder
     */
    public YoutubeRequest flatPlaylist() {
        return option("flat-playlist");
    }

    /**
     * Playlist video to start at (default is 1)
     *
     * @param start point of start entry playlist
     * @return youtube request builder
     */
    public YoutubeRequest playlistStart(int start) {
        return option("playlist-start", start);
    }

    /**
     * Playlist video to end at (default is last)
     *
     * @param end point of latest entry playlist
     * @return youtube request builder
     */
    public YoutubeRequest playlistEnd(int end) {
        return option("playlist-end", end);
    }

    /**
     * Use only video ID in file name
     *
     * @return youtube request builder
     */
    public YoutubeRequest id() {
        return option("id");
    }

    /**
     * Specify ffmpeg/avconv audio quality, insert a value between 0 (better) and 9 (worse) for VBR or a specific bitrate like 128K (default 5)
     *
     * @param quality of audio
     * @return youtube request builder
     */
    public YoutubeRequest audioQuality(int quality) {
        return option("audio-quality", quality);
    }

    /**
     * Encode the video to another format if necessary (currently supported: mp4|flv|ogg|webm|mkv|avi)
     *
     * @param format of recode video
     * @return youtube request builder
     */
    public YoutubeRequest recodeVideo(@NonNull String format) {
        return option("recode-video", format);
    }

    /**
     * Convert video files to audio-only files (requires ffmpeg/avconv and ffprobe/avprobe)
     *
     * @return youtube request builder
     */
    public YoutubeRequest extractAudio() {
        return option("extract-audio");
    }

    /**
     * Specify audio format: "best", "aac", "flac", "mp3", "m4a", "opus", "vorbis", or "wav"
     * "best" by default. No effect without -x
     *
     * @param format of audioformat
     * @return youtube request builder
     */
    public YoutubeRequest audioFormat(@NonNull String format) {
        return option("audio-format", format);
    }

    /**
     * Video format code, see the "FORMAT SELECTION" for all the info
     *
     * @param format selection of format code
     * @return youtube request builder
     */
    public YoutubeRequest format(@NonNull String format) {
        return option("format", format);
    }

    /**
     * Disable filesystem caching
     *
     * @return youtube request builder
     */
    public YoutubeRequest noCacheDir() {
        return option("no-cache-dir");
    }

    /**
     * Delete all filesystem cache files
     *
     * @return youtube request builder
     */
    public YoutubeRequest rmCacheDir() {
        return option("rm-cache-dir");
    }

    /**
     * Abort after downloading NUMBER files
     *
     * @param maximum download entry in a playlist
     * @return youtube request builder
     */
    public YoutubeRequest maxDownload(int maximum) {
        return option("max-download", maximum);
    }

    /**
     * Download only the video, if the URL refers to a video and a playlist.
     *
     * @return youtube request builder
     */
    public YoutubeRequest noPlaylist() {
        return option("no-playlist");
    }

    /**
     * Download the playlist, if the URL refers to a video and a playlist.
     *
     * @return youtube request builder
     */
    public YoutubeRequest yesPlaylist() {
        return option("yes-playlist");
    }

    /**
     * Be quiet and print the video information as JSON (video is still being downloaded).
     *
     * @return youtube request builder
     */
    public YoutubeRequest printJson() {
        return option("print-json");
    }

    /**
     * Simulate, quiet but print JSON information. See the "OUTPUT TEMPLATE" for a description of available keys.
     *
     * @return youtube request builder
     */
    public YoutubeRequest dumpJson() {
        return option("dump-json");
    }
}
