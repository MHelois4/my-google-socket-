package server;

import server.facade.FileFacade;

import java.io.*;
import java.net.Socket;

class ConnectionHandler implements Runnable {

    private final Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try {

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String route = ois.readUTF();
            Response response;
            FileFacade fileFacade = new FileFacade();
            switch (route){
                case "search":
                    System.out.println("Search route");

                    String keyword = ois.readUTF();
                    response = fileFacade.search(keyword);

                    break;
                case "upload":
                    System.out.println("Upload route");

                    Long fileLength = ois.readLong();
                    String fileName = ois.readUTF();

                    response = fileFacade.upload(fileLength, fileName, ois);

                    break;
                case "list":
                    System.out.println("List route");

                    response = fileFacade.list();

                    break;
                case "delete":
                    System.out.println("Delete route");

                    String uuid = ois.readUTF();
                    response = fileFacade.delete(uuid);

                    break;
                default:
                    System.out.println("Route not found");
                    response = new Response("Error - Route not found",null);
            }

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);

            ois.close();
            oos.close();

            socket.close();

            System.out.println("Waiting for client message...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}