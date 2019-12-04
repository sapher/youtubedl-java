package com.sapher.youtubedl.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoInfo {

    public String id;
    public String fulltitle;
    public String title;
    @JsonProperty("upload_date")
    public String uploadDate;
    @JsonProperty("display_id")
    public String displayId;
    public int duration;
    public String description;
    public String thumbnail;
    public String license;
    
    @JsonProperty("view_count")
    public String viewCount;
    @JsonProperty("like_count")
    public String likeCount;
    @JsonProperty("dislike_count")
    public String dislikeCount;
    @JsonProperty("repost_count")
    public String repostCount;
    @JsonProperty("average_rating")
    public String averageRating;
    

    @JsonProperty("uploader_id")
    public String uploaderId;
    public String uploader;
    
    @JsonProperty("channel_url")
    public String channelUrl;
    
    @JsonProperty("channel_id")
    public String channelId;
    
    @JsonProperty("uploader_url")
    public String uploaderUrl;
    
    public String track;
    public String playlist;
    @JsonProperty("playlist_index")
    public String playlistIndex;
    @JsonProperty("episode_number")
    public String episodeNumber;
    @JsonProperty("season_number")
    public String seasonNumber;
    
    @JsonProperty("is_live")
    public String isLive;
    public String series;
    
    @JsonProperty("release_date")
    public String releaseDate;
    @JsonProperty("release_year")
    public String releaseYear;
    
    
    @JsonProperty("scan_date")
    public String scanDate;
    
    public String creator;
    public String artist;
    @JsonProperty("alt_title")
    public String altTitle;
    @JsonProperty("extractor_key")
    public String extractorKey;
    public String chapters;
    public String album;
    

    @JsonProperty("player_url")
    public String playerUrl;
    @JsonProperty("webpage_url")
    public String webpageUrl;
    @JsonProperty("webpage_url_basename")
    public String webpageUrlBasename;

    public String resolution;
    public int width;
    public int height;
    public String format;
    public String ext;

    @JsonProperty("http_headers")
    public HttpHeader httpHeader;
    public ArrayList<String> categories;
    public ArrayList<String> tags;
    public ArrayList<VideoFormat> formats;
    public ArrayList<VideoThumbnail> thumbnails;
    //public ArrayList<VideoSubtitle> subtitles;
    
    //some useful getters
	public String getViewCount() {
		return viewCount;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public String getDislikeCount() {
		return dislikeCount;
	}
	public String getRepostCount() {
		return repostCount;
	}
	public String getAverageRating() {
		return averageRating;
	}
	public String getId() {
		return id;
	}
	public String getFulltitle() {
		return fulltitle;
	}
	public String getTitle() {
		return title;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public int getDuration() {
		return duration;
	}
	public String getDescription() {
		return description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public String getUploaderId() {
		return uploaderId;
	}
	public String getUploader() {
		return uploader;
	}
	@Override
	public String toString() {
		return "VideoInfo [id=" + id + "\n, fulltitle=" + fulltitle + "\n, title=" + title + "\n, uploadDate=" + uploadDate
				+ "\n, displayId=" + displayId + "\n, duration=" + duration + "\n, description=" + description
				+ "\n, thumbnail=" + thumbnail + "\n, license=" + license + "\n, viewCount=" + viewCount + "\n, likeCount="
				+ likeCount + "\n, dislikeCount=" + dislikeCount + "\n, repostCount=" + repostCount + "\n, averageRating="
				+ averageRating + "\n, uploaderId=" + uploaderId + "\n, uploader=" + uploader + "\n, channelUrl=" + channelUrl
				+ "\n, channelId=" + channelId + "\n, uploaderUrl=" + uploaderUrl + "\n, track=" + track + "\n, playlist="
				+ playlist + "\n, playlistIndex=" + playlistIndex + "\n, episodeNumber=" + episodeNumber + "\n, seasonNumber="
				+ seasonNumber + "\n, isLive=" + isLive + "\n, series=" + series + "\n, releaseDate=" + releaseDate
				+ "\n, releaseYear=" + releaseYear + "\n, scanDate=" + scanDate + "\n, creator=" + creator + "\n, artist="
				+ artist + "\n, altTitle=" + altTitle + "\n, extractorKey=" + extractorKey + "\n, chapters=" + chapters
				+ "\n, album=" + album + "\n, playerUrl=" + playerUrl + "\n, webpageUrl=" + webpageUrl
				+ "\n, webpageUrlBasename=" + webpageUrlBasename + "\n, resolution=" + resolution + "\n, width=" + width
				+ "\n, height=" + height + "\n, format=" + format + "\n, ext=" + ext + "\n, httpHeader=" + httpHeader
				+ "\n, categories=" + categories + "\n, tags=" + tags + "\n, formats=" + formats + "\n, thumbnails="
				+ thumbnails + "]";
	}
	
	
	
}
