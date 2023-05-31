/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.concurrent.Semaphore;

/**
 *
 * @author marti
 */
public class CarsWarehouse {
    
    private int standardCarsDone;
    private int accessoryCarsDone;
    private int standardCarsAvailable;
    private int accessoryCarsAvailable;
    private Semaphore semaphore;

    public CarsWarehouse() {
        this.standardCarsDone = 0;
        this.accessoryCarsDone = 0;
        this.standardCarsAvailable = 0;
        this.accessoryCarsAvailable = 0;
        this.semaphore = new Semaphore(1);
    }
  
    public void updateStandardStorage(int number) {
        this.standardCarsDone += number;
        this.standardCarsAvailable += number;
    }
    
    public void updateAccessoryStorage(int number) {
        this.accessoryCarsDone += number;
        this.accessoryCarsAvailable += number;
    }
    
    // Getters and setters

    public int getStandardCarsDone() {
        return standardCarsDone;
    }

    public void setStandardCarsDone(int standardCarsDone) {
        this.standardCarsDone = standardCarsDone;
    }

    public int getAccessoryCarsDone() {
        return accessoryCarsDone;
    }

    public void setAccessoryCarsDone(int accessoryCarsDone) {
        this.accessoryCarsDone = accessoryCarsDone;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public int getStandardCarsAvailable() {
        return standardCarsAvailable;
    }

    public void setStandardCarsAvailable(int standardCarsAvailable) {
        this.standardCarsAvailable = standardCarsAvailable;
    }

    public int getAccessoryCarsAvailable() {
        return accessoryCarsAvailable;
    }

    public void setAccessoryCarsAvailable(int accessoryCarsAvailable) {
        this.accessoryCarsAvailable = accessoryCarsAvailable;
    }
    
}
