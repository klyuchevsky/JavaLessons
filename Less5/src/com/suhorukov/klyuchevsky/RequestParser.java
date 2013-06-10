package com.suhorukov.klyuchevsky;

import java.io.*;
import java.net.URLDecoder;

class RequestParser {
    String parseGet(String getRequest) {
        return getRequest.substring(4, getRequest.length() - 9);
    }

    public static String getRequestMethod(String header) {
        String method = "";
        if (header != null && header.trim().length() > 0) {
            String[] cmd = header.split(" ");
            if (cmd[0].length() > 1) {
                method = cmd[0];
            }
        }
        return method;
    }

    public static String getRelativePath(String header) throws UnsupportedEncodingException {
        String relativePath = "";
        if (header != null && header.trim().length() > 0) {
            String[] cmd = header.split(" ");
            if (cmd[1].length() > 1) {
                cmd[1] = URLDecoder.decode(cmd[1], "UTF-8");
                cmd[1] = cmd[1].replaceAll("/$", "");
                relativePath = cmd[1].replace("/", File.separator);
            }
        }
        return relativePath;
    }

}
