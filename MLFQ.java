/**
algorithm for Multi-level Feeedback Queue */
import java.util.*;
import java.util.Random; 

public class MLFQ{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    ArrayList<Job> tempJobList = new ArrayList<Job> (10);
    Clock clock1 = new Clock();
    double TIMESLICE; 
    Queue<Job> P1 = new LinkedList<>(); 
    Queue<Job> P2 = new LinkedList<>(); 
    Queue<Job> P3 = new LinkedList<>(); 
    double S; 
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);
 


    public MLFQ(ArrayList<Job> joblist, double TIMESLICE, double S){ 
        this.joblist = joblist;
        this.tempJobList = new ArrayList<Job> (joblist); 
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
        this.S = S; 
    }

    public void runRobin(Queue<Job> removeFromQueue, Queue<Job> addToQueue, Job jobk) {
        double newRunTime = jobk.getRemainingRunTime() - TIMESLICE; 

        if (jobk.getRunTime() == jobk.getRemainingRunTime()) { // check if it is first time running 
            responseTimes.add(clock1.getTime() - jobk.getArrivalTime()); 
        }

        if (jobk.getRemainingRunTime() == TIMESLICE) { // will finish 
            System.out.println(jobk.toString() + " is running in runRobin.");
            clock1.addTime(TIMESLICE);
            tempJobList.remove(jobk); 
            removeFromQueue.remove(jobk);
            turnaroundTimes.add(clock1.getTime() - jobk.getArrivalTime());
        }
        if (jobk.getRemainingRunTime() < TIMESLICE) {
            System.out.println(jobk.toString() + " is running in runRobin.");
            clock1.addTime(jobk.getRemainingRunTime());
            tempJobList.remove(jobk);
            removeFromQueue.remove(jobk);
            turnaroundTimes.add(clock1.getTime() - jobk.getArrivalTime());
        }
        if (jobk.getRemainingRunTime() > TIMESLICE) {
            jobk.setRemainingRunTime(newRunTime);
            System.out.println(jobk.toString() + " is running in runRobin.");
            clock1.addTime(TIMESLICE);
            removeFromQueue.remove(jobk);
            addToQueue.add(jobk);
        }
    }

    public Job runJobsInP1() {
        if (P1.size() != 0) {
            System.out.println();
            System.out.println("----- Running in Q1 -----");
        }
        Job jobQ1 = null; 
         
        while (P1.size() != 0) {
            jobQ1 = P1.peek(); 
            if (jobQ1 != null && jobQ1.getArrivalTime() <= clock1.getTime()) {
                runRobin(P1, P2, jobQ1); 
            }
            else {
                break; 
            }
        }
        return jobQ1; 
    }


    public void run() {
       
        //assume jobs are sorted by greatest runtime first 
        // this.sortJobs(); 
        clock1.setTime(tempJobList.get(0).getArrivalTime());

        Job jobi; 
        for (int i = 0; i < tempJobList.size(); i++) { // add ALL jobs to first queue 
            jobi = tempJobList.get(i); 
            P1.add(jobi);
        }

        while (!tempJobList.isEmpty()) { 
            Job nextJobQ1 = runJobsInP1();
            if (P2.size() != 0) {
                System.out.println();
                System.out.println("----- Running in Q2 -----");  
            }
            
            Job jobQ2;
            while (P2.size() != 0) {
                jobQ2 = P2.peek();  
                runRobin(P2, P3, jobQ2); 
                if (clock1.getTime() >= S) { // if clock is equal to fixed int S; move ALL jobs back to 1
                    System.out.println("Reached S. Moving following jobs back to P1:");
                    while (P2.size() != 0) {
                        Job moveJob2 = P2.peek(); 
                        P1.add(moveJob2);
                        System.out.println(moveJob2.toString());
                        P2.remove(moveJob2); 
                    }
                    while (P3.size() != 0) {
                        Job moveJob3 = P3.peek(); 
                        P1.add(moveJob3);
                        System.out.println(moveJob3.toString());
                        P3.remove(moveJob3); 
                    }
                    S += S; 
                }
                if ((nextJobQ1 != null) && (nextJobQ1.getArrivalTime() <= clock1.getTime())) {
                    nextJobQ1 = runJobsInP1(); 
                    if (P2.size() != 0) {
                        System.out.println();
                        System.out.println("----- Running in Q2 -----");  
                    }
                }
            }
            if (P3.size() != 0) {
                System.out.println();
                System.out.println("----- Running in Q3 -----"); 
            }
            
            Job jobQ3;
            while (P3.size() != 0) {
                jobQ3 = P3.peek(); 
                //simulate runtime
                clock1.addTime(jobQ3.getRemainingRunTime());
                turnaroundTimes.add(clock1.getTime() - jobQ3.getArrivalTime());
                tempJobList.remove(jobQ3);
                P3.remove(jobQ3);
                System.out.println(jobQ3.toString() + " is running in Q3.");
                if (clock1.getTime() >= S) { // if clock is equal to fixed int S; move ALL jobs back to 1
                    System.out.println("Reached S. Moving following jobs back to P1:");
                    while (P2.size() != 0) {
                        Job moveJob2 = P2.peek(); 
                        P1.add(moveJob2);
                        System.out.println(moveJob2.toString());
                        P2.remove(moveJob2); 
                    }
                    while (P3.size() != 0) {
                        Job moveJob3 = P3.peek(); 
                        P1.add(moveJob3);
                        System.out.println(moveJob3.toString());
                        P3.remove(moveJob3); 
                    }
                    S += S; 
                }
                if ((nextJobQ1 != null) && (nextJobQ1.getArrivalTime() <= clock1.getTime())) {
                    nextJobQ1 = runJobsInP1(); 
                    if (P3.size() != 0) {
                        System.out.println();
                        System.out.println("----- Running in Q3 -----");  
                    }
                }
                
            }
        }
        System.out.println("total runtime: " + clock1.getTime()); 
    }

    public double getScheduleTime(){
        return this.clock1.getTime();
    }

    public double getResponseTime(){
        double i = 0; 
        for (double time: responseTimes) {
            i += time; 
        }
        i = i/responseTimes.size(); 
        return i; 
    }

    public double getTurnaroundTime(){
        double i = 0; 
        for (double time: turnaroundTimes) {
            i += time; 
        }
        i = i/turnaroundTimes.size(); 
        return i; 
    }

    public double getContextSwitchTime() {
        return clock1.getNumContextSwitch() * 1.2; 
    }

    public static void main (String[] args) {
        ArrayList<Job> workload1 = new ArrayList<Job>(5);

        workload1.add(new Job(10, 0, false, 0, 3)); 
        workload1.add(new Job(8, 0, false, 0, 2)); 
        workload1.add(new Job(6, 0, false, 0, 2)); 
        workload1.add(new Job(4, 0, false, 0, 6));
        workload1.add(new Job(2, 0, false, 0, 1));

        MLFQ MLFQ1 = new MLFQ(workload1, 2, 20);
        MLFQ1.run();

        System.out.println("\n" + "Finished running MLFQ at " + MLFQ1.getScheduleTime());
        System.out.println("Total context-switch time: " + MLFQ1.getContextSwitchTime());
        double withContextSwitchtime = MLFQ1.getScheduleTime() + MLFQ1.getContextSwitchTime(); 
        System.out.println("Finished running MLFQ at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + MLFQ1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + MLFQ1.getTurnaroundTime() + "\n"); 


        ArrayList<Job> workload2 = new ArrayList<Job>(5);

        workload2.add(new Job(20, 0, false, 0, 3)); 
        workload2.add(new Job(16, 0, false, 0, 2)); 
        workload2.add(new Job(3, 4, false, 0, 2)); 
        workload2.add(new Job(17, 10, false, 0, 6));
        workload2.add(new Job(30, 12, false, 0, 1));

        MLFQ MLFQ2 = new MLFQ(workload2, 4, 50);
        MLFQ2.run();

        System.out.println("\n" + "Finished running MLFQ at " + MLFQ2.getScheduleTime());
        System.out.println("Total context-switch time: " + MLFQ2.getContextSwitchTime());
        double withContextSwitchtime1 = MLFQ2.getScheduleTime() + MLFQ2.getContextSwitchTime(); 
        System.out.println("Finished running MLFQ at " + withContextSwitchtime1 + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + MLFQ2.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + MLFQ2.getTurnaroundTime() + "\n"); 
    }
}