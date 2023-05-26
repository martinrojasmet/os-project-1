/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author marti
 */
public class MotorEmployee extends Thread{
    
    private int salary;
    private double daysToDo;
        
    private PartsWarehouse warehouse;
    
    private boolean isType1;

    public MotorEmployee(boolean isType1, PartsWarehouse warehouse) {
        this.warehouse = warehouse;
        this.salary = 20;
        if (isType1) {
            this.daysToDo = 1;
        } else {
            this.daysToDo = 0;
        }
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getSalary() {
        return salary;
    }

    public double getDaysToDo() {
        return daysToDo;
    }
    
        private void takePartToWarehouse() {
        try {
            warehouse.getSemaphore().acquire();
            TimeUnit.SECONDS.sleep(1);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        } finally {
            warehouse.getSemaphore().release();
            
        }
    
    }
}
