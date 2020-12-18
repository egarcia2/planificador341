/**
algorithm for FIFO */
import java.util.*;

public class Fifo{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1;
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);

    public Fifo(ArrayList<Job> joblist){
        this.joblist = joblist;
        clock1 = new Clock();
    }


    public void run() {
        clock1.setTime(joblist.get(0).getArrivalTime());
            
        for(int i = 0; i < joblist.size(); i++) {
            Job jobi = joblist.get(i);
            double arriveTime = jobi.getArrivalTime();
            if(arriveTime > clock1.getTime()) {
                clock1.setTime(arriveTime);
            }
            responseTimes.add(clock1.getTime() - jobi.getArrivalTime()); 
            System.out.println("Job " + joblist.get(i) + " starting at time: " + clock1.getTime());
            //simulate runtime
            clock1.addTime(jobi.getRunTime());
            turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime());
            System.out.println("Job " + joblist.get(i) + " finished at time: " + clock1.getTime());
            System.out.println();
        }
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
        // Job jobby1 = new Job(4, 1, false, 0);
        // Job jobby2 = new Job(2.25, 2, false, 0);
        // Job jobby3 = new Job(3, 15, false, 0);
        // Job jobby4 = new Job(1.75, 16, false, 0);
        
        // Job[] jobArray = {jobby1, jobby2, jobby3, jobby4};

        // // call sort jobs
        // Fifo fifo1 = new Fifo(jobArray);

        // fifo1.run();
        // System.out.println("Finished running Fifo at " + fifo1.getScheduleTime() + "\n");
        
        ArrayList<Job> workload = new ArrayList<Job>(5); 

        workload.add(new Job(5, 2, false, 0));
        workload.add(new Job(4, 2, false, 0));
        workload.add(new Job(1, 3, false, 0));
        workload.add(new Job(5, 3, false, 0));
        workload.add(new Job(1, 10, false, 0));
        
        Fifo fifo1 = new Fifo(workload);
        fifo1.run();

        System.out.println("Finished running Fifo at " + fifo1.getScheduleTime());
        System.out.println("Total context-switch time: " + fifo1.getContextSwitchTime());
        double withContextSwitchtime = fifo1.getScheduleTime() + fifo1.getContextSwitchTime(); 
        System.out.println("Finished running Fifo at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + fifo1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + fifo1.getTurnaroundTime() + "\n"); 

        ArrayList<Job> workload2 = new ArrayList<Job>(4); 

        workload2.add(new Job(4, 1, false, 0));
        workload2.add(new Job(2.25, 2, false, 0));
        workload2.add(new Job(3, 15, false, 0));
        workload2.add(new Job(1.75, 16, false, 0));
        
        Fifo fifo2 = new Fifo(workload2);
        fifo2.run();

        System.out.println("Finished running Fifo at " + fifo2.getScheduleTime());
        System.out.println("Total context-switch time: " + fifo2.getContextSwitchTime());
        double withContextSwitchtime2 = fifo2.getScheduleTime() + fifo2.getContextSwitchTime(); 
        System.out.println("Finished running Fifo at " + withContextSwitchtime2 + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + fifo2.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + fifo2.getTurnaroundTime() + "\n"); 
        
    }
}