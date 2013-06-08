package com.suhorukov.klyuchevsky;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler {
    private int port;
    private String defaultPath;

    public RequestHandler(int port, String defaultPath) {
        this.port = port;
        this.defaultPath = defaultPath;
    }

    public void listen() throws Throwable {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new SocketProcessor(clientSocket, defaultPath));
            thread.start();
        }
    }
}



