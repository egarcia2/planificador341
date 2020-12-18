# CS 341 Final Project: Planificador 
OS scheduler simulator (planificador is the spanish translation for 'scheduler'). **We have used Java to implement the following:**

Our project implements the following things: 
* Five common scheduling algorithms as described in OSTEP Chapters (CPU: Scheduling, Multi-level Feedback, and Lottery Scheduling)
* Scheduling algorithms include: First In First Out (FIFO), Shortest Job First (SJF), Round Robin (RR), Lottery (Lottery_Priority), and Multi-level Feedback Queue (MLFQ)
* Four scheduling metrics for all algorithms to calculate the average runtime, average turnaround time, total completion time and total completion time with incorporation of context-switch time 
* Clock class to simulate time being run on the CPU 
* Job class to simulate jobs in a workload
* Planificador main class where all algorithms are run and metrics are used to compare and contrast algorithms.
* Planificador2 main class where all algorithms (except for Lottery_Priority) are run and metrics are used to compare and contrast algorithms.  

## How to Compile & Run 
To compile all files on VSC, run: __javac *.java__ <br />
Or to compile a file individually, run: **javac (filename).java** <br />

To run all algorithms and view their metrics, after compiling, run: **java Planificador** <br />
To run algorithms independently, after compiling, run: **java (filename)** 

## Known Bugs/Limitations/Assumptions
1. Lottery_Priority: Due to the nature of this algorithm, we decided to assume that all jobs given would come in at 0. We were unsure of how to implement this algorithm since our current implementation distributes tickets to jobs in the very beginning. Having jobs come in at varying times would mess with the division of tickets we had previously done. Hence, all workloads tested for this algorithm only contain jobs with arrival times of 0. 
2. sortJobs: This is a method that we were planning on implementing to sort the jobs in our workload by arrival time. However, in the end we decided to sort the jobs ourselves in our testing cases since this really isn't part of the operating schedulers job, but rather part of our simulation. Hence, it was a design decision to do the sorting ourselves. 
3. For some reason, our algorithm would produce incorrect results in Planificador and Planificador2 if shared the same workload ArrayList object. So we decided to create different objects of the SAME workload.  
4. As stated in the Clock class, all time is assumed to be in microseconds. 
5. You may notice that the Job class contains parameters for IO functionality. Due to limitations in time, we were unable to implement this feature, so we assume that all jobs are running on the CPU. 


## Planificador.java 
* About the workload: workload1 is tested among all algorithms. All arrival times are 0 (due to Lottery_Priority limitation described above). 
* Results: All jobs finish at the same time of 30 (context-switch time excluded). With incorporation of the context-switch, it is visible that Lottery_Priority and MLFQ times increase drastically which makes sense because of the multiple instances of switching of jobs. Turnaround time is pretty similar throughout with SJF having the best turnaround time (14) and RR having the worst (23.6). Differences in response time are evident for each algorithm with MLFQ having the best response time of 4 and Fifo having the worst with 16. 
* **NOTE:** workload2 and workload3 contain the exact same jobs as workload1. However, they were created due to the bug described in the section above (#3). 

## Planificador2.java 
* About the workload: workload2 is tested among all algorithms except for Lottery_Priority since it contains varying arrival times. 
* Results: All jobs finish at the same time of 86 (context-switch time excluded). With incorporation of the context-switch, MLFQ time increases significantly compared to other algorithms. Turnaround time is pretty similar throughout with SJF still having the best turnaround time (37.4) and RR having the worst (52.2). Differences in response time are very evident for each algorithm with MLFQ having the best response time of 2.4 and Fifo having the worst with 25. 
* **NOTE:** workload3 contains the exact same jobs as workload2. However, they were created due to the bug described in the section above (#3). 

## Basic Code Structure of Algorithms  
* Necessary instance variables including Array Lists for calculating response and turnaround time. Other variables are pretty similar throughout algorithms with the additional TIMESLICE for algorithms running round robin, and S for MLFQ. 
* run(): Implemented in all algorithms. Most, if not all code, in this function performs the running of the algorithm. 
* Metric calculations: Implemented in all algorithms to use for calculation of scheduling metrics. 
```java
 public double getScheduleTime()
 public double getResponseTime()
 public double getTurnaroundTime()
 public double getContextSwitchTime() 
```
* main: Driver function. Runs tests for algorithm and performs metrics. 
* **NOTE:** Additional helper methods have been implemented for some algorithms depending on complexity.




