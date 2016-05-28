package com.sapher.youtubedl;

import org.junit.Assert;
import org.junit.Test;

public class YoutubeDLRequestTest {

    @Test
    public void testBuildOptionStandalone() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("help");

        Assert.assertEquals("--help", request.buildOptions());
    }

    @Test
    public void testBuildOptionWithValue() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("password", "1234");

        Assert.assertEquals("--password 1234", request.buildOptions());
    }

    @Test
    public void testBuildChainOptionWithValue() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setOption("password", "1234");
        request.setOption("username", "1234");

        Assert.assertEquals("--username 1234 --password 1234", request.buildOptions());
    }
}
