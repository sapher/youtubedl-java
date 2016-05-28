package com.sapher.youtubedl;

import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoThumbnail;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class YoutubeDLTest {

    //private final String directory = System.getProperty("user.home");
    private final String directory = System.getProperty("java.io.tmpdir");
    private final String videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

    @Test
    public void testGetVersion() throws YoutubeDLException {
        Assert.assertNotNull(YoutubeDL.getVersion());
    }

    @Test
    public void testElapsedTime() throws YoutubeDLException {

        long startTime = System.nanoTime();

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("version");
        YoutubeDLResponse response = YoutubeDL.execute(request);

        int elapsedTime = (int) (System.nanoTime() - startTime);

        Assert.assertTrue(elapsedTime > response.getElapsedTime());
    }


    @Test
    public void testSimulateDownload() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setUrl(videoUrl);
        request.setOption("simulate");

        YoutubeDLResponse response = YoutubeDL.execute(request);

        Assert.assertEquals("youtube-dl " + videoUrl + " --simulate", response.getCommand());
    }

    @Test
    public void testDirectory() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("simulate");

        YoutubeDLResponse response = YoutubeDL.execute(request);

        Assert.assertEquals(directory, response.getDirectory());
    }

    @Test
    public void testGetVideoInfo() throws YoutubeDLException {
        VideoInfo videoInfo = YoutubeDL.getVideoInfo(videoUrl);
        Assert.assertNotNull(videoInfo);
    }

    @Test
    public void testGetFormats() throws YoutubeDLException {
        List<VideoFormat> formats = YoutubeDL.getFormats(videoUrl);
        Assert.assertNotNull(formats);
        Assert.assertTrue(formats.size() > 0);
    }

    @Test
    public void testGetThumbnails() throws YoutubeDLException {
        List<VideoThumbnail> thumbnails = YoutubeDL.getThumbnails(videoUrl);
        Assert.assertNotNull(thumbnails);
        Assert.assertTrue(thumbnails.size() > 0);
    }

    @Test
    public void testGetTags() throws YoutubeDLException {
        List<String> tags = YoutubeDL.getTags(videoUrl);
        Assert.assertNotNull(tags);
        Assert.assertTrue(tags.size() > 0);
    }

    @Test
    public void testGetCategories() throws YoutubeDLException {
        List<String> categories = YoutubeDL.getCategories(videoUrl);
        Assert.assertNotNull(categories);
        Assert.assertTrue(categories.size() > 0);
    }
}