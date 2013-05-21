package com.suhorukov.klyuchevsky;

import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String[] args) throws Throwable {
        int port = Integer.parseInt(args[0]);
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s)).start();


        }
    }
}

