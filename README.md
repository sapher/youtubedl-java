# youtubedl-java [![Build Status](https://travis-ci.org/sapher/youtubedl-java.svg?branch=master)](https://travis-ci.org/sapher/youtubedl-java)

A simple java wrapper for [youtube-dl](https://github.com/rg3/youtube-dl) executable

There's a lot of thing left to do. Parsing output is one of them. Too bad, youtube-dl doesn't output formatted data.

# Prerequisite

:warning: Youtube-dl should be installed and available in your `$PATH.

[How to properly install YoutubeDL executable](https://rg3.github.io/youtube-dl/download.html)

Otherwise you will get this error :

`Cannot run program "youtube-dl" (in directory "/Users/my/beautiful/path"): error=2, No such file or directory`

# Usage

## Installation

You can use jitpack.io to add the library to your project.

[youtube-dl](https://jitpack.io/#sapher/youtubedl-java)

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
    compile 'com.github.sapher:youtubedl-java:1.+'
}
```

## Make request

```java
// Video url to download
String videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

// Destination directory
String directory = System.getProperty("user.home");

// Build request
YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
request.setOption("ignore-errors");		// --ignore-errors
request.setOption("output", "%(id)s");	// --output "%(id)s"
request.setOption("retries", 10);		// --retries 10

// Make request and return response
YoutubeDLResponse response = YoutubeDL.execute(request);

// Response
String stdOut = response.getOut(); // Executable output
```

You may also specify a callback to get notified about the progress of the download:

```
...
YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
          @Override
          public void onProgressUpdate(float progress, long etaInSeconds) {
              System.out.println(String.valueOf(progress) + "%");
          }
      });
```
# Links
* [Youtube-dl documentation](https://github.com/sapher/youtubedl-java)
