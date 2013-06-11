package com.suhorukov.klyuchevsky.fileProcessor;

import com.suhorukov.klyuchevsky.HTMLFileGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileService {
    private String relativePath;
    private String absolutePath;
    private FileSystem fileSystem;

    public FileService(String absolutePath, String relativePath) {
        this.relativePath = relativePath;
        this.absolutePath = absolutePath + relativePath;
//        System.out.println(this.absolutePath);
        this.fileSystem = new FileSystem(this.absolutePath);
        System.out.println(this.fileSystem.getAbsolutePath());
    }

    public String getContentByPath() throws IOException {
        String result = "";
        System.out.println(!fileSystem.checkExist());
        System.out.println(fileSystem.checkDir());

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

    private String getDirectoryContent() throws IOException {
        String result = "";
        try {
            result = fileSystem.getIndexHTML();

        } catch (IOException e) {
            HTMLFileGenerator htmlFileGenerator = new HTMLFileGenerator();
            result = htmlFileGenerator.generateHTML(fileSystem.getAbsolutePath());
        }
//        System.out.println(result);
        return result;
    }

    public String getMimeType() {
        return "";
    }

}
