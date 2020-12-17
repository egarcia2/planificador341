/**
This is a representation of time in terms of microseconds 
 */
import java.util.*;

public class Clock{

    double time;
    int numContextSwitch; 

    public Clock() {
        this.time = 0;
        this.numContextSwitch= 0; 
    }
    
    public double getTime() {
        return this.time;
    }

    public int getNumContextSwitch() {
        return this.numContextSwitch;
    }

    public double addOne() {
        this.time += 1;
        return this.time;
    }

    public double addTime(double time){
        this.time += time;
        this.numContextSwitch +=1; 
        return this.time;
    }

    public void addIO() {
        this.time += 1.2;
    }

    public void setTime(double t){
        this.time = t;
    }

}