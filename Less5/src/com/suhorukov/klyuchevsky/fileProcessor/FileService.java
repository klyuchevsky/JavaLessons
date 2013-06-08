package com.suhorukov.klyuchevsky.fileProcessor;

public class FileService {
    private String relativePath;
    private String absolutePath;
    private FileSystem fileSystem;

    public FileService(String defaultPath, String relativePath) {
        this.relativePath = relativePath;
//        setAbsolutePath(defaultPath + relativePath);
    }


    public String getContentByPath() {
        return "";
    }

    public String getMimeType() {
        return "";
    }


}
