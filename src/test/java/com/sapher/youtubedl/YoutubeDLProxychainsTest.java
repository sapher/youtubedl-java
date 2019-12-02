package com.sapher.youtubedl;

import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoThumbnail;
import com.sapher.youtubedl.YoutubeDLProxychains;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class YoutubeDLProxychainsTest{

    private final static String DIRECTORY = System.getProperty("java.io.tmpdir");
    private final static String VIDEO_URL = "https://www.youtube.com/watch?v=nMfPqeZjc2c";
    private final static String NONE_EXISTENT_VIDEO_URL = "https://www.youtube.com/watch?v=dQw4w9WgXcZ";

    /**@Test
    public void testUsingOwnExecutablePath() throws YoutubeDLException {
        YoutubeDLProxychains.setExecutablePath("/usr/bin/youtube-dl");
        Assert.assertNotNull(YoutubeDLProxychains.getVersion());
    }**/

    @Test
    public void testGetVersion() throws YoutubeDLException {
        Assert.assertNotNull(YoutubeDLProxychains.getVersion());
    }

    @Test
    public void testElapsedTime() throws YoutubeDLException {

        long startTime = System.nanoTime();

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("version");
        YoutubeDLResponse response = YoutubeDLProxychains.execute(request);

        int elapsedTime = (int) (System.nanoTime() - startTime);

        Assert.assertTrue(elapsedTime > response.getElapsedTime());
    }


    @Test
    public void testSimulateDownload() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setUrl(VIDEO_URL);
        request.setOption("simulate");

        YoutubeDLResponse response = YoutubeDLProxychains.execute(request);
        System.out.println(response.getCommand());
        Assert.assertEquals("proxychains youtube-dl " + VIDEO_URL + " --simulate", response.getCommand());
    }

    @Test
    public void testDirectory() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest(VIDEO_URL, DIRECTORY);
        request.setOption("simulate");

        YoutubeDLResponse response = YoutubeDLProxychains.execute(request);

        Assert.assertEquals(DIRECTORY, response.getDirectory());
    }

    @Test
    public void testGetVideoInfo() throws YoutubeDLException {
        VideoInfo videoInfo = YoutubeDLProxychains.getVideoInfo(VIDEO_URL);
        Assert.assertNotNull(videoInfo);
    }
    
    @Test
    public void testGetVideoInfoInDetails() throws YoutubeDLException {
        VideoInfo videoInfo = YoutubeDLProxychains.getVideoInfo(VIDEO_URL);
        Assert.assertNotNull(videoInfo);
        //Let's check we can access key elements from video info
        Assert.assertNotNull(videoInfo.getId());
        Assert.assertNotNull(videoInfo.getTitle());
        Assert.assertNotNull(videoInfo.getFulltitle());
        Assert.assertNotNull(videoInfo.getDescription());
        Assert.assertNotNull(videoInfo.getThumbnail());
        Assert.assertNotNull(videoInfo.getUploaderId());
        Assert.assertNotNull(videoInfo.getUploader());
        Assert.assertNotNull(videoInfo.getUploadDate());
        Assert.assertNotNull(videoInfo.getDuration());
        Assert.assertNotNull(videoInfo.getViewCount());
        Assert.assertNotNull(videoInfo.getLikeCount());
        Assert.assertNotNull(videoInfo.getDislikeCount());
        Assert.assertNotNull(videoInfo.getAverageRating());
        //Let's print the whole object
        System.out.println("videoInfo:"+videoInfo.toString());
        
        
    }
    
    @Test
    public void testGetVideoInfoInDetailsWithProxychains() throws YoutubeDLException {
        VideoInfo videoInfo = YoutubeDLProxychains.getVideoInfo(VIDEO_URL);
        Assert.assertNotNull(videoInfo);
        //Let's check we can access key elements from video info
        Assert.assertNotNull(videoInfo.getId());
        Assert.assertNotNull(videoInfo.getTitle());
        Assert.assertNotNull(videoInfo.getFulltitle());
        Assert.assertNotNull(videoInfo.getDescription());
        Assert.assertNotNull(videoInfo.getThumbnail());
        Assert.assertNotNull(videoInfo.getUploaderId());
        Assert.assertNotNull(videoInfo.getUploader());
        Assert.assertNotNull(videoInfo.getUploadDate());
        Assert.assertNotNull(videoInfo.getDuration());
        Assert.assertNotNull(videoInfo.getViewCount());
        Assert.assertNotNull(videoInfo.getLikeCount());
        Assert.assertNotNull(videoInfo.getDislikeCount());
        Assert.assertNotNull(videoInfo.getAverageRating());
        //Let's print the whole object
        System.out.println("videoInfo:"+videoInfo.toString());
        
        
    }

    @Test
    public void testGetFormats() throws YoutubeDLException {
        List<VideoFormat> formats = YoutubeDLProxychains.getFormats(VIDEO_URL);
        System.out.println("formats : "+formats);
        Assert.assertNotNull(formats);
        Assert.assertTrue(formats.size() > 0);
    }

    @Test
    public void testGetThumbnails() throws YoutubeDLException {
        List<VideoThumbnail> thumbnails = YoutubeDLProxychains.getThumbnails(VIDEO_URL);
        Assert.assertNotNull(thumbnails);
        Assert.assertTrue(thumbnails.size() > 0);
    }

    @Test
    public void testGetTags() throws YoutubeDLException {
        List<String> tags = YoutubeDLProxychains.getTags(VIDEO_URL);
        Assert.assertNotNull(tags);
        Assert.assertTrue(tags.size() > 0);
    }

    @Test
    public void testGetCategories() throws YoutubeDLException {
        List<String> categories = YoutubeDLProxychains.getCategories(VIDEO_URL);
        Assert.assertNotNull(categories);
        Assert.assertTrue(categories.size() > 0);
    }

    @Test(expected = YoutubeDLException.class)
    public void testFailGetNonExistentVideoInfo() throws YoutubeDLException {
        YoutubeDLProxychains.getVideoInfo(NONE_EXISTENT_VIDEO_URL);
    }
}