package server.actions;

import server.ServerFilesPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Delete {

    public String execute(String fileName) throws IOException {

        Path fileToDeletePath = Paths.get(ServerFilesPath.PATH + fileName);
        Files.delete(fileToDeletePath);

        return "ok";
    }
}
