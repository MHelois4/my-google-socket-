package client;


import client.actions.DeleteClientAction;
import client.actions.ListFilesClientAction;
import client.actions.SearchClientAction;
import client.actions.UploadClientAction;
import server.ServerFilesPath;

import java.io.IOException;
import java.util.Scanner;

public class ClientSocket {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:\n");
            System.out.println("1. Upload File");
            System.out.println("2. List File");
            System.out.println("3. Search in File");
            System.out.println("4. Delete File");
            System.out.println("5. Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            System.out.println("___________________________________________________");
            switch (choice) {
                case 1:
                    System.out.println("\nUpload File option selected");

                    UploadClientAction.execute(ServerFilesPath.PATH+"/a");

                    break;
                case 2:
                    System.out.println("\nList option selected");

                    ListFilesClientAction.execute();
                    break;
                case 3:
                    System.out.println("\nSearch option selected");

                    SearchClientAction.execute("Serra da Capivara");
                    break;
                case 4:
                    System.out.println("\nDelete option selected");

                    DeleteClientAction.execute("3dc3e52f-be57-4ada-a497-d92c01c9c55d__2019pt.json");
                    break;
                case 5:
                    System.out.println("\nStatistics option selected");
                    StatisticsFacade.execute();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println("___________________________________________________");
        }
        scanner.close();
    }

}