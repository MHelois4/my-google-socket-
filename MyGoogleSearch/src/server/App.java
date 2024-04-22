package server;

public class App {

    public static void main(String[] args) {
        ServerSocketDispatcher socketDispatcher = new ServerSocketDispatcher(8000);
        socketDispatcher.handleConnection();
    }
}
