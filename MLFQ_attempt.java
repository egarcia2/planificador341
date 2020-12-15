/**
algorithm for Multi-level Feeedback Queue */
import java.util.*;
import java.util.Random; 

public class MLFQ_attempt{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 
    Queue<Job> P1 = new LinkedList<>(); 
    Queue<Job> P2 = new LinkedList<>(); 
    Queue<Job> P3 = new LinkedList<>(); 
    double S; 


    public MLFQ_attempt(ArrayList<Job> joblist, double TIMESLICE){ 
        this.joblist = joblist;
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
        this.S = 1000; 
    }

    public void sortJobs() {
        //System.out.println("implement sortJobs");
    }

    /** 
     * Idea: have a for loop that is checking jobs in P1 surrounding the whole thing. This way we are constantly checking if a P1 job has come in yet.
     * If we get to a point where P1 is empty (use method of poll since it returns null if the queue is empty) OR no jobs have "come in yet" (arrival time greater than current clock)
     * then we move on to loop through P2 which runs round robin as well and adds jobs to P3. At this point we would also start to check if we have reached the fixed time S. If we do 
     * then we move all items from both P2 and P3 to P1. If we don't reach S and P2 becomes empty, then we move on to P3 and do round robin there again. 
     * 
     * ISSUES: not sure how to remove items from temp list since I'm currently trying to do tempJobList.remove(new Job(jobi)) but I think it doesn't recognize it as a type Job anymore
     * since it is from a queue now?? If you compile it, you will see the errors. 
     */


    public void run() {
       
        //assume jobs are sorted by greatest runtime first 
        // this.sortJobs(); 
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist);
        clock1.addTime(tempJobList.get(0).getArrivalTime());

        Job jobi; 
        for (int i = 0; i < tempJobList.size(); i++) { // add ALL jobs to first queue 
            jobi = tempJobList.get(i); 
            P1.add(jobi);
        }

        while (!tempJobList.isEmpty()) { // stays because it makes sure we are done with all jobs 
            for (int j = 0; j <= P1.size(); j++) {
                jobi = P1.peek(); 
                if (jobi.getArrivalTime() <= clock1.getTime()) { // run round robin on queue 1
                    double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 
                    if (jobi.getRemainingRunTime() == TIMESLICE) { // will finish 
                        System.out.println(jobi.toString() + " is running.");
                        clock1.addTime(TIMESLICE);
                    
                        tempJobList.remove(new Job(jobi)); 
                        P1.remove(jobi);
                    }
                    if (jobi.getRemainingRunTime() < TIMESLICE) {
                        System.out.println(jobi.toString() + " is running.");
                        clock1.addTime(jobi.getRemainingRunTime());
                        tempJobList.remove(new Job(jobi));
                        P1.remove(jobi);
                    }
                    if (jobi.getRemainingRunTime() > TIMESLICE) {
                        jobi.setRemainingRunTime(newRunTime);
                        System.out.println(jobi.toString() + " is running.");
                        clock1.addTime(TIMESLICE);
                        P1.remove(jobi);
                        P2.add(jobi);
                    }
                }
                else if (P1.poll() == null || jobi.getArrivalTime() > clock1.getTime()) { // either P1 is empty or no jobs have come in yet even after clock has been advanced
                    Job jobk = P2.peek(); 
                    for (int k = 0; k <= P2.size(); k ++) {
                        if (clock1.getTime() != S) {
                            double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 
                            if (jobk.getRemainingRunTime() == TIMESLICE) { // will finish 
                                System.out.println(jobk.toString() + " is running.");
                                clock1.addTime(TIMESLICE);
                                tempJobList.remove(new Job(jobk)); 
                                P2.remove(jobk);
                            }
                            if (jobk.getRemainingRunTime() < TIMESLICE) {
                                System.out.println(jobk.toString() + " is running.");
                                clock1.addTime(jobk.getRemainingRunTime());
                                tempJobList.remove(new Job(jobk));
                                P1.remove(jobk);
                            }
                            if (jobi.getRemainingRunTime() > TIMESLICE) {
                                jobi.setRemainingRunTime(newRunTime);
                                System.out.println(jobi.toString() + " is running.");
                                clock1.addTime(TIMESLICE);
                                P2.remove(jobi);
                                P3.add(jobi);
                            }
                        } // S has not been reached 
                        else if ((P2.poll() == null) && (clock1.getTime() != S)) { // nothing left in P2 or P1 and haven't reached S
                            // run round robin again 
                        }
                        else if (clock1.getTime() == S) { // if clock is equal to fixed int S; move ALL jobs back to 1
                            for (Job moveJob2: P2) {
                                P1.add(moveJob2);
                                P2.remove(moveJob2); 
                            } // move from P2 to 1
                            for (Job moveJob3: P3) {
                                P1.add(moveJob3);
                                P3.remove(moveJob3);
                            } // move from P3 to 1
                        } // S has been reached; all jobs returned to 1
                    } // looping through P2 (round robin in P2)
                } // else: no more jobs to run in P1 
            } // looping through P1 (checking if job has come in)
        } // while temp job list is NOT empty
        System.out.println("Jobs finished at " + clock1.getTime());  
    } // function RUN
     

    public static void main (String[] args) {
        // ArrayList<Job> jobArray = new ArrayList<Job>(5);

        // jobArray.add(new Job(10, 0, false, 0, 3)); 
        // jobArray.add(new Job(8, 0, false, 0, 2)); 
        // jobArray.add(new Job(6, 0, false, 0, 2)); 
        // jobArray.add(new Job(4, 0, false, 0, 6));
        // jobArray.add(new Job(2, 0, false, 0, 1));

        // MLFQ_attempt badname = new MLFQ_attempt(jobArray, 2);
        // badname.run();
        Queue<Integer> q 
            = new LinkedList<>(); 
  
        // Adds elements {0, 1, 2, 3, 4} to 
        // the queue 
        for (int i = 0; i < 5; i++) 
            q.add(i); 
        System.out.println(q.size());
    }
}