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
        
    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0));
        jobArray.add(new Job(4, 2, false, 0));
        jobArray.add(new Job(1, 3, false, 0));
        jobArray.add(new Job(5, 3, false, 0));
        jobArray.add(new Job(1, 10, false, 0));
        
        // call sort jobs 

        // Clock clock1 = new Clock();

        // //add the arrival time 
        // clock1.addTime(jobArray[0].getArrivalTime());
           
        // for(int i = 0; i<4; i++) {
        //     Job jobi = jobArray[i];
        //     double arriveTime = jobi.getArrivalTime();
        //     if(arriveTime > clock1.getTime()) {
        //         clock1.setTime(arriveTime);
        //     }
        //     System.out.println("Job " + (i+1) + " starting at time: " + clock1.getTime());
        //     //simulate runtime
        //     clock1.addTime(jobi.getRunTime());
        //     System.out.println("Job " + (i+1) + " finished at time: " + clock1.getTime());
        //     System.out.println();
        // }
    }
}