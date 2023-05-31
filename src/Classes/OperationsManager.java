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
    private boolean keepGoing;
    private int discountedSalary;
    
    public OperationsManager(float salary, CarsPlant plant) {
        this.salary = salary;
        this.accSalary = 0;
        this.faults = 0;
        this.isWorking = true;
        this.plant = plant;
        this.keepGoing = true;
        this.discountedSalary = 0;
    }
    
    @Override
    public void run() {
        long sixteenHours = this.getSixteenHoursInMs();
        long eightHours = (long) (this.plant.getDayDurationInMs() - sixteenHours);
        while(this.keepGoing) {
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
                this.setIsWorking(!this.isWorking);
                accTime += thirtyMinutes;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setIsWorking(true);
    }
    
    public void changeCounter() {
        try {
            this.plant.getCounterMutex().acquire();
            this.plant.setDaysToDeliver(this.plant.getDaysToDeliver() - 1);
            this.plant.setTotalDays(this.plant.getTotalDays() + 1);
            this.plant.getCounterMutex().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(OperationsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPayment() {
        this.accSalary += this.salary * 24;
        this.plant.setCosts(this.plant.getCosts() + (this.salary * 24)); //Hay que revisar
    }
 
    public void stopRunning() {
        this.keepGoing = false;
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

    public boolean isKeepGoing() {
        return keepGoing;
    }

    public void setKeepGoing(boolean keepGoing) {
        this.keepGoing = keepGoing;
    }

    public int getDiscountedSalary() {
        return discountedSalary;
    }

    public void setDiscountedSalary(int discountedSalary) {
        this.discountedSalary = discountedSalary;
    }
    
}
