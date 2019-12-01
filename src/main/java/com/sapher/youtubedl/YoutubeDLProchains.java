package com.sapher.youtubedl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapher.youtubedl.mapper.VideoInfo;

public class YoutubeDLProchains extends YoutubeDL{
	
	/**
     * Retrieve all information available on a video
     * @param url Video url
     * @return Video info
     * @throws YoutubeDLException
     */
    public static VideoInfo getVideoInfo(String url) throws YoutubeDLException  {
    	
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	
        // Build request
        YoutubeDLRequest request = new YoutubeDLRequest(url);
        request.setOption("dump-json");
        request.setOption("no-playlist");
        YoutubeDLResponse response = YoutubeDL.execute(request);

        // Parse result
        ObjectMapper objectMapper = new ObjectMapper();
        VideoInfo videoInfo;
        
        //Proxychains : remove proxychains header ex: "ProxyChains-3.1 (http://proxychains.sf.net)\n"
    	String output = response.getOut();
        output = output.substring(output.indexOf("{"));
        
        try {
            videoInfo = objectMapper.readValue(output, VideoInfo.class);
        } catch (IOException e) {
            throw new YoutubeDLException("Unable to parse video information: " + e.getMessage());
        }

        return videoInfo;
    }
}
