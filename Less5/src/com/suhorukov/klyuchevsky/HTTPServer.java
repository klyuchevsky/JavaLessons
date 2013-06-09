package com.suhorukov.klyuchevsky;

public class HTTPServer {
    public static void main(String[] args) throws Throwable {
        int port = 8080;
        String defaultPath = "\\";
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
            defaultPath = args[1];

        } else System.out.println("Укажите параметры запуска: порт, директорию сервера");
        RequestHandler requestHandler = new RequestHandler(port, defaultPath);
        requestHandler.listen();
    }
}


