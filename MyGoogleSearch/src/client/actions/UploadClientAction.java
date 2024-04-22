package client.actions;

import server.Response;

import java.io.*;
import java.net.Socket;

public class UploadClientAction {

    public static void execute(String path) {

        try (Socket socket = new Socket("localhost", 8000)) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            int bytes = 0;
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);

            objectOutputStream.writeUTF("upload");
            objectOutputStream.writeLong(file.length());
            objectOutputStream.writeUTF(file.getName());

            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                objectOutputStream.write(buffer, 0, bytes);
                objectOutputStream.flush();
            }
            fileInputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) objectInputStream.readObject();

            System.out.println("Response status "+ response.getStatus());

            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}