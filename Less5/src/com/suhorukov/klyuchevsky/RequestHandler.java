package com.suhorukov.klyuchevsky;

import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler {
    private int port;
    private String absolutePath;

    public RequestHandler(int port, String absolutePath) {
        this.port = port;
        this.absolutePath = absolutePath;
    }

    public void listen() throws Throwable {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new SocketProcessor(clientSocket, absolutePath));
            thread.start();
        }
    }
}



