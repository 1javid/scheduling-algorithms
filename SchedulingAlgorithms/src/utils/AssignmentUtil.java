package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AssignmentUtil {

    public static List<int[]> readInputandSort(String inputFile) throws Exception {
        List<int[]> processes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                int[] process = new int[] {
                        Integer.parseInt(tokens[0].trim()),
                        Integer.parseInt(tokens[1].trim()),
                        Integer.parseInt(tokens[2].trim())
                };
                processes.add(process);
            }
        }

        // Sort processes by arrival time
        processes.sort((p1, p2) -> Integer.compare(p1[1], p2[1]));

        return processes;
    }

    public static void writeOutputTime(String outputFile, int startTime, int id) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
            writer.printf("time %d: running process: %d%n", startTime, id);
        }
    }

    public static void writeOutputCalculation(String outputFile, int[] waitingTimes,
            int[] turnaroundTimes, int[] runningTimes, int numProcess, int currentTime) throws Exception {

        double averageWaitingTime = (double) Arrays.stream(waitingTimes).sum() / (double) numProcess;
        double averageTurnaroundTime = (double) Arrays.stream(turnaroundTimes).sum() / (double) numProcess;
        double averageCpuUtilization = (double) (Arrays.stream(runningTimes).sum() / (double) currentTime) * 100;

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
            writer.printf("\nAverage waiting time: %.2f%n", averageWaitingTime);
            writer.printf("Average turnaround time: %.2f%n", averageTurnaroundTime);
            writer.printf("Average CPU usage: %.2f%%%n", averageCpuUtilization);
        }
    }

    public static int[] listToIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++)
            ret[i] = list.get(i);
        return ret;
    }
}
