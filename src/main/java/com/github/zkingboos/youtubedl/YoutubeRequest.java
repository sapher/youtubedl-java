package com.github.zkingboos.youtubedl;

import com.github.zkingboos.youtubedl.callback.DownloadProgressCallback;
import com.github.zkingboos.youtubedl.entry.playlist.PlaylistInfo;
import com.github.zkingboos.youtubedl.exception.YoutubeDLException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * YoutubeDL request
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class YoutubeRequest extends YoutubeOption {

    private String directory;
    private String url;

    public YoutubeRequest() {
        this(null, null);
    }

    public YoutubeRequest(@NonNull String url) {
        this(url, null);
    }

    /**
     * Construct a request with a videoUrl and working directory
     *
     * @param url       youtube video url
     * @param directory destination output video download and dump data information
     */
    public YoutubeRequest(String url, String directory) {
        this.url = url;
        this.directory = directory;
    }

    /**
     * Transform options to a string that the executable will execute
     *
     * @return Command string
     */
    protected String[] buildCommandLine(String... command) {
        final List<String> optionsCommandList = new LinkedList<>();
        if (command.length > 0) {
            optionsCommandList.addAll(Arrays.asList(command));
        }

        for (Entry<String, String> entry : getOptions().entrySet()) {
            optionsCommandList.add("--".concat(entry.getKey().trim()));
            String value = entry.getValue();
            if (value != null) {
                optionsCommandList.add(value.trim());
            }
        }

        // Set url if it's present
        if (url != null) {
            optionsCommandList.add(url.trim());
        }

        return optionsCommandList.toArray(new String[0]);
    }

    public YoutubeResponse build() throws YoutubeDLException {
        return build(null);
    }

    public YoutubeResponse build(DownloadProgressCallback callback) throws YoutubeDLException {
        return YoutubeDL.execute(this, callback);
    }

    public PlaylistInfo get() throws YoutubeDLException {
        return YoutubeDL.getPlaylistInfo(this);
    }
}
