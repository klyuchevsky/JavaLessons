package com.suhorukov.klyuchevsky.fileProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileService {
    private String relativePath;
    private String absolutePath;
    private FileSystem fileSystem;

    public FileService(String defaultPath, String relativePath) {
        this.relativePath = relativePath;
        this.absolutePath = defaultPath + relativePath;
        this.fileSystem = new FileSystem(absolutePath);
//        setAbsolutePath(defaultPath + relativePath);
    }

    public String getContentByPath() throws IOException {
        String result = "";
        if (!fileSystem.checkExist()) {
            throw new FileNotFoundException(absolutePath);
        }
        if (fileSystem.checkDir()) {
            result = getDirectoryContent();
        } else {
            result = fileSystem.getFileContent();
        }
        return result;
    }

    private String getDirectoryContent() {
        String result = "";
        try {
            result = fileSystem.getIndex();
        } catch (IOException e) {
//            result = HTMLGen.generateHTML( fileSystem.getFile(), relativePath);
        }
        return result;
    }

    public String getMimeType() {
        return "";
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
        this.fileSystem = new FileSystem(absolutePath);
    }

}
