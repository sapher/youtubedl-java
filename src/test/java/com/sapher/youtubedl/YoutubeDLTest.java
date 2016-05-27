package com.sapher.youtubedl;

import org.junit.Test;
import org.junit.Assert;

public class YoutubeDLTest {

    private final String directory = System.getProperty("user.home");
    private final String videoUrl = "https://www.youtube.com/watch?v=9ka5bgHnHyg";

    @Test
    public void testGetVersion() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setHelp(true);

        Assert.assertNotNull(YoutubeDL.execute(request));
    }

    @Test
    public void testSimulateDownload() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setUrl(videoUrl);
        request.setSimulate(true);

        YoutubeDLResponse response = YoutubeDL.execute(request);

        Assert.assertEquals("youtube-dl https://www.youtube.com/watch?v=9ka5bgHnHyg --simulate", response.getCommand());
    }

    @Test
    public void testDirectory() throws YoutubeDLException {

        YoutubeDLRequest request = new YoutubeDLRequest(directory, videoUrl);
        request.setSimulate(true);

        YoutubeDLResponse response = YoutubeDL.execute(request);

        Assert.assertEquals(directory, response.getDirectory());
    }
}