/**
algorithm for Round Robin */
import java.util.*;
import java.util.Random; 

public class Lottery2{

    ArrayList<Job> joblist = new ArrayList<Job>(10); 
    Clock clock1 = new Clock();
    double TIMESLICE; 


    public Lottery2(ArrayList<Job> joblist, double TIMESLICE){ 
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
    
        //CALC TOTAL TIME TO RUN ALL JOBS
        int totalRun = 0; 
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            double runtime = jobi.getRunTime();
            totalRun += runtime;
        }
        System.out.println("total runtime: " + totalRun);

        //calc num tickets based on %runtime 
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            int priority = jobi.getPriority();
            if (priority == 1) {
                jobi.setNumTickets(100);
            }
            if (priority == 2) {
                jobi.setNumTickets(75);
            }
            if (priority == 3) {
                jobi.setNumTickets(50);
            }
            if (priority > 3) {
                jobi.setNumTickets(25);
            }
            // int numTickets = (int) Math.floor((runtime/totalRun) * 100);
            
            // System.out.println("num tickets: " + numTickets);
        }

        //assign ticket ranges
        int lastRange = 0;
        for(int i = 0; i < tempJobList.size(); i++) {
            Job jobi = tempJobList.get(i);
            int jobiNumTickets = jobi.getNumTickets();
            int[] newRange = new int[2];
            newRange[0] = lastRange;
            // System.out.println(i + " low: " + lastRange);
            // System.out.println(i + " high: " + (lastRange + jobiNumTickets - 1));
            newRange[1] = lastRange + jobiNumTickets - 1;
            lastRange = lastRange + jobiNumTickets;
            jobi.setTicketRange(newRange);
        }

        
        while (!tempJobList.isEmpty()) { 
            Job jobi = null;
            boolean picked = false;
            while(!picked) {
                Random rand = new Random(); 
                int rand_int1 = rand.nextInt(lastRange);
                // System.out.println("random number: "+ rand_int1);

                //find the job with the random number 
                for(int i = 0; i < tempJobList.size(); i++) {
                    Job jobNext = tempJobList.get(i);
                    int[] range = jobNext.getTicketRange();
                    // System.out.println("range low " + range[0]);
                    // System.out.println("range high " + range[1]);
                    if(rand_int1 >= range[0] && rand_int1 <= range[1]){
                        jobi = jobNext;
                        picked = true;
                    }
                }
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

        Lottery2 badname = new Lottery2(jobArray, 2);
        badname.run();
    }
}