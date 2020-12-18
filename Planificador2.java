/**
Planificador runs scheduling algorithms on certain jobs.
 */

import java.util.*;

public class Planificador2 {

    public static void main (String[] args){
        ArrayList<Job> workload2 = new ArrayList<Job>(5);

        workload2.add(new Job(20, 0, false, 0, 3)); 
        workload2.add(new Job(16, 0, false, 0, 2)); 
        workload2.add(new Job(3, 4, false, 0, 2)); 
        workload2.add(new Job(17, 10, false, 0, 6));
        workload2.add(new Job(30, 12, false, 0, 1));

        
        Fifo fifo1 = new Fifo(workload2);
        System.out.println("---- RUNNING FIFO ----");
        System.out.println();
        fifo1.run();

        SJF SJF1 = new SJF(workload2);
        System.out.println();
        System.out.println("---- RUNNING SJF ----");
        System.out.println();
        SJF1.run();

        RR RR1 = new RR(workload2, 10);
        System.out.println();
        System.out.println("---- RUNNING RR ----");
        RR1.run();

        ArrayList<Job> workload3 = new ArrayList<Job>(5); // Workload is the same as above. New ArrayList object created due to referencing issues. See bugs/limitations #3 in README. 

        workload3.add(new Job(20, 0, false, 0, 3)); 
        workload3.add(new Job(16, 0, false, 0, 2)); 
        workload3.add(new Job(3, 4, false, 0, 2)); 
        workload3.add(new Job(17, 10, false, 0, 6));
        workload3.add(new Job(30, 12, false, 0, 1));

        MLFQ MLFQ1 = new MLFQ(workload3, 4, 50);
        System.out.println();
        System.out.println("---- RUNNING MLFQ ----");
        MLFQ1.run();

    
        System.out.println("\n" + "~~~~~~~~~~~~~~~~~~~~ CALCULATING METRICS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Finished running Fifo at " + fifo1.getScheduleTime());
        System.out.println("Total context-switch time: " + fifo1.getContextSwitchTime());
        double withContextSwitchtime = fifo1.getScheduleTime() + fifo1.getContextSwitchTime(); 
        System.out.println("Finished running Fifo at " + withContextSwitchtime + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + fifo1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + fifo1.getTurnaroundTime() + "\n"); 

        System.out.println("Finished running SJF at " + SJF1.getScheduleTime());
        System.out.println("Total context-switch time: " + SJF1.getContextSwitchTime());
        double withContextSwitchtime1 = SJF1.getScheduleTime() + SJF1.getContextSwitchTime(); 
        System.out.println("Finished running SJF at " + withContextSwitchtime1 + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + SJF1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + SJF1.getTurnaroundTime() + "\n"); 

        System.out.println("\n" +"Finished running RR at " + RR1.getScheduleTime());
        System.out.println("Total context-switch time: " + RR1.getContextSwitchTime());
        double withContextSwitchtime2 = RR1.getScheduleTime() + RR1.getContextSwitchTime(); 
        System.out.println("Finished running RR at " + withContextSwitchtime2 + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + RR1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + RR1.getTurnaroundTime() + "\n"); 

        System.out.println("\n" + "Finished running MLFQ at " + MLFQ1.getScheduleTime());
        System.out.println("Total context-switch time: " + MLFQ1.getContextSwitchTime());
        double withContextSwitchtime4 = MLFQ1.getScheduleTime() + MLFQ1.getContextSwitchTime(); 
        System.out.println("Finished running MLFQ at " + withContextSwitchtime4 + " with context-switch time included.");
        System.out.println("The average response time for this workload is: " + MLFQ1.getResponseTime()); 
        System.out.println("The average turnaround time for this workload is: " + MLFQ1.getTurnaroundTime() + "\n"); 
         
    }
}