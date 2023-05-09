package algorithms;

import java.util.ArrayList;
import java.util.List;

import utils.AssignmentUtil;

public class SRTF {

    public static void run(String inputFile, String outputFile) throws Exception {

        // Read input from file
        List<int[]> originalProcesses = AssignmentUtil.readInputandSort(inputFile);
        List<int[]> processes = AssignmentUtil.readInputandSort(inputFile);

        // Run SRTF
        int currentTime = 0;
        int numProcess = processes.size();

        List<Integer> runningTimes = new ArrayList<>();
        List<Integer> waitingTimes = new ArrayList<>();
        List<Integer> turnaroundTimes = new ArrayList<>();

        // Find total run time and add running times to runningTimes list
        for (int[] process : processes) {
            runningTimes.add(process[2]);
        }

        // Run until all processes are finished
        while (processes.size() != 0) {

            // Find all processes at currentTime and store them in List of readyProcesses
            List<int[]> readyProcesses = new ArrayList<>();
            for (int[] process : processes) {
                if (process[1] <= currentTime) {
                    readyProcesses.add(process);
                }
            }

            // Remove process from processes list if it has finished running
            for (int i = 0; i < readyProcesses.size(); i++) {
                if (readyProcesses.get(i)[2] == 0) {
                    for (int j = 0; j < processes.size(); j++) {
                        if (processes.get(j)[0] == readyProcesses.get(i)[0]) {
                            processes.remove(j);
                            // Find finished process in originalProcesses list
                            int[] finishedProcess = new int[3];
                            for (int k = 0; k < originalProcesses.size(); k++) {
                                if (originalProcesses.get(k)[0] == readyProcesses.get(i)[0]) {
                                    finishedProcess = originalProcesses.get(k);
                                }
                            }

                            readyProcesses.remove(i);

                            // Calculate waiting time and add to waitingTimes list
                            int waitingTime = currentTime - finishedProcess[1]
                                    - finishedProcess[2];
                            waitingTimes.add(waitingTime);

                            // Calculate turnaround time and add to turnaroundTimes list
                            int turnaroundTime = currentTime - finishedProcess[1];
                            turnaroundTimes.add(turnaroundTime);
                        }
                    }
                }
            }

            // Sort processes inside readyProcesses by their running time
            readyProcesses.sort((p1, p2) -> Integer.compare(p1[2], p2[2]));

            // If readyProcesses list is empty, increment currentTime by 1 and skip the rest
            // of
            // the loop
            if (readyProcesses.size() == 0) {
                currentTime++;
                continue;
            }

            int[] currentProcess = readyProcesses.get(0);
            for (int j = 0; j < processes.size(); j++) {
                if (processes.get(j)[0] == currentProcess[0]) {
                    processes.get(j)[2]--;
                    AssignmentUtil.writeOutputTime(outputFile, currentTime, processes.get(j)[0]);
                    currentTime++;
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