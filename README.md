# planificador341
OS scheduler simulator (planificador is the spanish translation for 'scheduler')



# CS 341 Final Project: Planificador 
OS scheduler simulator (planificador is the spanish translation for 'scheduler'). ** We have used Java to implement the following: **

Our project implements the following things: 
* Five common scheduling algorithms as described in OSTEP Chapters (CPU: Scheduling, Multi-level Feedback, and Lottery Scheduling)
* Scheduling algorithms include: First In First Out (FIFO), Shortest Job First (SJF), Round Robin (RR), Lottery (Lottery_Priority), and Multi-level Feedback Queue (MLFQ)
* Four scheduling metrics for all algorithms to calculate the average runtime, average turnaround time, total completion time and total completion time with incorporation of context-switch time 
* Calculation of IO incorporation in Shortest Job First 
* Clock class to simulate time being run on the CPU 
* Job class to simulate jobs in a workload
* Planificador main class where all algorithms are run and metrics are used to compare and contrast algorithms 

## How to Compile & Run 
To compile all files on VSC, please run: ** javac *.java **
Or to run a file individually: ** javac (filename).java **

To run all algorithms and view their metrics, after compiling, run: ** java Planificador **
To view algorithms independently, run: ** java (filename) **

## Known Bugs/Limitations/Assumptions
* Lottery_Priority: Due to the nature of this algorithm, we decided to assume that all jobs given would come in at 0. We were unsure of how to implement this algorithm since our current implementation distributes tickets to jobs in the very beginning. Having jobs come in at varying times would mess with the division of tickets we had previously done. Hence, all workloads tested for this algorithm only contain jobs with arrival times of 0. 
* sortJobs: This is a method that we were planning on implementing to sort the jobs in our workload by arrival time. However, in the end we decided to sort the jobs ourselves in our testing cases since this really isn't part of the operating schedulers job, but rather part of our simulation. Hence, it was a design decision to do the sorting ourselves. 


## Tests in Planificador 
* workload1: This workload is tested among all algorithms. All arrival times are 0 (due to Lottery_Priority limitation described above). 
Ex. 1: This test that contains a mixture of builtin and executable commands that should run successfully. 
> ./shell tests/test1.in 

Ex. 2: This test that contains a mixture of builtin and executable commands that should NOT run successfully due to missing or incorrectly given files. 
> ./shell tests/test2.in

Ex. 3: This test contains a mixture of builtin and executable commands that should run successfully. Last command after exit should not be executed. 
> ./shell commands.txt

## Basic Code Structure of Algorithms  
* 

* main: Driver function. Runs tests for algorithm and performs metrics. 




