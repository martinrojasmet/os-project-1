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
public class AccessoryEmployee extends Thread {
    
    private int salary;
    private double daysToDo;
    
    private PartsWarehouse warehouse;
    
    private boolean isType1;

    public AccessoryEmployee(boolean isType1, PartsWarehouse warehouse) {
        this.warehouse = warehouse;
        this.salary = 17;
        if (isType1) {
            this.daysToDo = 2;
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
            System.out.println("thread 1 esperando");
            warehouse.getSemaphore().acquire();
            System.out.println("thread 1 corriendo");
            sleep(500);
            warehouse.addAccessoriesDone(1);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        } finally {
            warehouse.getSemaphore().release();
            System.out.println("thread 1 termino");
        }
    }
    
}
