package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.fileProcessor.FileService;

import java.io.*;
import java.net.Socket;

public class SocketProcessor implements Runnable {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String defaultPath;

    private final static String CODE_200 = "200 OK";
    private final static String CODE_404 = "404 Not Found";
    private final static String BODY_404 = "<h1>Error 404 File not found</h1>";
    private final static String CODE_501 = "501 Not Implemented";
    private final static String BODY_501 = "<h1>Error 501 Not implemented</h1>";
    private final static String CODE_500 = "500 Internal Server Error";
    private final static String BODY_500 = "<h1>Error 500 Internal Server Error</h1>";

    public SocketProcessor(Socket socket, String serverPath) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.defaultPath = defaultPath;
    }

    public void run() {
        System.out.println("Starting processor for client " + socket.getInetAddress() + ":" + socket.getPort());
        try {
            String header = readInputHeader();
            System.out.println(header);
            String method = RequestParser.getRequestMethod(header);
            System.out.println(!checkRequestMethod(method));
            System.out.println(method);
            if (!checkRequestMethod(method)) {
                throw new NotImplementedCommand("Не поддерживаемая команда " + method);
            }
            String relativePath = RequestParser.getRelativePath(header);
            System.out.println(relativePath);
            FileService fileService = new FileService(defaultPath, relativePath);
            String result = fileService.getContentByPath();
            writeResponse(CODE_200, result, fileService.getMimeType());

        } catch (FileNotFoundException e) {
            try {
                writeResponse(CODE_404, BODY_404, "text/html");
            } catch (Throwable e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (NotImplementedCommand e) {
            try {
                writeResponse(CODE_501, BODY_501, "text/html");
            } catch (Throwable e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            try {
                writeResponse(CODE_500, BODY_500 + "<p>" + e.getMessage() + "</p>", "text/html");
            } catch (Throwable e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        System.out.println("Client processing finished for client " + socket.getInetAddress() + ":" + socket.getPort());
    }

    private void writeResponse(String code, String result, String type) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: HTTPServer/2013-05-14\r\n" +
                "Content-Type: text/html\r\n" +
//                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
//        String result = response + s;
        outputStream.write(result.getBytes());
        outputStream.flush();
    }

    private String readInputHeader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.readLine();
    }

    private boolean checkRequestMethod(String method) {
        if ("GET".equals(method) || "HEAD".equals(method))
            return true;
        else
            return false;
    }
}

