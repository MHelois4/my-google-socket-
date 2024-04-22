package server.actions;

import server.ServerFilesPath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.UUID;

public class Upload {

    public String execute(Long fileLength, String fileName, ObjectInputStream fileReader) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(ServerFilesPath.PATH + UUID.randomUUID()+"__" + fileName );
        byte[] buffer = new byte[4 * 1024];
        int bytes = 0;

        while (fileLength > 0 && (bytes = fileReader.read(buffer, 0, (int) Math.min(buffer.length, fileLength))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            fileLength -= bytes;
        }
        fileOutputStream.close();

        return "ok";
    }
}
