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
    
    private Semaphore mutex;
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
        this.mutex = new Semaphore(1);
        this.partsForACarTaken = 0;
    }
    
    public void updateStorage(String type, int qtyParts) {
        if (type.equals(EmployeeTypes.chasisEmployee)) {
            this.addAccessoriesDone(qtyParts);
        } else if (type.equals(EmployeeTypes.bodyworkEmployee)) {
            this.addBodyworksDone(qtyParts);
        } else if (type.equals(EmployeeTypes.motorEmployee)) {
            this.addMotorsDone(qtyParts);
        } else if (type.equals(EmployeeTypes.wheelEmployee)) {
            this.addWheelsDone(qtyParts);
        } else if (type.equals(EmployeeTypes.accesoryEmployee)) {
            this.addAccessoriesDone(qtyParts);
        }
    }
    
    // Functions to add parts to the warehouse
    
    public void addAccessoriesDone(int number) {
        if (this.accessoriesDone <= this.limitAccessories) {
            this.accessoriesDone += number;
        }
    }
    
    public void addBodyworksDone(int number) {
        if (this.bodyworksDone <= this.limitBodyworks) {  
            this.bodyworksDone += number;
        }
    }
    
    public void addChasisDone(int number) {
        if (this.chasisDone <= this.limitChasis) {
            this.chasisDone += number;
        }
    }
    
    public void addMotorsDone(int number) {
        if (this.motorsDone <= this.limitMotors) {  
            this.motorsDone += number;
        }
    }
    
    public void addWheelsDone(int number) {
        if (this.wheelsDone <= this.limitWheels) {  
            this.wheelsDone += number;
        }
    }
    
    // Getters and setters

    public int getAccessoriesDone() {
        return accessoriesDone;
    }

    public void setAccessoriesDone(int accessoriesDone) {
        this.accessoriesDone = accessoriesDone;
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
        return mutex;
    }

    public void setSemaphore(Semaphore mutex) {
        this.mutex = mutex;
    }

    public int getPartsForACarTaken() {
        return partsForACarTaken;
    }

    public void setPartsForACarTaken(int partsForACarTaken) {
        this.partsForACarTaken = partsForACarTaken;
    }
    
}
