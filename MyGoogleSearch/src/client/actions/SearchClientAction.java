package client.actions;

import server.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class SearchClientAction {

    public static void execute(String keyword) {

        try (Socket socket = new Socket("localhost", 8000)) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeUTF("search");
            objectOutputStream.writeUTF(keyword);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) objectInputStream.readObject();

            Map<String, List<String>> map = (Map<String, List<String>>) response.getBody();

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String fileName = entry.getKey();
                List<String> lines = entry.getValue();

                System.out.println("File name: " + fileName);
                for (String line : lines) {
                    System.out.println(line);
                }
                System.out.println("--------------------");
            }

            System.out.println("Response status "+ response.getStatus());

            objectOutputStream.close();
            objectInputStream.close();

        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
