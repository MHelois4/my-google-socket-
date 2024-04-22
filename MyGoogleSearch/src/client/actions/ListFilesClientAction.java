package client.actions;



import server.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class ListFilesClientAction {

    public static void execute() {

        try (Socket socket = new Socket("localhost", 8000)) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeUTF("list");
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Response response = (Response) objectInputStream.readObject();

            Set<String> set = (Set<String>) response.getBody();

            set.forEach(s -> System.out.println(s));

            System.out.println("Response status "+ response.getStatus());

            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
