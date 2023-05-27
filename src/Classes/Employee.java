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
        if (this.type.equals(EmployeeTypes.accesoryEmployee)) {
            this.salary = 17;
            this.daysToDo = 2;
        } else if (this.type.equals(EmployeeTypes.assemblerEmployee)) {
            this.salary = 25;
            this.daysToDo = 2;
        } else if (this.type.equals(EmployeeTypes.bodyworkEmployee)) {
            this.salary = 13;
            this.daysToDo = 4;
        } else if (this.type.equals(EmployeeTypes.chasisEmployee)) {
            this.salary = 10;
            this.daysToDo = 4;
        } else if (this.type.equals(EmployeeTypes.motorEmployee)) {
            this.salary = 20;
            this.daysToDo = 1;
        } else if (this.type.equals(EmployeeTypes.wheelEmployee)) {
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
    
    private void goToWarehouse() {
        if (getType().equals(EmployeeTypes.accesoryEmployee)) {
            getPartsWarehouse().addAccessoriesDone(1);
        } else if (getType().equals(EmployeeTypes.assemblerEmployee)) {
            getCarsWarehouse().addCarDones(1);
        } else if (getType().equals(EmployeeTypes.bodyworkEmployee)) {
            getPartsWarehouse().addBodyworksDone(1);
        } else if (getType().equals(EmployeeTypes.chasisEmployee)) {
            getPartsWarehouse().addChasisDone(1);
        } else if (getType().equals(EmployeeTypes.motorEmployee)) {
            getPartsWarehouse().addMotorsDone(1);
        } else if (getType().equals(EmployeeTypes.wheelEmployee)) {
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
    
    public void addProduction(int number) {
        this.production = getProduction() + number;
    }
    
    // Getters and setters
    
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public float getDaysToDo() {
        return daysToDo;
    }

    public void setDaysToDo(float daysToDo) {
        this.daysToDo = daysToDo;
    }

    public int getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(int durationDay) {
        this.durationDay = durationDay;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public PartsWarehouse getPartsWarehouse() {
        return partsWarehouse;
    }

    public void setPartsWarehouse(PartsWarehouse partsWarehouse) {
        this.partsWarehouse = partsWarehouse;
    }

    public CarsWarehouse getCarsWarehouse() {
        return carsWarehouse;
    }

    public void setCarsWarehouse(CarsWarehouse carsWarehouse) {
        this.carsWarehouse = carsWarehouse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
