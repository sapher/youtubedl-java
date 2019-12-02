package com.sapher.youtubedl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import com.sapher.youtubedl.mapper.VideoThumbnail;

/**
 * @author memento
 * This class is child class from YoutubeDL that allows to use youtube-dl behind a Tor "Proxychains" software to avoid BAN for example.
 * 
 * Test class: YoutubeDLProxychainsTest
 * 
 * Before using this class, be sure you have installed proxychains on your machine
 * If you're using debian/ubuntu, install this powerful tool by typing this in your terminal :
 * > sudo apt-get install proxychains tor obfsproxy
 * 
 * (Other linux distributions : https://www.linuxsecrets.com/3372-install-setup-proxychains-on-linux )
 * 
 * Normally, to use youtube-dl behind proxychains, you'd execute (example) : 
 * > proxychains youtube-dl https://www.youtube.com/watch?v=nMfPqeZjc2c
 * 
 * pros : you stay anonymous and you avoid being ban
 * cons : the queries take longer 
 *
 */
public class YoutubeDLProxychains extends YoutubeDL{
	
	/**
     * Execute youtube-dl request
     * @param request request object
     * @return response object
     * @throws YoutubeDLException
     */
    public static YoutubeDLResponse execute(YoutubeDLRequest request) throws YoutubeDLException {
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	return execute(request, null);
    }
	
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
        YoutubeDLResponse response = execute(request);

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
    
    /**
     * List formats
     * @param url Video url
     * @return list of formats
     * @throws YoutubeDLException
     */
    public static List<VideoFormat> getFormats(String url) throws YoutubeDLException {
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	
    	VideoInfo info = getVideoInfo(url);
        return info.formats;
    }

    /**
     * List thumbnails
     * @param url Video url
     * @return list of thumbnail
     * @throws YoutubeDLException
     */
    public static List<VideoThumbnail> getThumbnails(String url) throws YoutubeDLException {
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	
    	VideoInfo info = getVideoInfo(url);
        return info.thumbnails;
    }

    /**
     * List categories
     * @param url Video url
     * @return list of category
     * @throws YoutubeDLException
     */
    public static List<String> getCategories(String url) throws YoutubeDLException {
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	
    	VideoInfo info = getVideoInfo(url);
        return info.categories;
    }

    /**
     * List tags
     * @param url Video url
     * @return list of tag
     * @throws YoutubeDLException
     */
    public static List<String> getTags(String url) throws YoutubeDLException {
    	//We use proxychains, here
    	setExecutablePath("proxychains youtube-dl");
    	
    	VideoInfo info = getVideoInfo(url);
        return info.tags;
    }
}
