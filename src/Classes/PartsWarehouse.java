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
        System.out.println(getAccessoriesDone());
    }

    public int getBodyworksDone() {
        return bodyworksDone;
    }

    public void setBodyworksDone(int bodyworksDone) {
        this.bodyworksDone = bodyworksDone;
    }

    public int getChasisDone() {
        return chasisDone;
    }

    public void setChasisDone(int chasisDone) {
        this.chasisDone = chasisDone;
    }

    public int getMotorsDone() {
        return motorsDone;
    }

    public void setMotorsDone(int motorsDone) {
        this.motorsDone = motorsDone;
    }

    public int getWheelsDone() {
        return wheelsDone;
    }

    public void setWheelsDone(int wheelsDone) {
        this.wheelsDone = wheelsDone;
    }

    public int getLimitAccessories() {
        return limitAccessories;
    }

    public void setLimitAccessories(int limitAccessories) {
        this.limitAccessories = limitAccessories;
    }

    public int getLimitBodyworks() {
        return limitBodyworks;
    }

    public void setLimitBodyworks(int limitBodyworks) {
        this.limitBodyworks = limitBodyworks;
    }

    public int getLimitChasis() {
        return limitChasis;
    }

    public void setLimitChasis(int limitChasis) {
        this.limitChasis = limitChasis;
    }

    public int getLimitMotors() {
        return limitMotors;
    }

    public void setLimitMotors(int limitMotors) {
        this.limitMotors = limitMotors;
    }

    public int getLimitWheels() {
        return limitWheels;
    }

    public void setLimitWheels(int limitWheels) {
        this.limitWheels = limitWheels;
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
