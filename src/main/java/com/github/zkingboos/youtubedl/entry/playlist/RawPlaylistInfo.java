package com.github.zkingboos.youtubedl.entry.playlist;

import com.github.zkingboos.youtubedl.entry.video.VideoInfo;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Iterator;
import java.util.List;

@Getter
@ToString
public class RawPlaylistInfo<T extends VideoInfo> implements Iterable<T> {

    private final List<T> videoInfoList;
    private final String id;
    private final String title;
    private final String uploader;

    public RawPlaylistInfo(@NonNull List<T> videoInfoList) {
        this.videoInfoList = videoInfoList;
        final VideoInfo videoInfo = videoInfoList.get(0);
        this.id = videoInfo.getPlaylistId();
        this.title = videoInfo.getPlaylistTitle();
        this.uploader = videoInfo.getPlaylistUploader();
    }

    @Override
    public Iterator<T> iterator() {
        return videoInfoList.iterator();
    }

}
