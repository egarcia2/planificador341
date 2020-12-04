/**
algorithm for FIFO */
import java.util.*;

public class Fifo{

    Job[] joblist = new Job[10];


    public Fifo(Job[] joblist){
        this.joblist = joblist;
    }

    public void sortJobs() {
        System.out.println("implement sortJobs");
    }

    public static void main (String[] args) {
        Job jobby1 = new Job(4, 1, false, 0);
        Job jobby2 = new Job(2.25, 2, false, 0);
        Job jobby3 = new Job(3, 15, false, 0);
        Job jobby4 = new Job(1.75, 16, false, 0);
        
        Job[] jobArray = {jobby1, jobby2, jobby3, jobby4};

        Clock clock1 = new Clock();

        //add the arrival time 
        clock1.addTime(jobArray[0].getArrivalTime());
            
        for(int i = 0; i<4; i++) {
            Job jobi = jobArray[i];
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
}