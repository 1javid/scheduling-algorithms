package algorithms;

import java.util.List;

import utils.AssignmentUtil;

public class FCFS {

    public static void run(String inputFile, String outputFile) throws Exception {

        // Read input from file
        List<int[]> processes = AssignmentUtil.readInputandSort(inputFile);

        // Run FCFS
        int i = 0;
        int currentTime = 0;
        int numProcess = processes.size();

        int[] waitingTimes = new int[numProcess];
        int[] turnaroundTimes = new int[numProcess];
        int[] runningTimes = new int[numProcess];

        for (int[] process : processes) {
            int id = process[0];
            int arrivalTime = process[1];
            int runTime = process[2];

            // If current time is less than arrival time, set current time to arrival time
            if (currentTime < arrivalTime) {
                currentTime = arrivalTime;
            }

            // Find start and end times of the current process
            int startTime = currentTime;
            int endTime = startTime + runTime;

            // Find waiting times, turnaround times, and running times of the current
            // process
            waitingTimes[i] = startTime - arrivalTime;
            turnaroundTimes[i] = endTime - arrivalTime;
            runningTimes[i] = runTime;
            currentTime = endTime;

            // Write output to file

            // Write output to file for the proces that runs more than 1 time unit
            // consecutively
            int contRunTime = runTime;
            int contStartTime = startTime;
            while (contRunTime-- > 0) {
                AssignmentUtil.writeOutputTime(outputFile, contStartTime++, id);
            }
            i++;
        }

        // Append metrics to output file
        AssignmentUtil.writeOutputCalculation(outputFile, waitingTimes, turnaroundTimes,
                runningTimes, numProcess, currentTime);
    }
}
