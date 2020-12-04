/**
This is a representation of time in terms of microseconds 
 */
import java.util.*;

public class Clock{

    double time;

    public Clock() {
        this.time = 0;
    }
    
    public double getTime() {
        return this.time;
    }

    public double addOne() {
        this.time += 1;
        return this.time;
    }

    public double addTime(double time){
        this.time += time;
        return this.time;
    }

    public void addIO() {
        this.time += 1.2;
    }

    public void setTime(double t){
        this.time = t;
    }

}