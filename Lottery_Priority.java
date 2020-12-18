/**
algorithm for Lottery based on Priority */
import java.util.*;
import java.util.Random; 

public class Lottery_Priority{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);

    // constructor - requires a time slice
    public Lottery_Priority(ArrayList<Job> joblist, double TIMESLICE){ 
        this.joblist = joblist;
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
    }

    /**
    Runs the scheduling algorithm simulation
     */
    public void run() {
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist); // make a copy of the joblist
        clock1.setTime(tempJobList.get(0).getArrivalTime());
    
        // calculate total time to run all jobs
        int totalRun = 0; 
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            double runtime = jobi.getRunTime();
            totalRun += runtime;
        }

        // assign tickets based on priority  
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            int priority = jobi.getPriority();
            if (priority == 1) {
                jobi.setNumTickets(100);
            }
            if (priority == 2) {
                jobi.setNumTickets(75);
            }
            if (priority == 3) {
                jobi.setNumTickets(50);
            }
            if (priority > 3) {
                jobi.setNumTickets(25);
            }
        }

        //assign ticket ranges
        int lastRange = 0;
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            int jobiNumTickets = jobi.getNumTickets();
            int[] newRange = new int[2];
            newRange[0] = lastRange;
            newRange[1] = lastRange + jobiNumTickets - 1;
            lastRange = lastRange + jobiNumTickets;
            jobi.setTicketRange(newRange);
        }

        
        while (!tempJobList.isEmpty()) {    // while there are still jobs left to run
            Job jobi = null;
            boolean picked = false;     // no job picked yet
            while(!picked) {
                Random rand = new Random();     
                int rand_int1 = rand.nextInt(lastRange);    // get new random num

                for(int i = 0; i < tempJobList.size(); i++) { // find the job with the random number
                    Job jobNext = tempJobList.get(i);
                    int[] range = jobNext.getTicketRange();
                    if(rand_int1 >= range[0] && rand_int1 <= range[1]){
                        jobi = jobNext;
                        picked = true;
                    }
                }
            }

            double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 

            if (jobi.getRunTime() == jobi.getRemainingRunTime()) { // check if this is job's first time running 
                responseTimes.add(clock1.getTime() - jobi.getArrivalTime()); // add response time
            }

            if (jobi.getRemainingRunTime() == TIMESLICE) { // if the remaining runtime is same as timeslice 
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(TIMESLICE);  // run this job (add timeslice to clock)
                tempJobList.remove(jobi);   // remove job from list 
                turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime());  // add turnaround time
            }
            if (jobi.getRemainingRunTime() < TIMESLICE) {   // if the remaining runtime is less than timeslice
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(jobi.getRemainingRunTime());     // run this job (add remaining time to clock)
                tempJobList.remove(jobi);   //remove this job from list 
                turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime());  // add turnaround time 
            }
            if (jobi.getRemainingRunTime() > TIMESLICE) {   // if remaining runtime is more than timeslice 
                jobi.setRemainingRunTime(newRunTime);       // update the job's remaining time 
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(TIMESLICE);      // run this job (add timeslice to clock)
            }
        }
    }

    public double getScheduleTime(){
        return this.clock1.getTime();
    }

    // calculate the average of response times in responseTimes list
    public double getResponseTime(){
        double i = 0; 
        for (double time: responseTimes) {
            i += time; 
        }
        i = i/responseTimes.size(); 
        return i; 
    }

    // calculate the average of turnaround times in turnaroundTimes list
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
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(10, 0, false, 0, 3)); 
        jobArray.add(new Job(8, 0, false, 0, 2)); 
        jobArray.add(new Job(6, 0, false, 0, 2)); 
        jobArray.add(new Job(4, 0, false, 0, 6));
        jobArray.add(new Job(2, 0, false, 0, 1));

        Lottery_Priority LottPrior1 = new Lottery_Priority(jobArray, 2);
        LottPrior1.run();

        System.out.println("\n" + "~~~~~~~~~~~~~~~~~~~~ CALCULATING METRICS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Finished running Lottery_Priority at " + LottPrior1.getScheduleTime());
        System.out.println("Total context-switch time: " + LottPrior1.getContextSwitchTime());
        double withContextSwitchtime = LottPrior1.getScheduleTime() + LottPrior1.getContextSwitchTime(); 
        System.out.println("Finished running Lottery_Priority at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + LottPrior1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + LottPrior1.getTurnaroundTime() + "\n"); 
    }
}