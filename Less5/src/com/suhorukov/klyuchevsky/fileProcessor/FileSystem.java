package com.suhorukov.klyuchevsky.fileProcessor;

import java.io.*;

public class FileSystem {
    private File absolutePathFile;
    private File[] listFiles = new File[]{};

    public FileSystem(String absolutePath) {
        setAbsolutePathFile(absolutePath);
    }

    public void setAbsolutePathFile(String absolutePath) {
        absolutePathFile = new File(absolutePath);
        if (absolutePathFile.exists())
            listFiles = absolutePathFile.listFiles();
    }

    public String getFileContent() throws IOException {
        return getFileString(absolutePathFile);
    }

    private String getFileString(File file) throws IOException {
        if (!file.canRead()) {
            throw new FileNotFoundException();
        }
        StringBuilder result = new StringBuilder();
        InputStream fInput = null;
        try {
            fInput = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int read;
            while ((read = fInput.read(buf)) != -1) {
                result.append(new String(buf));
            }
        } finally {
            try {
                fInput.close();
            } catch (IOException e) {
            }
        }
        return result.toString();
    }

    public String getIndexHTML() throws IOException {
        return getFileString(new File(absolutePathFile.getPath() + File.separator + "index.html"));
    }


    public boolean checkExist() throws FileNotFoundException {
        return absolutePathFile.exists();
    }

    public boolean checkDir() {
        return absolutePathFile.isDirectory();
    }

    public File getAbsolutePath() {
        return absolutePathFile;
    }
}
