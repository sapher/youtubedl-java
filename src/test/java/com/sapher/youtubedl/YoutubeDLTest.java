package com.sapher.youtubedl;

import org.junit.Test;
import org.junit.Assert;

public class YoutubeDLTest {

    private YoutubeDL youtubeDL = new YoutubeDL();

    @Test
    public void testTest() throws YoutubeDLException {
        Assert.assertNotNull(youtubeDL.execute("--version"));
    }
}