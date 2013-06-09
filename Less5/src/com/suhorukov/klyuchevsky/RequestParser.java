package com.suhorukov.klyuchevsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public static String getRelativePath(String header) {
        String r = "";
        return r;
    }

}
