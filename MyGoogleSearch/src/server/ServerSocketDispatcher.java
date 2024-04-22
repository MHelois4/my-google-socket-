package server;

import java.io.*;
import java.net.Socket;

public class ServerSocketDispatcher {

    private java.net.ServerSocket server;

    public ServerSocketDispatcher(int port) {
        try {
            server = new java.net.ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnection() {
        System.out.println("Waiting for client message...");

        while (true) {
            try {
                Socket socket = server.accept();
                new ConnectionHandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
