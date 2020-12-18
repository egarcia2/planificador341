/**
algorithm for Shortest Job First */
import java.util.*;


public class SJF{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    ArrayList<Double> responseTimes = new ArrayList<Double>(10);
    ArrayList<Double> turnaroundTimes = new ArrayList<Double>(10);

    // constructor 
    public SJF(ArrayList<Job> joblist){
        this.joblist = joblist;
    }

    /**
     finds and returns the next shortest job given a list of jobs (normally templistjobs)
     */ 
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

    /**
    Runs the scheduling algorithm simulation
     */
    public void run() {
        ArrayList<Job> tempJobList = new ArrayList<Job> (joblist);
        clock1.setTime(tempJobList.get(0).getArrivalTime()); // advancce the clock to be that of the first job 
         
        Job firstJob = tempJobList.get(0); 
        Job shortestJob = tempJobList.get(0); // assume first job has shortest runtime 
        int i = 0; 
        double currentRuntime = firstJob.getRunTime();
        
        // determine which job of those that arrive at the same initial time has the shortest runtime 
        while((i < tempJobList.size()) && (firstJob.getArrivalTime() == tempJobList.get(i).getArrivalTime())) {
            if (tempJobList.get(i).getRunTime() <= currentRuntime) {
                currentRuntime = tempJobList.get(i).getRunTime(); 
                shortestJob = tempJobList.get(i); 
            }
            i++; 
        }
        clock1.setTime(shortestJob.getArrivalTime());
        responseTimes.add(clock1.getTime() - shortestJob.getArrivalTime()); // add response time for first job
        System.out.println( shortestJob.toString()+ " starting at time: " + clock1.getTime());
        clock1.addTime(shortestJob.getRunTime()); // run the first job
        turnaroundTimes.add(clock1.getTime() - shortestJob.getArrivalTime()); // add turnaround time for first job
        tempJobList.remove(shortestJob); // job is done running, remove
        System.out.println(shortestJob.toString() + " finished at time: " + clock1.getTime());
        System.out.println();
        
        while (!tempJobList.isEmpty()){     //while there are still jobs left to run
            Job currJob = findNextJob(tempJobList);
            responseTimes.add(clock1.getTime() - currJob.getArrivalTime()); // add respone time of current job
            System.out.println(currJob.toString() + " starting at time: " + clock1.getTime());

            //simulate runtime
            clock1.addTime(currJob.getRunTime()); //run current job 
            turnaroundTimes.add(clock1.getTime() - currJob.getArrivalTime()); // add turnaround time of current job
            System.out.println(currJob.toString() + " finished at time: " + clock1.getTime());
            tempJobList.remove(currJob); // job is done running, remove
            System.out.println();
        }

    }

    public double getScheduleTime(){
        return this.clock1.getTime();
    }

    // calculate the average of response times in responseTimes list
    public double getResponseTime(){
        double i = 0; 
        for (double time: responseTimes) {
            i += time; 
        }
        i = i/responseTimes.size(); 
        return i; 
    }

    // calculate the average of turnaround times in turnaroundTimes list
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
        ArrayList<Job> workload = new ArrayList<Job>(5);

        workload.add(new Job(5, 2, false, 0)); // maybe 3, 12
        workload.add(new Job(4, 2, false, 0)); //1, 6
        workload.add(new Job(1, 3, false, 0)); //2, 7
        workload.add(new Job(5, 3, false, 0)); //5, 18
        workload.add(new Job(1, 10, false, 0)); //4, 13

        SJF SJF1 = new SJF(workload);
        SJF1.run();

        System.out.println("\n" + "~~~~~~~~~~~~~~~~~~~~ CALCULATING METRICS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Finished running SJF at " + SJF1.getScheduleTime());
        System.out.println("Total context-switch time: " + SJF1.getContextSwitchTime());
        double withContextSwitchtime = SJF1.getScheduleTime() + SJF1.getContextSwitchTime(); 
        System.out.println("Finished running SJF at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + SJF1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + SJF1.getTurnaroundTime() + "\n"); 
    }
}