/**
 */
public class Job{
    //instance variables
    double runtime;
    int cost;
    double arrivalTime;
    boolean performIO;
    int numIOs;
    int priority; //if positive then uses priority, -1 is no priority

    public Job(double runtime, double arrival_time, boolean performIO, int numIOs){
        this.runtime = runtime;
        this.arrivalTime = arrival_time;
        this.performIO = performIO;
        this.numIOs = numIOs;
        this.cost = numIOs;
        this.priority = -1;
    }

    public Job(double runtime, double arrival_time, boolean performIO, int numIOs, int priority){
        this.runtime = runtime;
        this.arrivalTime = arrival_time;
        this.performIO = performIO;
        this.numIOs = numIOs;
        this.cost = numIOs;
        this.priority = priority;
    }

    public String toString(){
        return "[Job: " + runtime + ", " + arrivalTime + ", " +  cost + ", " + performIO + ", " + numIOs + ", " + priority + " ]";
    }

    public double getRunTime(){
        return this.runtime;
    }

    public double getArrivalTime(){
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

    public void setRunTime(double run_time){
        this.runtime = run_time;
    }

    public void setArrivalTime(double arrival_time){
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