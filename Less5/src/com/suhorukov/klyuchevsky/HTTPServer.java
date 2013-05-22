package com.suhorukov.klyuchevsky;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String[] args) throws Throwable {
        int port = Integer.parseInt(args[0]);
        ServerSocket ss = new ServerSocket(port);
        File folder = new File(args[1]);
        if (folder.isDirectory()) {
            while (true) {
                Socket s = ss.accept();
                System.err.println("Client accepted");
                new Thread(new SocketProcessor(s, folder)).start();

            }
        } else {
            System.out.println("Неверно указана директория сервера.");
            System.out.println("Проверьте параметры запуска сервера.");
            System.out.println("Убедитесь, что директория сервера не указывает на файл");
        }
    }
}

