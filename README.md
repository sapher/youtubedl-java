# youtubedl-java [![Build Status](https://travis-ci.org/sapher/youtubedl-java.svg?branch=master)](https://travis-ci.org/sapher/youtubedl-java)
Just a simple WIP java wrapper for youtubedl 

## Prerequisite
You need `youtube-dl` to be installed and available. You can follow [those instructions](https://github.com/rg3/youtube-dl/blob/master/README.md#installation) to install `youtube-dl`

## Usage

YoutubeDL wrapper is compose of 3 main class : `YoutubeDL`, `YoutubeDLRequest`, `YoutubeDLResponse`.

### YoutubeDL
Static class that execute a request and return a response.

#### Execute a command

```
YoutubeDL.execute('--version');
```

#### Execute a request

```
YoutubeDLRequest request = new YoutubeDLRequest();
YoutubeDL.execute(request);
```

### YoutubeDLRequest
Represent a command for youtube-dl to execute.
You can **set** and **get** all options handle by youtube-dl : [YoutubeDL Options](https://github.com/rg3/youtube-dl/blob/master/README.md#options).

```
// Example to set / get rate limit
request.setRateLimit(1024);
request.getRateLimit();
```

### YoutubeDLResponse
Represent the result of a request.

This object is composed of:

* **exitCode** Exit code of youtube-dl programm
*  **err** String from stderr
*  **out** String from stdout
*  **directory** Directory where the programme has been launched
* *more useful to come...*

## Custom request

You can use `YoutubeDLRequest` object to build any  command you want.

```
// Download a video
YoutubeDLRequest request = new YoutubeDLRequest();
request.setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
request.setDirectory("/Users/johndoe/Desktop");
YoutubeDLResponse response = YoutubeDL.execute(request);

// will result to youtube-dl https://www.youtube.com/watch?v=dQw4w9WgXcQ
```

Be care, you can build a nonsensical command.

### Links

[YoutubeDL Documentation](https://github.com/rg3/youtube-dl/blob/master/README.md#installation)