/**
algorithm for Shortest Job First */
import java.util.*;


public class SJF{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);

    public SJF(ArrayList<Job> joblist){
        this.joblist = joblist;
    }

    private Job findNextJob(ArrayList<Job> jobs){
        Job shortestJob = jobs.get(0);

        for (int i = 0; i < jobs.size(); i++){
            if (jobs.get(i).getArrivalTime() < clock1.getTime()) {
                if (jobs.get(i).getRunTime() < shortestJob.getRunTime()){
                    shortestJob = jobs.get(i); 
                } 
            }
        }
        return shortestJob;
    }

    public void run() {
        ArrayList<Job> tempJobList = joblist;
        clock1.setTime(tempJobList.get(0).getArrivalTime());
         
        Job firstJob = tempJobList.get(0);
        Job shortestJob = tempJobList.get(0);
        int i = 0; 
        double currentRuntime = firstJob.getRunTime();
        
        while(firstJob.getArrivalTime() == tempJobList.get(i).getArrivalTime()) {
            if (tempJobList.get(i).getRunTime() <= currentRuntime) {
                currentRuntime = tempJobList.get(i).getRunTime(); 
                shortestJob = tempJobList.get(i); 
            }
            i++; 
        }
        clock1.setTime(shortestJob.getArrivalTime());
        responseTimes.add(clock1.getTime() - shortestJob.getArrivalTime()); 
        System.out.println( shortestJob.toString()+ " starting at time: " + clock1.getTime());
        clock1.addTime(shortestJob.getRunTime());
        turnaroundTimes.add(clock1.getTime() - shortestJob.getArrivalTime());
        tempJobList.remove(shortestJob); 
        System.out.println(shortestJob.toString() + " finished at time: " + clock1.getTime());
        System.out.println();
        
        while (!tempJobList.isEmpty()){ 
            Job currJob = findNextJob(tempJobList);
            responseTimes.add(clock1.getTime() - currJob.getArrivalTime()); 
            System.out.println(currJob.toString() + " starting at time: " + clock1.getTime());

            //simulate runtime
            clock1.addTime(currJob.getRunTime());
            turnaroundTimes.add(clock1.getTime() - currJob.getArrivalTime());
            System.out.println(currJob.toString() + " finished at time: " + clock1.getTime());
            tempJobList.remove(currJob);
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
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0)); // maybe 3, 12
        jobArray.add(new Job(4, 2, false, 0)); //1, 6
        jobArray.add(new Job(1, 3, false, 0)); //2, 7
        jobArray.add(new Job(5, 3, false, 0)); //5, 18
        jobArray.add(new Job(1, 10, false, 0)); //4, 13

        SJF SJF1 = new SJF(jobArray);
        SJF1.run();


        System.out.println("Finished running SJF at " + SJF1.getScheduleTime());
        System.out.println("Total context-switch time: " + SJF1.getContextSwitchTime());
        double withContextSwitchtime = SJF1.getScheduleTime() + SJF1.getContextSwitchTime(); 
        System.out.println("Finished running SJF at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + SJF1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + SJF1.getTurnaroundTime() + "\n"); 
    }
}