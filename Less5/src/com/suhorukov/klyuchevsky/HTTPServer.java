package com.suhorukov.klyuchevsky;

public class HTTPServer {
    public static void main(String[] args) throws Throwable {
        int port = 8080;
        String absolutePath = "\\";
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
            absolutePath = args[1];

        } else System.out.println("Укажите параметры запуска: порт, директорию сервера");
        RequestHandler requestHandler = new RequestHandler(port, absolutePath);
        requestHandler.listen();
    }
}


