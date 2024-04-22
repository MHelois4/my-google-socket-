package server.actions;

import server.ServerFilesPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFiles {

    public Set<String> execute() throws IOException {

        try (Stream<Path> stream = Files.list(Paths.get(ServerFilesPath.PATH))) {

            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }
}
