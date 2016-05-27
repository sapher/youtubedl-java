package com.sapher.youtubedl;

import java.lang.reflect.Field;

public class YoutubeDLRequest {

    private String directory;
    private String url;

    public YoutubeDLRequest() {
    }

    public YoutubeDLRequest(String directory, String url) {
        this.directory = directory;
        this.url = url;
    }

    public String buildCommandString() {

        StringBuilder options = new StringBuilder();
        options.append(url); // youtube-dl doesn't care if you put the url even if you make --help, --version,...

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName();

            String name = "--" + fieldName.replaceAll("(.)([A-Z])", "$1-$2");
            String value = "";

            // Quick and dirty
            if(fieldName.equals("directory") || fieldName.equals("url")) continue;

            try {
                Object fieldValue = field.get(this);
                Class<?> type = field.getType();
                String className = type.getSimpleName();

                // String
                if(className.equals("String")) {
                    value = (String) fieldValue;
                }
                // Int
                else if(className.equals("int")) {
                    Integer number = (Integer) fieldValue;
                    value = number.toString();
                }
                // Boolean > not touch

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // Do nothing it's good
            }

            options.append(String.format(" %s %s", name, value));
        }

        return options.toString().trim();
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
}
