/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marti
 */
public class Employee extends Thread {
    
    private float salary;
    private long durationDay;
    private float productionPerDay;
    private PartsWarehouse partsWarehouse;
    private String type;
    private float accSalary;
    private float productionCounter;

    public Employee(float salary, String type, long durationDay, float production, PartsWarehouse partsWarehouse) {
        this.partsWarehouse = partsWarehouse;
        this.durationDay = durationDay;
        this.productionPerDay = production;
        this.type = type;
        this.accSalary = 0;
        this.productionCounter = 0;
        this.salary = salary;
    }

    @Override
    public void run() {
        while(true) {
            try {

                productionOfDay();
                getPayment();
                sleep(this.durationDay);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public void productionOfDay() {
        this.productionCounter += this.productionPerDay;
        
        if (this.productionCounter >= 1) {
            try {
                
                this.partsWarehouse.getSemaphore().acquire();
                this.partsWarehouse.updateStorage(this.type, (int) this.productionCounter);
                this.partsWarehouse.getSemaphore().release();
                this.productionCounter = 0;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public long getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(long durationDay) {
        this.durationDay = durationDay;
    }

    public float getProductionPerDay() {
        return productionPerDay;
    }

    public void setProductionPerDay(float productionPerDay) {
        this.productionPerDay = productionPerDay;
    }
    
    public PartsWarehouse getPartsWarehouse() {
        return partsWarehouse;
    }

    public void setPartsWarehouse(PartsWarehouse partsWarehouse) {
        this.partsWarehouse = partsWarehouse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAccSalary() {
        return accSalary;
    }

    public void setAccSalary(float accSalary) {
        this.accSalary = accSalary;
    }

    public float getProductionCounter() {
        return productionCounter;
    }

    public void setProductionCounter(float productionCounter) {
        this.productionCounter = productionCounter;
    }
    
}
