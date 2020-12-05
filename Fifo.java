/**
algorithm for FIFO */
import java.util.*;

public class Fifo{

    Job[] joblist = new Job[10];
    Clock clock1;

    public Fifo(Job[] joblist){
        this.joblist = joblist;
        clock1 = new Clock();
    }

    public void sortJobs() {
        System.out.println("implement sortJobs");
    }

    public void run() {
        clock1.addTime(joblist[0].getArrivalTime());
            
        for(int i = 0; i< joblist.length; i++) {
            Job jobi = joblist[i];
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

    public double getScheduleTime(){
        return this.clock1.getTime();
    }

    public static void main (String[] args) {
        Job jobby1 = new Job(4, 1, false, 0);
        Job jobby2 = new Job(2.25, 2, false, 0);
        Job jobby3 = new Job(3, 15, false, 0);
        Job jobby4 = new Job(1.75, 16, false, 0);
        
        Job[] jobArray = {jobby1, jobby2, jobby3, jobby4};

        // call sort jobs
        Fifo fifo1 = new Fifo(jobArray);

        fifo1.run();
        System.out.println("Finished running Fifo at " + fifo1.getScheduleTime() + "\n");
    }
}