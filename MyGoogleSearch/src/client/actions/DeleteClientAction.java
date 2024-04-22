package client.actions;

import server.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DeleteClientAction {


    public static void execute(String fileName) {

        try (Socket socket = new Socket("localhost", 8000)) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeUTF("delete");
            objectOutputStream.writeUTF(fileName);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Response response = (Response) objectInputStream.readObject();

            System.out.println("Response status " + response.getStatus());

            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
