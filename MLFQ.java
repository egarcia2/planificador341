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

    public void sortJobs() {
        //System.out.println("implement sortJobs");
    }

    public void runRobin(Queue<Job> removeFromQueue, Queue<Job> addToQueue, Job jobk) {
        double newRunTime = jobk.getRemainingRunTime() - TIMESLICE; 
        if (jobk.getRemainingRunTime() == TIMESLICE) { // will finish 
            System.out.println(jobk.toString() + " is running in runRobin.");
            clock1.addTime(TIMESLICE);
            tempJobList.remove(jobk); 
            removeFromQueue.remove(jobk);
        }
        if (jobk.getRemainingRunTime() < TIMESLICE) {
            System.out.println(jobk.toString() + " is running in runRobin.");
            clock1.addTime(jobk.getRemainingRunTime());
            tempJobList.remove(jobk);
            removeFromQueue.remove(jobk);
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
        clock1.addTime(tempJobList.get(0).getArrivalTime());

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
        System.out.println("Jobs finished at " + clock1.getTime());  
    }

    public static void main (String[] args) {
        // ArrayList<Job> jobArray = new ArrayList<Job>(5);

        // jobArray.add(new Job(10, 0, false, 0, 3)); 
        // jobArray.add(new Job(8, 0, false, 0, 2)); 
        // jobArray.add(new Job(6, 0, false, 0, 2)); 
        // jobArray.add(new Job(4, 0, false, 0, 6));
        // jobArray.add(new Job(2, 0, false, 0, 1));

        // MLFQ badname = new MLFQ(jobArray, 2, 20);
        // badname.run();



        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(10, 0, false, 0, 3)); 
        jobArray.add(new Job(8, 0, false, 0, 2)); 
        jobArray.add(new Job(6, 4, false, 0, 2)); 
        jobArray.add(new Job(4, 10, false, 0, 6));
        jobArray.add(new Job(2, 12, false, 0, 1));

        MLFQ badname = new MLFQ(jobArray, 2, 20);
        badname.run();
    }
}