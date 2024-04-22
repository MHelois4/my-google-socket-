package server.actions;

import server.ServerFilesPath;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {

    public Map<String, List<String>> execute(String keyword) throws IOException {

        ListFiles listFiles = new ListFiles();
        Set<String> set = listFiles.execute();

        Map<String, List<String>> map = new HashMap<>();

        for (String s : set) {
            try {
                List<String> list = searchInFile(keyword, (ServerFilesPath.PATH + s));
                map.put(s, list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return map;
    }

    private List<String> searchInFile(String keyword, String pathFile) throws IOException {

        InputStream inputStream = new FileInputStream(pathFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Stream<String> linesStream = bufferedReader.lines();
        return linesStream.filter(a -> a.contains(keyword)).collect(Collectors.toList());
    }
}
