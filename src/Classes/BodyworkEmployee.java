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
public class BodyworkEmployee extends Thread{
    
    private int salary;
    private double daysToDo;
        
    private PartsWarehouse warehouse;
    
    private boolean isType1;

    public BodyworkEmployee(boolean isType1, PartsWarehouse warehouse) {
        this.warehouse = warehouse;
        this.salary = 13;
        if (isType1) {
            this.daysToDo = 4;
        } else {
            this.daysToDo = 0;
        }
    }

    @Override
    public void run() {
        takePartToWarehouse();
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
            sleep(500);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        } finally {
            warehouse.getSemaphore().release();
            
        }
    
    }
} 
