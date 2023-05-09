package algorithms;

import java.util.ArrayList;
import java.util.List;

import utils.AssignmentUtil;

public class RR {

    public static void run(String inputFile, String outputFile) throws Exception {

        // Read input from file
        List<int[]> originalProcesses = AssignmentUtil.readInputandSort(inputFile);
        List<int[]> processes = AssignmentUtil.readInputandSort(inputFile);

        // Run RR (quantum = 1)
        int currentTime = 0;
        int numProcess = processes.size();

        List<Integer> runningTimes = new ArrayList<>();
        List<Integer> turnaroundTimes = new ArrayList<>();
        List<Integer> waitingTimes = new ArrayList<>();

        // Find total run time and add running times to runningTimes list
        for (int[] process : processes) {
            runningTimes.add(process[2]);
        }

        while (processes.size() != 0) {
            // Find all processes at currentTime and store them in List of readyProcesses
            List<int[]> readyProcesses = new ArrayList<>();
            for (int[] process : processes) {
                if (process[1] <= currentTime) {
                    readyProcesses.add(process);
                }
            }

            // If ready processes is empty, increment currentTime by 1 and skip the rest of
            // the loop
            if (readyProcesses.size() == 0) {
                currentTime++;
                continue;
            }

            // Iterate through each process and reduce their running time by 1
            int temp = currentTime;
            for (int[] process : readyProcesses) {
                if (process[1] <= currentTime && process[2] > 0) {
                    process[2]--;
                    AssignmentUtil.writeOutputTime(outputFile, temp++, process[0]);
                }
            }
            temp = 0;

            for (int i = 0; i < readyProcesses.size(); i++) {
                // Increment currentTime by 1
                currentTime++;

                // Remove processes that have finished running
                if (readyProcesses.get(i)[2] == 0) {
                    // Find finished process in originalProcesses list
                    int[] finishedProcess = new int[3];
                    for (int j = 0; j < originalProcesses.size(); j++) {
                        if (originalProcesses.get(j)[0] == readyProcesses.get(i)[0]) {
                            finishedProcess = originalProcesses.get(j);
                        }
                    }

                    // Add waiting time of finished processes to waitingTimes list
                    waitingTimes.add(currentTime - (finishedProcess[1] + finishedProcess[2]));
                    // Find turnaround time of finished processes and add it to turnaroundTimes list
                    turnaroundTimes.add(currentTime - finishedProcess[1]);

                    // Remove finished processes from processes list
                    for (int j = 0; j < processes.size(); j++) {
                        // If process id matches finished process id
                        if (processes.get(j)[0] == finishedProcess[0]) {
                            // Remove process from processes list
                            processes.remove(j);
                        }
                    }
                }
            }
        }

        // Convert times to int[]
        int[] waitingTimesArray = AssignmentUtil.listToIntArray(waitingTimes);
        int[] turnaroundTimesArray = AssignmentUtil.listToIntArray(turnaroundTimes);
        int[] runningTimesArray = AssignmentUtil.listToIntArray(runningTimes);

        // Append metrics to output file
        AssignmentUtil.writeOutputCalculation(outputFile, waitingTimesArray,
                turnaroundTimesArray,
                runningTimesArray,
                numProcess, currentTime);
    }
}