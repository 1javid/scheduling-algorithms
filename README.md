# Scheduling Algorithms

CPU Scheduling Simulator

# Project Description

This project is a CPU scheduling simulator that reads process information from an input file named `processes.txt` and simulates three different CPU scheduling algorithms - `First-Come-First-Served (FCFS), Shortest Remaining Time First (SRTF), and Round Robin (RR).` The simulator outputs which process is running during each time slot and calculates performance metrics such as `average waiting time, average turnaround time, and overall CPU usage` for each algorithm. The output is written to separate files for each algorithm - `TeamNaN_FCRS.txt, TeamNaN_SRTF.txt, and TeamNaN_RR.txt.`

# Input File Format

The input file named processes.txt should have the following format:
```
process_id, arrival_time, running_time
1, 0, 2
2, 1, 1
```
Each line of the input file represents a process, and the columns represent the process ID, arrival time, and running time, respectively.

# Output File Format

The output files for each algorithm should have the following format:
```
TeamNaN_FCRS.txt:

time 0: running process: 1
time 1: running process: 1
time 2: running process: 2

Average waiting time: 0.50
Average turnaround time: 2.00
Average CPU usage: 100.00%
```
```
TeamNaN_SRTF.txt:

time 0: running process: 1
time 1: running process: 1
time 2: running process: 2

Average waiting time: 0.50
Average turnaround time: 2.00
Average CPU usage: 100.00%
```
```
TeamNaN_RR.txt:

time 0: running process: 1
time 1: running process: 1
time 2: running process: 2

Average waiting time: 0.50
Average turnaround time: 2.00
Average CPU usage: 100.00%
```
The output files show which process is running during each time slot, and the performance metrics calculated by the simulator - average waiting time, average turnaround time, and overall CPU usage.