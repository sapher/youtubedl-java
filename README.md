# youtubedl-java [![](https://jitpack.io/v/zkingboos/youtubedl-java.svg)](https://jitpack.io/#zkingboos/youtubedl-java)

A rewrite java wrapper for [youtube-dl](https://github.com/rg3/youtube-dl) executable with object orietation

## Pre requisites
You should have `youtube-dl` installed and available in your $PATH environment variable. You can also add your path dynamically.

[How to properly install YoutubeDL executable](https://rg3.github.io/youtube-dl/download.html)

Otherwise you'll get this error :

`Cannot run program "youtube-dl" (in directory "computer's path"): error=2, No such file or directory`

### Installation

You can use [youtube-dl](https://jitpack.io/#zkingboos/youtubedl-java) jitpack.io repository to add the library to your project.

### Gradle

*Step 1 :* Add jitpack repository to your build file

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

*Step 2:* Add the dependency

```
dependencies {
    implementation 'com.github.zkingboos:youtubedl-java:VERSION'
}
```

## Example

```java
// Change environment variables
YoutubeDL.setEnvironmentConsumer(environment -> {
  //TODO: manage your environment variables here
});

// Change default youtube-dl command
YoutubeDL.setExecutablePath("cmd.exe", "/c", "youtube-dl"); //example with windows  

// Video url to download
String videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"; //to download youtube video url

//or
String videoUrl = "ytsearch1:fantastic music song"; //to search by a video and download it
  
// Destination directory
String directory = System.getProperty("user.home");

// Build request with video url
YoutubeRequest request = YoutubeDL
  .from(videoUrl, directory)
  .ignoreErrors() // --ignore-errors
  .output("%(id)s") // --output "%(id)s"
  .retries(10); // --retries 10

//or build request with search video
YoutubeRequest request = YoutubeDL
  .search(videoUrl, directory)
  .ignoreErrors() // --ignore-errors
  .output("%(id)s") // --output "%(id)s"
  .retries(10); // --retries 10

// Make request and return response
final YoutubeResponse response = request.build();

// Response
final String out = response.getOut(); // Executable output
```
> You can also see [youtube-dl options][youtube-dl-options] for more options.

You may also specify a callback to get notified about the progress of the download:

```
YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
          @Override
          public void onProgressUpdate(float progress, long etaInSeconds) {
              System.out.println(String.valueOf(progress) + "%");
          }
      });
```
> DownloadProgressCallback will be longer no supported by this project.

[youtube-dl-options]: https://github.com/ytdl-org/youtube-dl#OPTIONS