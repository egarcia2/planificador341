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
            System.out.println(jobk.toString() + " is running.");
            clock1.addTime(TIMESLICE);
            tempJobList.remove(jobk); 
            removeFromQueue.remove(jobk);
        }
        if (jobk.getRemainingRunTime() < TIMESLICE) {
            System.out.println(jobk.toString() + " is running.");
            clock1.addTime(jobk.getRemainingRunTime());
            tempJobList.remove(jobk);
            removeFromQueue.remove(jobk);
        }
        if (jobk.getRemainingRunTime() > TIMESLICE) {
            jobk.setRemainingRunTime(newRunTime);
            System.out.println(jobk.toString() + " is running.");
            clock1.addTime(TIMESLICE);
            removeFromQueue.remove(jobk);
            addToQueue.add(jobk);
        }
    }

    public Job runJobsInP1() {
        Job jobQ1 = null; 
        for (int j = 1; j <= P1.size(); j++) {
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

        while (!tempJobList.isEmpty()) { // stays because it makes sure we are done with all jobs 
            Job nextJobQ1 = runJobsInP1();
            
            System.out.println("---------");
            int atQ1 = 1; 
            System.out.println("At Q1 for " + atQ1 + " time." + "Q2 is following: "); 
            for (Job jobs2: P2) {
                System.out.println(jobs2.toString()); 
            }
            System.out.println("Current job list is:"); 
            for (Job jobinList: tempJobList) {
                System.out.println(jobinList.toString());
            }
            System.out.println("---------");
            atQ1++; 


            Job jobQ2;
            for (int k = 1; k <= P2.size(); k++) {
                // jobQ1 arrrival time is <= clock time then go to for loop
                jobQ2 = P2.peek();  
                runRobin(P2, P3, jobQ2); 
                if (clock1.getTime() >= S) { // if clock is equal to fixed int S; move ALL jobs back to 1
                    System.out.println("Reached S. Moving following jobs back to P1:");
                    for (Job moveJob2: P2) {
                        P1.add(moveJob2);
                        System.out.println(moveJob2.toString());
                        P2.remove(moveJob2); 
                    } // move from P2 to 1
                    for (Job moveJob3: P3) {
                        P1.add(moveJob3);
                        System.out.println(moveJob3.toString());
                        P3.remove(moveJob3);
                    } 
                    S += S;  
                }
                if ((nextJobQ1 != null) && (nextJobQ1.getArrivalTime() <= clock1.getTime())) {
                    nextJobQ1 = runJobsInP1(); 
                }
            }
            Job jobQ3;
            for (int m = 1; m <= P3.size(); m++) {
                jobQ3 = P3.peek(); 
                //simulate runtime
                clock1.addTime(jobQ3.getRunTime());
                tempJobList.remove(jobQ3);
                P3.remove(jobQ3);
                System.out.println(jobQ3.toString() + " is running.");
                if (clock1.getTime() >= S) { // if clock is equal to fixed int S; move ALL jobs back to 1
                    System.out.println("Reached S. Moving following jobs back to P1:");
                    for (Job moveJob2: P2) {
                        P1.add(moveJob2);
                        System.out.println(moveJob2.toString());
                        P2.remove(moveJob2); 
                    } // move from P2 to 1
                    for (Job moveJob3: P3) {
                        P1.add(moveJob3);
                        System.out.println(moveJob3.toString());
                        P3.remove(moveJob3);
                    }  
                    S += S; 
                }
                if ((nextJobQ1 != null) && (nextJobQ1.getArrivalTime() <= clock1.getTime())) {
                    nextJobQ1 = runJobsInP1(); 
                }
            }
        System.out.println("Jobs finished at " + clock1.getTime());  
        }
    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(10, 0, false, 0, 3)); 
        jobArray.add(new Job(8, 0, false, 0, 2)); 
        jobArray.add(new Job(6, 0, false, 0, 2)); 
        jobArray.add(new Job(4, 0, false, 0, 6));
        jobArray.add(new Job(2, 0, false, 0, 1));

        MLFQ badname = new MLFQ(jobArray, 2, 20);
        badname.run();
    }
}