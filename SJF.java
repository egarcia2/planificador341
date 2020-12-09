/**
algorithm for Shortest Job First */
import java.util.*;


public class SJF{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();


    public SJF(ArrayList<Job> joblist){
        this.joblist = joblist;
    }

    public void sortJobs() {
        //System.out.println("implement sortJobs");
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
        this.sortJobs(); 
        ArrayList<Job> tempJobList = joblist;
        clock1.addTime(tempJobList.get(0).getArrivalTime());
         
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
        System.out.println( shortestJob.toString()+ " starting at time: " + clock1.getTime());
        clock1.addTime(shortestJob.getRunTime());
        tempJobList.remove(shortestJob); 
        System.out.println(shortestJob.toString() + " finished at time: " + clock1.getTime());
        System.out.println();
        
        while (!tempJobList.isEmpty()){ 
            Job currJob = findNextJob(tempJobList);
            System.out.println(currJob.toString() + " starting at time: " + clock1.getTime());

            //simulate runtime
            clock1.addTime(currJob.getRunTime());
            System.out.println(currJob.toString() + " finished at time: " + clock1.getTime());
            tempJobList.remove(currJob);
            System.out.println();
        }

    }

    public static void main (String[] args) {
        ArrayList<Job> jobArray = new ArrayList<Job>(5);

        jobArray.add(new Job(5, 2, false, 0)); // maybe 3, 12
        jobArray.add(new Job(4, 2, false, 0)); //1, 6
        jobArray.add(new Job(1, 3, false, 0)); //2, 7
        jobArray.add(new Job(5, 3, false, 0)); //5, 18
        jobArray.add(new Job(1, 10, false, 0)); //4, 13

        SJF badname = new SJF(jobArray);
        badname.run();
    }
}