/**
algorithm for Round Robin */
import java.util.*;


public class RR{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 


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
        clock1.addTime(tempJobList.get(0).getArrivalTime());
    

        while (!tempJobList.isEmpty()) { 
            for(int i = 0; i < tempJobList.size(); i++) {
                Job jobi = tempJobList.get(i);
                double arriveTime = jobi.getArrivalTime();
                if(arriveTime > clock1.getTime()) {
                    clock1.setTime(arriveTime);
                }
                double newRunTime = jobi.getRemainingRunTime() - TIMESLICE; 

                if (jobi.getRemainingRunTime() == TIMESLICE) {
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);
                    tempJobList.remove(jobi); 
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() < TIMESLICE) {
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(jobi.getRemainingRunTime());
                    tempJobList.remove(jobi); 
                    i -= 1; 
                }
                if (jobi.getRemainingRunTime() > TIMESLICE) {
                    jobi.setRemainingRunTime(newRunTime);
                    System.out.println(jobi.toString() + " is running.");
                    clock1.addTime(TIMESLICE);
                }
            }
        }
        System.out.println("Jobs finished at " + clock1.getTime());  
    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0)); // maybe 3, 12
        jobArray.add(new Job(4, 2, false, 0)); //1, 6
        jobArray.add(new Job(1, 3, false, 0)); //2, 7
        jobArray.add(new Job(5, 3, false, 0)); //5, 18
        jobArray.add(new Job(1, 10, false, 0)); //4, 13

        RR badname = new RR(jobArray, 4);
        badname.run();
    }
}