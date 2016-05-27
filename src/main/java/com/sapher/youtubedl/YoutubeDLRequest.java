package com.sapher.youtubedl;

import java.lang.reflect.Field;
import java.util.*;

public class YoutubeDLRequest {

    private String directory;
    private String url;

    // Generals Options
    private Boolean help;
    private Boolean version;
    private Boolean update;
    private Boolean ignoreErrors;
    private Boolean abortOnError;
    private Boolean dumpUserAgent;
    private Boolean listExtractors;
    private Boolean extractorDescriptions;
    private Boolean forceGenericExtractor;
    private String defaultSearch;
    private Boolean ignoreConfig;
    private Boolean flatPlaylist;
    private Boolean markWatched;
    private Boolean noMarkWatched;
    private Boolean noColor;

    // Network Options
    private String proxy;
    private String socketTimeout;
    private String sourceAddress;
    private Boolean forceIpv4;
    private Boolean forceIpv6;
    private String cnVerificationProxy;

    // Video Selection Options
    private Integer playlistEnd;
    private String playlistItems;
    private String matchTitle;
    private String rejectTitle;
    private Integer maxDownloads;
    private Integer minFilesize;
    private Integer maxFilesize;
    private String date;
    private String dateBefore;
    private String dateAfter;
    private Integer minViews;
    private Integer maxViews;
    private String matchFilter;
    private Boolean noPlaylist;
    private Boolean yesPlaylist;
    private Integer ageLimit;
    private String downloadArchive;
    private Boolean includeAds;

    // Download Options
    private Integer rateLimit;
    private Integer retries;
    private Integer fragmentRetries;
    private Integer bufferSize;
    private Boolean noResizeBuffer;
    private Boolean playlistReserve;
    private Boolean xattrSetFilesize;
    private Boolean hlsPreferNative;
    private Boolean hlsPreferFfmpeg;
    private Boolean hlsUseMpegts;
    private String externalDownload;
    private String externalDownloaderArgs;

    // Filesystem Options
    private String batchFile;
    private Boolean id;
    private String output;
    private Integer autonumberSize;
    private Boolean retrictFilenames;
    private Boolean autoNumber;
    private Boolean title;
    private Boolean literal;
    private Boolean noOverwrites;
    private Boolean yesContinue;
    private Boolean noContinue;
    private Boolean noPart;
    private Boolean noMtime;
    private Boolean writeDescription;
    private Boolean writeInfoJson;
    private Boolean writeAnnotations;
    private String loadInfo;
    private String cookies;
    private String cacheDir;
    private Boolean noCacheDir;
    private Boolean rmCacheDir;

    // Thumbnail Options
    private Boolean writeThumbnail;
    private Boolean writeAllThumbnails;
    private Boolean listThumbnails;

    // Verbosity / Simulation Options
    private Boolean quiet;
    private Boolean noWarnings;
    private Boolean simulate;
    private Boolean skipDownload;
    private Boolean getUrl;
    private Boolean getTitle;
    private Boolean getId;
    private Boolean getThumbnail;
    private Boolean getDescription;
    private Boolean getDuration;
    private Boolean getFilename;
    private Boolean getFormat;
    private Boolean dumpJson;
    private Boolean dumpSingleJson;
    private Boolean printJson;
    private Boolean newLine;
    private Boolean noProgress;
    private Boolean consoleTitle;
    private Boolean verbose;
    private Boolean dumpPages;
    private Boolean writePages;
    private Boolean printTraffic;
    private Boolean callHome;
    private Boolean noCallHome;

    // Workaround Options
    private String encoding;
    private Boolean noCheckCertificate;
    private Boolean preferInsecure;
    private String userAgent;
    private String referer;
    private String addHeader;
    private Boolean bidiWorkaround;
    private Integer sleepInterval;

    // Video Format Options
    private String format;
    private Boolean allFormat;
    private Boolean preferFreeFormats;
    private Boolean listFormats;
    private Boolean youtubeSkipDashManifest;
    private Boolean mergeOutputFormat;

    // Subtitle Options
    private Boolean writeSub;
    private Boolean writeAutoSub;
    private Boolean allSubs;
    private Boolean listSubs;
    private String subFormat;
    private String subLang;

    // Authentication Options
    private String username;
    private String password;
    private String twoFactor;
    private Boolean netRc;
    private String videoPassword;

    public YoutubeDLRequest() {
    }

    public YoutubeDLRequest(String directory, String url) {
        this.directory = directory;
        this.url = url;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getHelp() {
        return help;
    }

    public void setHelp(Boolean help) {
        this.help = help;
    }

    public Boolean getVersion() {
        return version;
    }

    public void setVersion(Boolean version) {
        this.version = version;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getIgnoreErrors() {
        return ignoreErrors;
    }

    public void setIgnoreErrors(Boolean ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    public Boolean getAbortOnError() {
        return abortOnError;
    }

    public void setAbortOnError(Boolean abortOnError) {
        this.abortOnError = abortOnError;
    }

    public Boolean getDumpUserAgent() {
        return dumpUserAgent;
    }

    public void setDumpUserAgent(Boolean dumpUserAgent) {
        this.dumpUserAgent = dumpUserAgent;
    }

    public Boolean getListExtractors() {
        return listExtractors;
    }

    public void setListExtractors(Boolean listExtractors) {
        this.listExtractors = listExtractors;
    }

    public Boolean getExtractorDescriptions() {
        return extractorDescriptions;
    }

    public void setExtractorDescriptions(Boolean extractorDescriptions) {
        this.extractorDescriptions = extractorDescriptions;
    }

    public Boolean getForceGenericExtractor() {
        return forceGenericExtractor;
    }

    public void setForceGenericExtractor(Boolean forceGenericExtractor) {
        this.forceGenericExtractor = forceGenericExtractor;
    }

    public String getDefaultSearch() {
        return defaultSearch;
    }

    public void setDefaultSearch(String defaultSearch) {
        this.defaultSearch = defaultSearch;
    }

    public Boolean getIgnoreConfig() {
        return ignoreConfig;
    }

    public void setIgnoreConfig(Boolean ignoreConfig) {
        this.ignoreConfig = ignoreConfig;
    }

    public Boolean getFlatPlaylist() {
        return flatPlaylist;
    }

    public void setFlatPlaylist(Boolean flatPlaylist) {
        this.flatPlaylist = flatPlaylist;
    }

    public Boolean getMarkWatched() {
        return markWatched;
    }

    public void setMarkWatched(Boolean markWatched) {
        this.markWatched = markWatched;
    }

    public Boolean getNoMarkWatched() {
        return noMarkWatched;
    }

    public void setNoMarkWatched(Boolean noMarkWatched) {
        this.noMarkWatched = noMarkWatched;
    }

    public Boolean getNoColor() {
        return noColor;
    }

    public void setNoColor(Boolean noColor) {
        this.noColor = noColor;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(String socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Boolean getForceIpv4() {
        return forceIpv4;
    }

    public void setForceIpv4(Boolean forceIpv4) {
        this.forceIpv4 = forceIpv4;
    }

    public Boolean getForceIpv6() {
        return forceIpv6;
    }

    public void setForceIpv6(Boolean forceIpv6) {
        this.forceIpv6 = forceIpv6;
    }

    public String getCnVerificationProxy() {
        return cnVerificationProxy;
    }

    public void setCnVerificationProxy(String cnVerificationProxy) {
        this.cnVerificationProxy = cnVerificationProxy;
    }

    public Integer getPlaylistEnd() {
        return playlistEnd;
    }

    public void setPlaylistEnd(Integer playlistEnd) {
        this.playlistEnd = playlistEnd;
    }

    public String getPlaylistItems() {
        return playlistItems;
    }

    public void setPlaylistItems(String playlistItems) {
        this.playlistItems = playlistItems;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public String getRejectTitle() {
        return rejectTitle;
    }

    public void setRejectTitle(String rejectTitle) {
        this.rejectTitle = rejectTitle;
    }

    public Integer getMaxDownloads() {
        return maxDownloads;
    }

    public void setMaxDownloads(Integer maxDownloads) {
        this.maxDownloads = maxDownloads;
    }

    public Integer getMinFilesize() {
        return minFilesize;
    }

    public void setMinFilesize(Integer minFilesize) {
        this.minFilesize = minFilesize;
    }

    public Integer getMaxFilesize() {
        return maxFilesize;
    }

    public void setMaxFilesize(Integer maxFilesize) {
        this.maxFilesize = maxFilesize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateBefore() {
        return dateBefore;
    }

    public void setDateBefore(String dateBefore) {
        this.dateBefore = dateBefore;
    }

    public String getDateAfter() {
        return dateAfter;
    }

    public void setDateAfter(String dateAfter) {
        this.dateAfter = dateAfter;
    }

    public Integer getMinViews() {
        return minViews;
    }

    public void setMinViews(Integer minViews) {
        this.minViews = minViews;
    }

    public Integer getMaxViews() {
        return maxViews;
    }

    public void setMaxViews(Integer maxViews) {
        this.maxViews = maxViews;
    }

    public String getMatchFilter() {
        return matchFilter;
    }

    public void setMatchFilter(String matchFilter) {
        this.matchFilter = matchFilter;
    }

    public Boolean getNoPlaylist() {
        return noPlaylist;
    }

    public void setNoPlaylist(Boolean noPlaylist) {
        this.noPlaylist = noPlaylist;
    }

    public Boolean getYesPlaylist() {
        return yesPlaylist;
    }

    public void setYesPlaylist(Boolean yesPlaylist) {
        this.yesPlaylist = yesPlaylist;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDownloadArchive() {
        return downloadArchive;
    }

    public void setDownloadArchive(String downloadArchive) {
        this.downloadArchive = downloadArchive;
    }

    public Boolean getIncludeAds() {
        return includeAds;
    }

    public void setIncludeAds(Boolean includeAds) {
        this.includeAds = includeAds;
    }

    public Integer getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Integer getFragmentRetries() {
        return fragmentRetries;
    }

    public void setFragmentRetries(Integer fragmentRetries) {
        this.fragmentRetries = fragmentRetries;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Boolean getNoResizeBuffer() {
        return noResizeBuffer;
    }

    public void setNoResizeBuffer(Boolean noResizeBuffer) {
        this.noResizeBuffer = noResizeBuffer;
    }

    public Boolean getPlaylistReserve() {
        return playlistReserve;
    }

    public void setPlaylistReserve(Boolean playlistReserve) {
        this.playlistReserve = playlistReserve;
    }

    public Boolean getXattrSetFilesize() {
        return xattrSetFilesize;
    }

    public void setXattrSetFilesize(Boolean xattrSetFilesize) {
        this.xattrSetFilesize = xattrSetFilesize;
    }

    public Boolean getHlsPreferNative() {
        return hlsPreferNative;
    }

    public void setHlsPreferNative(Boolean hlsPreferNative) {
        this.hlsPreferNative = hlsPreferNative;
    }

    public Boolean getHlsPreferFfmpeg() {
        return hlsPreferFfmpeg;
    }

    public void setHlsPreferFfmpeg(Boolean hlsPreferFfmpeg) {
        this.hlsPreferFfmpeg = hlsPreferFfmpeg;
    }

    public Boolean getHlsUseMpegts() {
        return hlsUseMpegts;
    }

    public void setHlsUseMpegts(Boolean hlsUseMpegts) {
        this.hlsUseMpegts = hlsUseMpegts;
    }

    public String getExternalDownload() {
        return externalDownload;
    }

    public void setExternalDownload(String externalDownload) {
        this.externalDownload = externalDownload;
    }

    public String getExternalDownloaderArgs() {
        return externalDownloaderArgs;
    }

    public void setExternalDownloaderArgs(String externalDownloaderArgs) {
        this.externalDownloaderArgs = externalDownloaderArgs;
    }

    public String getBatchFile() {
        return batchFile;
    }

    public void setBatchFile(String batchFile) {
        this.batchFile = batchFile;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getAutonumberSize() {
        return autonumberSize;
    }

    public void setAutonumberSize(Integer autonumberSize) {
        this.autonumberSize = autonumberSize;
    }

    public Boolean getRetrictFilenames() {
        return retrictFilenames;
    }

    public void setRetrictFilenames(Boolean retrictFilenames) {
        this.retrictFilenames = retrictFilenames;
    }

    public Boolean getAutoNumber() {
        return autoNumber;
    }

    public void setAutoNumber(Boolean autoNumber) {
        this.autoNumber = autoNumber;
    }

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getLiteral() {
        return literal;
    }

    public void setLiteral(Boolean literal) {
        this.literal = literal;
    }

    public Boolean getNoOverwrites() {
        return noOverwrites;
    }

    public void setNoOverwrites(Boolean noOverwrites) {
        this.noOverwrites = noOverwrites;
    }

    public Boolean getYesContinue() {
        return yesContinue;
    }

    public void setYesContinue(Boolean yesContinue) {
        this.yesContinue = yesContinue;
    }

    public Boolean getNoContinue() {
        return noContinue;
    }

    public void setNoContinue(Boolean noContinue) {
        this.noContinue = noContinue;
    }

    public Boolean getNoPart() {
        return noPart;
    }

    public void setNoPart(Boolean noPart) {
        this.noPart = noPart;
    }

    public Boolean getNoMtime() {
        return noMtime;
    }

    public void setNoMtime(Boolean noMtime) {
        this.noMtime = noMtime;
    }

    public Boolean getWriteDescription() {
        return writeDescription;
    }

    public void setWriteDescription(Boolean writeDescription) {
        this.writeDescription = writeDescription;
    }

    public Boolean getWriteInfoJson() {
        return writeInfoJson;
    }

    public void setWriteInfoJson(Boolean writeInfoJson) {
        this.writeInfoJson = writeInfoJson;
    }

    public Boolean getWriteAnnotations() {
        return writeAnnotations;
    }

    public void setWriteAnnotations(Boolean writeAnnotations) {
        this.writeAnnotations = writeAnnotations;
    }

    public String getLoadInfo() {
        return loadInfo;
    }

    public void setLoadInfo(String loadInfo) {
        this.loadInfo = loadInfo;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    public Boolean getNoCacheDir() {
        return noCacheDir;
    }

    public void setNoCacheDir(Boolean noCacheDir) {
        this.noCacheDir = noCacheDir;
    }

    public Boolean getRmCacheDir() {
        return rmCacheDir;
    }

    public void setRmCacheDir(Boolean rmCacheDir) {
        this.rmCacheDir = rmCacheDir;
    }

    public Boolean getWriteThumbnail() {
        return writeThumbnail;
    }

    public void setWriteThumbnail(Boolean writeThumbnail) {
        this.writeThumbnail = writeThumbnail;
    }

    public Boolean getWriteAllThumbnails() {
        return writeAllThumbnails;
    }

    public void setWriteAllThumbnails(Boolean writeAllThumbnails) {
        this.writeAllThumbnails = writeAllThumbnails;
    }

    public Boolean getListThumbnails() {
        return listThumbnails;
    }

    public void setListThumbnails(Boolean listThumbnails) {
        this.listThumbnails = listThumbnails;
    }

    public Boolean getQuiet() {
        return quiet;
    }

    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }

    public Boolean getNoWarnings() {
        return noWarnings;
    }

    public void setNoWarnings(Boolean noWarnings) {
        this.noWarnings = noWarnings;
    }

    public Boolean getSimulate() {
        return simulate;
    }

    public void setSimulate(Boolean simulate) {
        this.simulate = simulate;
    }

    public Boolean getSkipDownload() {
        return skipDownload;
    }

    public void setSkipDownload(Boolean skipDownload) {
        this.skipDownload = skipDownload;
    }

    public Boolean getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(Boolean getUrl) {
        this.getUrl = getUrl;
    }

    public Boolean getGetTitle() {
        return getTitle;
    }

    public void setGetTitle(Boolean getTitle) {
        this.getTitle = getTitle;
    }

    public Boolean getGetId() {
        return getId;
    }

    public void setGetId(Boolean getId) {
        this.getId = getId;
    }

    public Boolean getGetThumbnail() {
        return getThumbnail;
    }

    public void setGetThumbnail(Boolean getThumbnail) {
        this.getThumbnail = getThumbnail;
    }

    public Boolean getGetDescription() {
        return getDescription;
    }

    public void setGetDescription(Boolean getDescription) {
        this.getDescription = getDescription;
    }

    public Boolean getGetDuration() {
        return getDuration;
    }

    public void setGetDuration(Boolean getDuration) {
        this.getDuration = getDuration;
    }

    public Boolean getGetFilename() {
        return getFilename;
    }

    public void setGetFilename(Boolean getFilename) {
        this.getFilename = getFilename;
    }

    public Boolean getGetFormat() {
        return getFormat;
    }

    public void setGetFormat(Boolean getFormat) {
        this.getFormat = getFormat;
    }

    public Boolean getDumpJson() {
        return dumpJson;
    }

    public void setDumpJson(Boolean dumpJson) {
        this.dumpJson = dumpJson;
    }

    public Boolean getDumpSingleJson() {
        return dumpSingleJson;
    }

    public void setDumpSingleJson(Boolean dumpSingleJson) {
        this.dumpSingleJson = dumpSingleJson;
    }

    public Boolean getPrintJson() {
        return printJson;
    }

    public void setPrintJson(Boolean printJson) {
        this.printJson = printJson;
    }

    public Boolean getNewLine() {
        return newLine;
    }

    public void setNewLine(Boolean newLine) {
        this.newLine = newLine;
    }

    public Boolean getNoProgress() {
        return noProgress;
    }

    public void setNoProgress(Boolean noProgress) {
        this.noProgress = noProgress;
    }

    public Boolean getConsoleTitle() {
        return consoleTitle;
    }

    public void setConsoleTitle(Boolean consoleTitle) {
        this.consoleTitle = consoleTitle;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(Boolean verbose) {
        this.verbose = verbose;
    }

    public Boolean getDumpPages() {
        return dumpPages;
    }

    public void setDumpPages(Boolean dumpPages) {
        this.dumpPages = dumpPages;
    }

    public Boolean getWritePages() {
        return writePages;
    }

    public void setWritePages(Boolean writePages) {
        this.writePages = writePages;
    }

    public Boolean getPrintTraffic() {
        return printTraffic;
    }

    public void setPrintTraffic(Boolean printTraffic) {
        this.printTraffic = printTraffic;
    }

    public Boolean getCallHome() {
        return callHome;
    }

    public void setCallHome(Boolean callHome) {
        this.callHome = callHome;
    }

    public Boolean getNoCallHome() {
        return noCallHome;
    }

    public void setNoCallHome(Boolean noCallHome) {
        this.noCallHome = noCallHome;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Boolean getNoCheckCertificate() {
        return noCheckCertificate;
    }

    public void setNoCheckCertificate(Boolean noCheckCertificate) {
        this.noCheckCertificate = noCheckCertificate;
    }

    public Boolean getPreferInsecure() {
        return preferInsecure;
    }

    public void setPreferInsecure(Boolean preferInsecure) {
        this.preferInsecure = preferInsecure;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getAddHeader() {
        return addHeader;
    }

    public void setAddHeader(String addHeader) {
        this.addHeader = addHeader;
    }

    public Boolean getBidiWorkaround() {
        return bidiWorkaround;
    }

    public void setBidiWorkaround(Boolean bidiWorkaround) {
        this.bidiWorkaround = bidiWorkaround;
    }

    public Integer getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(Integer sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Boolean getAllFormat() {
        return allFormat;
    }

    public void setAllFormat(Boolean allFormat) {
        this.allFormat = allFormat;
    }

    public Boolean getPreferFreeFormats() {
        return preferFreeFormats;
    }

    public void setPreferFreeFormats(Boolean preferFreeFormats) {
        this.preferFreeFormats = preferFreeFormats;
    }

    public Boolean getListFormats() {
        return listFormats;
    }

    public void setListFormats(Boolean listFormats) {
        this.listFormats = listFormats;
    }

    public Boolean getYoutubeSkipDashManifest() {
        return youtubeSkipDashManifest;
    }

    public void setYoutubeSkipDashManifest(Boolean youtubeSkipDashManifest) {
        this.youtubeSkipDashManifest = youtubeSkipDashManifest;
    }

    public Boolean getMergeOutputFormat() {
        return mergeOutputFormat;
    }

    public void setMergeOutputFormat(Boolean mergeOutputFormat) {
        this.mergeOutputFormat = mergeOutputFormat;
    }

    public Boolean getWriteSub() {
        return writeSub;
    }

    public void setWriteSub(Boolean writeSub) {
        this.writeSub = writeSub;
    }

    public Boolean getWriteAutoSub() {
        return writeAutoSub;
    }

    public void setWriteAutoSub(Boolean writeAutoSub) {
        this.writeAutoSub = writeAutoSub;
    }

    public Boolean getAllSubs() {
        return allSubs;
    }

    public void setAllSubs(Boolean allSubs) {
        this.allSubs = allSubs;
    }

    public Boolean getListSubs() {
        return listSubs;
    }

    public void setListSubs(Boolean listSubs) {
        this.listSubs = listSubs;
    }

    public String getSubFormat() {
        return subFormat;
    }

    public void setSubFormat(String subFormat) {
        this.subFormat = subFormat;
    }

    public String getSubLang() {
        return subLang;
    }

    public void setSubLang(String subLang) {
        this.subLang = subLang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTwoFactor() {
        return twoFactor;
    }

    public void setTwoFactor(String twoFactor) {
        this.twoFactor = twoFactor;
    }

    public Boolean getNetRc() {
        return netRc;
    }

    public void setNetRc(Boolean netRc) {
        this.netRc = netRc;
    }

    public String getVideoPassword() {
        return videoPassword;
    }

    public void setVideoPassword(String videoPassword) {
        this.videoPassword = videoPassword;
    }

    protected String buildOptions() {

        Map<String, String> options = new HashMap<String, String>();

        // Handle list of fields
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {

            // Declarations
            String fieldName = field.getName();
            Object fieldValue;

            field.setAccessible(true);

            String name = fieldName.replaceAll("(.)([A-Z])", "$1-$2").toLowerCase();
            String value = "";

            // Quick and dirty
            if (fieldName.equals("directory") || fieldName.equals("url")) continue;

            try {
                Class<?> type = field.getType();
                fieldValue = field.get(this);
                String className = type.getSimpleName();

                if(fieldValue == null) continue;

                // String
                if (className.equals("String")) {
                    value = (String) fieldValue;
                }
                // Int
                else if (className.equals("Integer")) {
                    Integer number = (Integer) fieldValue;
                    value = number.toString();
                }
                // Boolean
                else if (className.equals("Boolean")) {
                    value = "";
                }

            } catch (IllegalAccessException e) {
                // Value stay empty

            }

            options.put(name, value);
        }

        StringBuilder builder = new StringBuilder();

        // Set Url
        if(url != null)
            builder.append(url + " ");

        // Build options strings
        Iterator it = options.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry option = (Map.Entry) it.next();

            String optionFormatted = String.format("--%s %s", option.getKey(), option.getValue()).trim();
            builder.append(optionFormatted);
            it.remove();
        }

        return builder.toString();
    }
}
