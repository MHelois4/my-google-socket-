package client;

import client.actions.SearchClientAction;
import client.actions.UploadClientAction;
import server.ServerFilesPath;
import statistcs.Statistics;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatisticsFacade {

    private static final Scanner scanner = new Scanner(System.in);

    public static void execute() {
        int numberRequests = chooseNumberRequest();
        if (numberRequests == -1) return;

        runTests(numberRequests);
    }

    static int chooseNumberRequest() {
        System.out.println("Choose number of requests:");
        System.out.println("1. 50 requests");
        System.out.println("2. 100 requests");
        System.out.println("3. 200 requests");
        System.out.println("4. 400 requests");
        System.out.println("5. Return");

        int choice = scanner.nextInt();
        System.out.println("___________________________________________________");

        switch (choice) {
            case 1:
                System.out.println("\n50 requests option selected");
                return 50;
            case 2:
                System.out.println("\n100 requests option selected");
                return 100;
            case 3:
                System.out.println("\n200 requests option selected");
                return 200;
            case 4:
                System.out.println("\n400 requests option selected");
                return 400;
            case 5:
                return -1;
            default:
                System.out.println("Invalid choice.");
                return -1;
        }
    }

    static void runTests(int numberRequests) {
        System.out.println("___________________________________________________");
        System.out.println("Choose an option:");
        System.out.println("1. Upload a file");
        System.out.println("2. Search a file");
        System.out.println("3. Return to main");

        int choice = scanner.nextInt();
        System.out.println("___________________________________________________");

        if (choice != 1 && choice != 2) {
            System.out.println("Invalid choice.");
            return;
        }

        Statistics statistics = new Statistics();
        try(ExecutorService executorService = Executors.newFixedThreadPool(numberRequests)) {

            for (int i = 0; i < numberRequests; i++) {
                final int finalChoice = choice;
                executorService.execute(() -> runAction(finalChoice, statistics));
            }
            executorService.shutdown();

            boolean b = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("___________________________________________________");
        System.out.println("Test Results:");
        System.out.println("Mean Response Time: " + statistics.calculateMean() + " ms");
        System.out.println("Median: " + statistics.calculateMedian() + " ms");
        System.out.println("Standard Deviation: " + statistics.calculateStandardDeviation() + " ms");
        System.out.println();
    }

    static void runAction(int choice, Statistics statistics) {

        long startTime = System.nanoTime();
        if (choice == 1) {
            UploadClientAction.execute(ServerFilesPath.PATH+"/a");
        } else {
            SearchClientAction.execute("Serra da Capivara");
        }
        long endTime = System.nanoTime();
        long executionTimeMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        statistics.addResponseTime(executionTimeMillis);
    }
}
