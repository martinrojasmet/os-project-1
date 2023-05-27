/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samer
 */
public class OperationsManager extends Thread {
    private float salary;
    private float accSalary;
    private boolean isWorking;
    private long dayDuration;
    private int dayCounter;
    
    public OperationsManager(float salary, long dayDuration, int dayCounter) {
        this.salary = salary;
        this.accSalary = 0;
        this.isWorking = true;
        this.dayDuration = dayDuration;
        this.dayCounter = dayCounter;
    }
    
    @Override
    public void run() {
        long sixteenHours = this.getSixteenHoursInMs();
        long eightHours = this.dayDuration - sixteenHours;
        while(true) {
            try {
                
                if (this.dayCounter == 0) {
                    break;
                } else {
                    workForSixteenHours();
                    sleep(eightHours);
                    changeCounter();
                }
            
            } catch (InterruptedException ex) {
                Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public long getSixteenHoursInMs() {
        int dayInHours = 24;
        long sixteenHours = (16 * this.dayDuration)/dayInHours;
        return sixteenHours;
    }
    
    public long getThirtyMinutesInMs() {
        int dayInHours = 24;
        long thirtyMinutes = (long) ((0.5 * this.dayDuration)/dayInHours);
        return thirtyMinutes;
    }
    
    public void workForSixteenHours() {
        long sixteenHours = this.getSixteenHoursInMs();
        long thirtyMinutes = this.getThirtyMinutesInMs();
        long accTime = 0;
        
        while(accTime <= sixteenHours) {
            try {
                
                sleep(thirtyMinutes);
                this.setIsWorking(!isWorking);
                accTime += thirtyMinutes;
                if (this.isWorking) {
                    System.out.println("Trabajando");
                } else {
                    System.out.println("Viendo carreras");
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void changeCounter() {
        this.setDayCounter(dayCounter - 1);
        System.out.println("Cambiando contador - Dias restantes: " + this.dayCounter);
    }

    // Getters and setters
    
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getAccSalary() {
        return accSalary;
    }

    public void setAccSalary(float accSalary) {
        this.accSalary = accSalary;
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public long getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(long dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }
    
}
