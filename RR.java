/**
algorithm for Round Robin */
import java.util.*;


public class RR{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);


    public RR(ArrayList<Job> joblist, double TIMESLICE){ 
        this.joblist = joblist;
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
    }

    public void sortJobs() {
        //System.out.println("implement sortJobs");
    }

    public void run() {
        
        this.sortJobs(); 
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist);
        clock1.setTime(tempJobList.get(0).getArrivalTime());
    

        while (!tempJobList.isEmpty()) { 
            for(int i = 0; i < tempJobList.size(); i++) {
                Job jobi = tempJobList.get(i);
                double arriveTime = jobi.getArrivalTime();
                if(arriveTime > clock1.getTime()) {
                    clock1.setTime(arriveTime);
                }
                double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 

                if (jobi.getRunTime() == jobi.getRemainingRunTime()) { // check if it is first time running 
                    responseTimes.add(clock1.getTime() - jobi.getArrivalTime()); 
                }

                if (jobi.getRemainingRunTime() == TIMESLICE) {
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);
                    tempJobList.remove(jobi); 
                    turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime());
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() < TIMESLICE) {
                    System.out.println(jobi.toString() + " is running.");

                    clock1.addTime(jobi.getRemainingRunTime());
                    tempJobList.remove(jobi);
                    turnaroundTimes.add(clock1.getTime() - jobi.getArrivalTime()); 
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() > TIMESLICE) {
                    jobi.setRemainingRunTime(newRunTime);
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);
                }
            }
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
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0)); // maybe 3, 12
        jobArray.add(new Job(4, 2, false, 0)); //1, 6
        jobArray.add(new Job(1, 3, false, 0)); //2, 7
        jobArray.add(new Job(5, 3, false, 0)); //5, 18
        jobArray.add(new Job(1, 10, false, 0)); //4, 13

        RR RR1 = new RR(jobArray, 4);
        RR1.run();

        System.out.println("\n" + "~~~~~~~~~~~~~~~~~~~~ CALCULATING METRICS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n" +"Finished running RR at " + RR1.getScheduleTime());
        System.out.println("Total context-switch time: " + RR1.getContextSwitchTime());
        double withContextSwitchtime = RR1.getScheduleTime() + RR1.getContextSwitchTime(); 
        System.out.println("Finished running RR at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + RR1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + RR1.getTurnaroundTime() + "\n"); 
        
    }
}