package com.sapher.youtubedl;

import org.junit.Assert;
import org.junit.Test;

public class YoutubeDLRequestTest {

    @Test
    public void testOptionBuild() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setPassword("password");

        Assert.assertEquals("--password password", request.buildOptions());
    }

    @Test
    public void testBuildOptionStandalone() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setHelp(true);

        Assert.assertEquals("--help", request.buildOptions());
    }

    @Test
    public void testBuildOptionWithValue() {

        YoutubeDLRequest request = new YoutubeDLRequest();
        request.setPassword("1234");

        Assert.assertEquals("--password 1234", request.buildOptions());
    }
}
