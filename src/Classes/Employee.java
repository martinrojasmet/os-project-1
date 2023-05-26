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
    
    private int salary;
    private float daysToDo;
    private int durationDay;
    private int production;
    
    private PartsWarehouse partsWarehouse;
    private CarsWarehouse carsWarehouse;
    
    private String type;

    public Employee(String type, PartsWarehouse partsWarehouse, CarsWarehouse carsWarehouse, int secondsForDay) {
        this.partsWarehouse = partsWarehouse;
        this.carsWarehouse = carsWarehouse;
        this.durationDay = secondsForDay;
        this.production = 0;
        this.type = type;
        if (this.type.equals("accessory")) {
            this.salary = 17;
            this.daysToDo = 2;
        } else if (this.type.equals("assembler")) {
            this.salary = 25;
            this.daysToDo = 2;
        } else if (this.type.equals("bodywork")) {
            this.salary = 13;
            this.daysToDo = 4;
        } else if (this.type.equals("chasis")) {
            this.salary = 10;
            this.daysToDo = 4;
        } else if (this.type.equals("motor")) {
            this.salary = 20;
            this.daysToDo = 1;
        } else if (this.type.equals("wheel")) {
            this.salary = 8;
            this.daysToDo = 0.2f;
        }
    }

    @Override
    public void run() {
        try {
            long sleepDuration = (long) (getDurationDay() * getDaysToDo());
            sleep(sleepDuration); //trabajo
        } catch (InterruptedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (getProduction() >= 1) {
            takePartToWarehouse();
        }
    }

    public int getSalary() {
        return salary;
    }

    public double getDaysToDo() {
        return daysToDo;
    }

    public int getDurationDay() {
        return durationDay;
    }

    public int getProduction() {
        return production;
    }

    public PartsWarehouse getPartsWarehouse() {
        return partsWarehouse;
    }

    public String getType() {
        return type;
    }

    public void addProduction(int number) {
        this.production = getProduction() + number;
    }

    public CarsWarehouse getCarsWarehouse() {
        return carsWarehouse;
    }
    
    private void goToWarehouse() {
        if (getType().equals("accessory")) {
            getPartsWarehouse().addAccessoriesDone(1);
        } else if (getType().equals("assembler")) {
            getCarsWarehouse().addCarDones(1);
        } else if (getType().equals("bodywork")) {
            getPartsWarehouse().addBodyworksDone(1);
        } else if (getType().equals("chasis")) {
            getPartsWarehouse().addChasisDone(1);
        } else if (getType().equals("motor")) {
            getPartsWarehouse().addMotorsDone(1);
        } else if (getType().equals("wheel")) {
            getPartsWarehouse().addWheelsDone(1);
        }
    }
    
    
    private void takePartToWarehouse() {
        try {
            getPartsWarehouse().getSemaphore().acquire();
            goToWarehouse();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        } finally {
            getPartsWarehouse().getSemaphore().release();
        }
    }
    
}
