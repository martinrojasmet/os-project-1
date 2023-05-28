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
    
    private int carDones;
    private Semaphore semaphore;

    public CarsWarehouse() {
        this.carDones = 0;
        this.semaphore = new Semaphore(1);
    }
    
    public void updateStorage(int number) {
        this.carDones += number;
    }
    
    // Getters and setters

    public int getCarDones() {
        return carDones;
    }

    public void setCarDones(int carDones) {
        this.carDones = carDones;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    
}
