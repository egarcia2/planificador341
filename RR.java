/**
algorithm for Round Robin */
import java.util.*;


public class RR{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);


    // constructor - requires a time slice
    public RR(ArrayList<Job> joblist, double TIMESLICE){ 
        this.joblist = joblist;
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
    }

     /**
    Runs the scheduling algorithm simulation
     */
    public void run() {
        
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist);
        clock1.setTime(tempJobList.get(0).getArrivalTime()); // advancce the clock to be that of the first job 
    

        while (!tempJobList.isEmpty()) {    // while there are still jobs left to run
            for(int i = 0; i < tempJobList.size(); i++) { // for every job that's left in the queue
                Job jobi = tempJobList.get(i);
                double arriveTime = jobi.getArrivalTime(); 
                if(arriveTime > clock1.getTime()) {
                    clock1.setTime(arriveTime);     // advance the clock to simulate the next job has arrived
                }
                double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; // calculate new runtime for this job

                if (jobi.getRunTime() == jobi.getRemainingRunTime()) { // check if this is job's first time running 
                    responseTimes.add(clock1.getTime() - jobi.getArrivalTime()); // add response time
                }

                if (jobi.getRemainingRunTime() == TIMESLICE) { // if the remaining runtime is same as timeslice 
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);      // run this job (add timeslice to clock)
                    tempJobList.remove(jobi);       // remove job from list
                    turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime()); // add turnaround time
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() < TIMESLICE) { // if the remaining runtime is less than timeslice 
                    System.out.println(jobi.toString() + " is running.");

                    clock1.addTime(jobi.getRemainingRunTime());     // run this job (add remaining time to clock)
                    tempJobList.remove(jobi);           // remove this job from list 
                    turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime()); // add turnaround time
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() > TIMESLICE) {   // if remaining runtime is more than timeslice
                    jobi.setRemainingRunTime(newRunTime);       // update the job's remaining time 
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);      // run this job (add timeslice to clock)
                }
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

        jobArray.add(new Job(5, 2, false, 0)); // maybe 3, 12
        jobArray.add(new Job(4, 2, false, 0)); //1, 6
        jobArray.add(new Job(1, 3, false, 0)); //2, 7
        jobArray.add(new Job(5, 3, false, 0)); //5, 18
        jobArray.add(new Job(1, 10, false, 0)); //4, 13

        RR RR1 = new RR(jobArray, 4);
        RR1.run();

        System.out.println("\n" + "~~~~~~~~~~~~~~~~~~~~ CALCULATING METRICS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Finished running RR at " + RR1.getScheduleTime());
        System.out.println("Total context-switch time: " + RR1.getContextSwitchTime());
        double withContextSwitchtime = RR1.getScheduleTime() + RR1.getContextSwitchTime(); 
        System.out.println("Finished running RR at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + RR1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + RR1.getTurnaroundTime() + "\n"); 
        
    }
}