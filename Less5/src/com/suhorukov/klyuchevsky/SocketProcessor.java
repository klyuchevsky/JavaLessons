package com.suhorukov.klyuchevsky;

import java.io.*;
import java.net.Socket;

public class SocketProcessor implements Runnable {

    private Socket s;
    private InputStream is;
    private OutputStream os;
    private String path;
    private File serverPath;
    private String getRequest;

    public SocketProcessor(Socket s, File serverPath) throws Throwable {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
        this.serverPath = serverPath;
    }

    public void run() {
        try {
            char separator = File.separator.toCharArray()[File.separator.length() - 1]; // separator in operation system
            File output;
            readInputHeaders();
            RequestParser requestParser = new RequestParser();
            path = requestParser.parseGet(getRequest);

            path = getRequest.replaceAll("/+", " ");
            path = path.trim();
            String[] dirs = path.split(" ");
            path = serverPath.getPath();
            path = path + separator;


            for (String dir : dirs) {
                if (separator != (path.charAt(path.length() - 1))) {
                    path = path + separator;
                }
                path = path + dir;
            }

            System.out.println(path);

//            if (getRequest.charAt(getRequest.length() - 1) == '/') {
//                output = new File(path);
//                if (output.exists()) {
//                    writeResponse("exist");
//                } else {
//
//                }
//            }
            writeResponse("<!DOCTYPE html><head><meta charset=\"utf-8\"></head><html><body><h1>Тестируем сервер</h1></body></html>");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.err.println("Client processing finished");
    }

    private void writeResponse(String s) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: HTTPServer/2013-05-14\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;
        os.write(result.getBytes());
        os.flush();
    }

    private void readInputHeaders() throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while (true) {
            String s = br.readLine();
            if (s.contains("GET")) {
                getRequest = s;
            }
            System.out.println(s);
            if (s.trim().length() == 0) {
                break;
            }
        }
    }

    public void setPath(String path) {
        getRequest = path;
    }
}

