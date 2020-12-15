/**
algorithm for Multi-level Feeedback Queue */
import java.util.*;
import java.util.Random; 

public class MLFQ{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 
    Queue<Integer> P1 = new LinkedList<>(); 
    Queue<Integer> P2 = new LinkedList<>(); 
    Queue<Integer> P3 = new LinkedList<>(); 


    public MLFQ(ArrayList<Job> joblist, double TIMESLICE){ 
        this.joblist = joblist;
        clock1 = new Clock(); 
        this.TIMESLICE = TIMESLICE;
    }

    public void sortJobs() {
        //System.out.println("implement sortJobs");
    }

    public void run() {
       
        //assume jobs are sorted by greatest runtime first 
        // this.sortJobs(); 
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist);
        clock1.addTime(tempJobList.get(0).getArrivalTime());

        Job jobi; 
        for (int i = 0; i < tempJobList.size(); i++) {
            jobi = tempJobList[i]; 
            if (jobi.getArrivalTime() <= clock1.getTime()) { 
                P1.add(jobi);
            }
        while (!tempJobList.isEmpty()) { // stays because it makes sure we are done with all jobs 



       






            }
            // System.out.println("jobi picked: " + jobi.toString());
            double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 

            if (jobi.getRemainingRunTime() == TIMESLICE) {
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(TIMESLICE);

                tempJobList.remove(jobi); 
            }
            if (jobi.getRemainingRunTime() < TIMESLICE) {
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(jobi.getRemainingRunTime());
                tempJobList.remove(jobi); 
                
            }
            if (jobi.getRemainingRunTime() > TIMESLICE) {
                jobi.setRemainingRunTime(newRunTime);
                System.out.println(jobi.toString() + " is running.");
                clock1.addTime(TIMESLICE);
            }
        }
        System.out.println("Jobs finished at " + clock1.getTime());  
        
    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(10, 0, false, 0, 3)); 
        jobArray.add(new Job(8, 0, false, 0, 2)); 
        jobArray.add(new Job(6, 0, false, 0, 2)); 
        jobArray.add(new Job(4, 0, false, 0, 6));
        jobArray.add(new Job(2, 0, false, 0, 1));

        Lottery_Priority badname = new Lottery_Priority(jobArray, 2);
        badname.run();
    }
}