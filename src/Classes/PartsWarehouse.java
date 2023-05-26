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
public class PartsWarehouse {
    
    private int accessoriesDone;
    private int bodyworksDone;
    private int chasisDone;
    private int motorsDone;
    private int wheelsDone;
    
    private int limitAccessories;
    private int limitBodyworks;
    private int limitChasis;
    private int limitMotors;
    private int limitWheels;
    
    private Semaphore semaphore;
    
    private int partsForACarTaken;

    public PartsWarehouse() {
        this.accessoriesDone = 0;
        this.bodyworksDone = 0;
        this.chasisDone = 0;
        this.motorsDone = 0;
        this.wheelsDone = 0;
        this.limitAccessories = 10;
        this.limitBodyworks = 20;
        this.limitChasis = 25;
        this.limitMotors = 55;
        this.limitWheels = 35;
        this.semaphore = new Semaphore(1);
        this.partsForACarTaken = 0;
    }

    public int getAccessoriesDone() {
        return accessoriesDone;
    }

    public void addAccessoriesDone(int number) {
        this.accessoriesDone = getAccessoriesDone() + number;
    }

    public int getBodyworksDone() {
        return bodyworksDone;
    }

    public void addBodyworksDone(int number) {
        this.accessoriesDone = getAccessoriesDone() + number;
    }

    public int getChasisDone() {
        return chasisDone;
    }

    public void addChasisDone(int number) {
        this.accessoriesDone = getAccessoriesDone() + number;
    }

    public int getMotorsDone() {
        return motorsDone;
    }

    public void addMotorsDone(int number) {
        this.accessoriesDone = getAccessoriesDone() + number;
    }

    public int getWheelsDone() {
        return wheelsDone;
    }

    public void addWheelsDone(int number) {
        this.accessoriesDone = getAccessoriesDone() + number;
    }

    public int getLimitAccessories() {
        return limitAccessories;
    }

    public int getLimitBodyworks() {
        return limitBodyworks;
    }

    public int getLimitChasis() {
        return limitChasis;
    }

    public int getLimitMotors() {
        return limitMotors;
    }

    public int getLimitWheels() {
        return limitWheels;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public int getPartsForACarTaken() {
        return partsForACarTaken;
    }

    public void setPartsForACarTaken(int partsForACarTaken) {
        this.partsForACarTaken = partsForACarTaken;
    }
    
}
