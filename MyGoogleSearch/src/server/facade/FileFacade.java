package server.facade;

import server.Response;
import server.actions.Delete;
import server.actions.ListFiles;
import server.actions.Search;
import server.actions.Upload;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileFacade {

    public Response upload(Long fileLength, String fileName, ObjectInputStream fileReader) throws IOException {

        Upload upload = new Upload();
        String result = upload.execute(fileLength, fileName, fileReader);

        return new Response(result, null);
    }

    public Response search(String keyword) throws IOException {

        Search search = new Search();
        Map<String, List<String>> map = search.execute(keyword);

        return new Response("ok", map);
    }

    public Response list() throws IOException {

        ListFiles listFiles = new ListFiles();
        Set<String> set = listFiles.execute();

        return new Response("ok", set);
    }

    public Response delete(String fileName) throws IOException {

        Delete delete = new Delete();
        String result = delete.execute(fileName);

        return new Response(result, null);
    }
}
