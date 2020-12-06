/**
algorithm for Shortest Job First */
import java.util.*;


public class SJF{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 


    public SJF(ArrayList<Job> joblist){
        this.joblist = joblist;
    }

    public void sortJobs() {
        System.out.println("implement sortJobs");
    }

    public void run() {
        this.sortJobs(); 
        clock1.addTime(joblist.get(0).getArrivalTime());
         
        Job firstJob = jobList.get(0);
        Job shortestJob = jobList.get(0);
        int i = 0; 
        double currentRuntime = firstJob.getRuntime();
        
        while(firstJob.getArrivalTime() == jobList.get(i).getArrivalTime()) {
            if (jobList.get(i).getRuntime() <= currentRuntime) {
                currentRuntime = jobList.get(i).getRuntime(); 
                shortestJob = jobList.get(i); 
            }
            i++; 
        }
        clock1.setTime(shortestJob.getArrivalTime());
        System.out.println("Job 1" + " starting at time: " + clock1.getTime());
        clock1.addTime(shortestJob.getRunTime());
        jobList.remove(shortestJob); 
        System.out.println("Job 1" + " finished at time: " + clock1.getTime());

        shortestJob = jobList.get(0);
        for(int i = 0; i< joblist.size(); i++) { 
            Job jobi = joblist.get(i);
            if (jobi.getArrivalTime() < clock1.getTime()) {
                if (jobi.getRuntime() < shortestJob.getRunTime()){
                    System.out.println("Job 2" + " starting at time: " + clock1.getTime());
                }
            }
                    
            double arriveTime = jobi.getArrivalTime();
            if(arriveTime > clock1.getTime()) {
                clock1.setTime(arriveTime);
            }
            System.out.println("Job " + (i+1) + " starting at time: " + clock1.getTime());
            //simulate runtime
            clock1.addTime(jobi.getRunTime());
            System.out.println("Job " + (i+1) + " finished at time: " + clock1.getTime());
            System.out.println();
        }
    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0));
        jobArray.add(new Job(4, 2, false, 0));
        jobArray.add(new Job(1, 3, false, 0));
        jobArray.add(new Job(5, 3, false, 0));
        jobArray.add(new Job(1, 10, false, 0));
     
    }
}