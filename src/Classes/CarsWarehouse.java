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
    private Semaphore semaphore;

    public CarsWarehouse() {
        this.standardCarsDone = 0;
        this.accessoryCarsDone = 0;
        this.semaphore = new Semaphore(1);
    }

    
    
    public void updateStorage(int number) {
        this.standardCarsDone += number;
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
    
}
