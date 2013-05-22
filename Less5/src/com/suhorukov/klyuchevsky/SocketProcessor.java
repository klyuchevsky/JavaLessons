package com.suhorukov.klyuchevsky;

import java.io.*;
import java.net.Socket;

public class SocketProcessor implements Runnable {

    private Socket s;
    private InputStream is;
    private OutputStream os;
    private String path;
    private File serverPath;
    private Boolean isDirectory;

    public SocketProcessor(Socket s, File serverPath) throws Throwable {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
        this.serverPath = serverPath;
    }

    public void run() {
        try {
            char separator = File.separator.toCharArray()[File.separator.length() - 1]; // separator in operation system
            readInputHeaders();
//            System.out.println(path);
            System.out.println(path.charAt(path.length() - 1));
//            if (path.charAt(path.length() - 1) == / )  {
//                isDirectory = true;
//            } else {
//                isDirectory = false;
//            }

            path = path.replaceAll("/+", " ");
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

            System.out.println(isDirectory);
            if (isDirectory) {
                path = path + separator;
            }
            System.out.println(path);

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
                path = s.substring(4, s.length() - 9); // get resource name from get command of client
//                System.out.println(path);
            }
            System.out.println(s);
            if (s.trim().length() == 0) {
                break;
            }
        }
    }
}

