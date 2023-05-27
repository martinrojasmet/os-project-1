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
    private int faults;
    private CarsPlant plant;
    
    public OperationsManager(float salary, CarsPlant plant) {
        this.salary = salary;
        this.accSalary = 0;
        this.faults = 0;
        this.isWorking = true;
        this.plant = plant;
    }
    
    @Override
    public void run() {
        long sixteenHours = this.getSixteenHoursInMs();
        long eightHours = (long) (this.plant.getDayDurationInMs() - sixteenHours);
        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true) {
            try {
              
                workForSixteenHours();
                sleep(eightHours);
                changeCounter();
                getPayment();
            
            } catch (InterruptedException ex) {
                Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public long getSixteenHoursInMs() {
        int dayInHours = 24;
        long sixteenHours = (long) ((16 * this.plant.getDayDurationInMs())/dayInHours);
        return sixteenHours;
    }
    
    public long getThirtyMinutesInMs() {
        int dayInHours = 24;
        long thirtyMinutes = (long) ((0.5 * this.plant.getDayDurationInMs())/dayInHours);
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
                
            } catch (InterruptedException ex) {
                Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void changeCounter() {
        this.plant.setDaysToDeliver(this.plant.getDaysToDeliver()- 1);
    }
    
    public void getPayment() {
        this.accSalary += this.salary * 24;
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

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public CarsPlant getPlant() {
        return plant;
    }

    public void setPlant(CarsPlant plant) {
        this.plant = plant;
    }
   
}
