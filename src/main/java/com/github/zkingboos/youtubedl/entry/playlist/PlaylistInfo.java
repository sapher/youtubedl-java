package com.github.zkingboos.youtubedl.entry.playlist;

import com.github.zkingboos.youtubedl.entry.video.VideoInfo;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@ToString
public class PlaylistInfo extends RawPlaylistInfo<VideoInfo> {

    public PlaylistInfo(@NonNull List<VideoInfo> videoInfoList) {
        super(videoInfoList);
    }
}
