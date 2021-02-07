package com.github.zkingboos.youtubedl;

import org.junit.Assert;
import org.junit.Test;

public class YoutubeDLRequestTest {

    @Test
    public void testBuildOptionStandalone() {

        YoutubeRequest request = new YoutubeRequest();
        request.setOption("help");

        Assert.assertEquals("--help", String.join(" ", request.buildCommandLine()));
    }

    @Test
    public void testBuildOptionWithValue() {

        YoutubeRequest request = new YoutubeRequest();
        request.setOption("password", "1234");

        Assert.assertEquals("--password 1234", String.join(" ", request.buildCommandLine()));
    }

    @Test
    public void testBuildChainOptionWithValue() {

        YoutubeRequest request = new YoutubeRequest();
        request.setOption("password", "1234");
        request.setOption("username", "1234");

        Assert.assertEquals("--password 1234 --username 1234", String.join(" ", request.buildCommandLine()));
    }
}
