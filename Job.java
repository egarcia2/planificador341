/**
 */
public class Job{
    //instance variables
    int runtime;
    int cost;
    int arrivalTime;
    boolean performIO;
    int numIOs;
    int priority; //if positive then uses priority, -1 is no priority

    public Job(int runtime, int arrival_time, boolean performIO, int numIOs){
        runtime = runtime;
        arrivalTime = arrival_time;
        performIO = performIO;
        numIOs = numIOs;

        cost = runtime + numIOs;
        priority = -1;
    }

    public Job(int runtime, int arrival_time, boolean performIO, int numIOs, int priority){
        runtime = runtime;
        arrivalTime = arrival_time;
        performIO = performIO;
        numIOs = numIOs;

        cost = runtime + numIOs;
        priority = priority;
    }

    public int getRunTime(){
        return this.runtime;
    }

    public int getArrivalTime(){
        return this.arrivalTime;
    }

    public boolean getPerformIO(){
        return this.performIO;
    }

    public int getNumIOs(){
        return this.numIOs;
    }

    public int getCost(){
        return this.cost;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setRunTime(int run_time){
        this.runtime = run_time;
    }

    public void setArrivalTime(int arrival_time){
        this.arrivalTime = arrival_time;
    }

    public void setPerformIO(boolean perform_IO){
        this.performIO = perform_IO;
    }

    public void setNumIOs(int num_IOs){
        this.numIOs = num_IOs;
    }

    public void setCost(int costco){
        this.cost = costco;
    }

    public void setPriority(int prioritee){
        this.priority = prioritee;
    }
    
    public static void main (String[] args){
        Job jobby1 = new Job(5, 0, false, 0, 1);
        Job jobby2 = new Job(4, 1, false, 0);
        
        System.out.println(jobby1.getRunTime());
        System.out.println(jobby1.getArrivalTime());
        System.out.println(jobby1.getPriority());
    
    }

}