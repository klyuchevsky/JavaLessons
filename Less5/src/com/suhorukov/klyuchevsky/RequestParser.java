package com.suhorukov.klyuchevsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class RequestParser {
    String parseGet(String getRequest) {
        return getRequest.substring(4, getRequest.length() - 9);
    }
}