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
    
    private float salary;
    private int durationDay;
    private float productionPerDay;
    private PartsWarehouse partsWarehouse;
    private CarsWarehouse carsWarehouse;
    private String type;
    private float accSalary;
    private float productionCounter;
    private boolean keepGoing;
    private CarsPlant plant;

    public Employee(float salary, String type, float production, CarsPlant plant) {
        this.partsWarehouse = plant.getPartsWarehouse();
        this.carsWarehouse = plant.getCarsWarehouse();
        this.productionPerDay = production;
        this.type = type;
        this.accSalary = 0;
        this.productionCounter = 0;
        this.salary = salary;
        this.keepGoing = true;
        this.plant = plant;
        this.durationDay = (int) plant.getDayDurationInMs();
    }

    @Override
    public void run() {
        while(this.keepGoing) {
            try {
                System.out.println("sdds");
                if (this.type.equals(EmployeeInformation.assemblerEmployee)) {
                    assembleCar();
                } else {
                    sleep(this.durationDay);
                    productionOfDay();
                    getPayment();
                }
                             
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public void productionOfDay() {
        this.productionCounter += this.productionPerDay;
        
        if (this.productionCounter >= 1) {
            try {
                this.partsWarehouse.getSemaphore().acquire();
                this.partsWarehouse.updateStorage(this.type, (int) this.productionCounter);
                this.partsWarehouse.getSemaphore().release();
                this.productionCounter = 0;

            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }
    
    public void assembleCar() {
        boolean carWithAccessories = false;
        boolean hasAccessoriesAvailable = true;
        boolean carDone = false;
        
        if (this.partsWarehouse.getCarsUntilAccessories() == 0) {
            carWithAccessories = true;
        }
        
        if (availableStandardParts()) {
            
            if (this.partsWarehouse.getAccessoriesDone() < this.plant.getAccessoryVehicle().getQtyAccessoryToProduce()) {
                hasAccessoriesAvailable = false;
            }
            
            try {
                // Se accede al almacen para tomar las partes necesarias para ensamblar un carro
                this.partsWarehouse.getSemaphore().acquire();
                if (!carWithAccessories && availableStandardParts()) {
                    this.partsWarehouse.setBodyworksDone((this.partsWarehouse.getBodyworksDone() - this.plant.getStandardVehicle().getQtyBodyworkToProduce()));
                    this.partsWarehouse.setChasisDone((this.partsWarehouse.getChasisDone() - this.plant.getStandardVehicle().getQtyChasisToProduce()));
                    this.partsWarehouse.setMotorsDone((this.partsWarehouse.getMotorsDone() - this.plant.getStandardVehicle().getQtyMotorToProduce()));
                    this.partsWarehouse.setWheelsDone((this.partsWarehouse.getWheelsDone() - this.plant.getStandardVehicle().getQtyWheelToProduce()));
                    this.partsWarehouse.setCarsUntilAccessories(this.partsWarehouse.getCarsUntilAccessories() - 1);
                    carDone = true;
                } else if (carWithAccessories && hasAccessoriesAvailable && availableStandardParts()) {
                    this.partsWarehouse.setBodyworksDone((this.partsWarehouse.getBodyworksDone() - this.plant.getStandardVehicle().getQtyBodyworkToProduce()));
                    this.partsWarehouse.setChasisDone((this.partsWarehouse.getChasisDone() - this.plant.getStandardVehicle().getQtyChasisToProduce()));
                    this.partsWarehouse.setMotorsDone((this.partsWarehouse.getMotorsDone() - this.plant.getStandardVehicle().getQtyMotorToProduce()));
                    this.partsWarehouse.setWheelsDone((this.partsWarehouse.getWheelsDone() - this.plant.getStandardVehicle().getQtyWheelToProduce()));
                    this.partsWarehouse.setAccessoriesDone((this.partsWarehouse.getAccessoriesDone() - this.plant.getAccessoryVehicle().getQtyAccessoryToProduce()));
                    this.partsWarehouse.setCarsUntilAccessories(this.partsWarehouse.getOriginalCarsUntilAccessories());
                    carDone = true;
                }
                this.partsWarehouse.getSemaphore().release();

                sleep(this.durationDay * 2);
                // Se pagan 2 días
                getPayment();
                getPayment();
                
                // Se accede al almacen de carros para almacenar el vehículo ya terminado
                if (carDone) {
                    this.carsWarehouse.getSemaphore().acquire();
                    if (carWithAccessories) {
                        this.carsWarehouse.updateAccessoryStorage(1);
                    } else {
                        this.carsWarehouse.updateStandardStorage(1);
                    }
                    this.carsWarehouse.getSemaphore().release();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean availableStandardParts() {
        return (this.partsWarehouse.getBodyworksDone() >= this.plant.getStandardVehicle().getQtyBodyworkToProduce())
                && (this.partsWarehouse.getChasisDone() >= this.plant.getStandardVehicle().getQtyChasisToProduce())
                && (this.partsWarehouse.getMotorsDone() >= this.plant.getStandardVehicle().getQtyMotorToProduce())
                && (this.partsWarehouse.getWheelsDone() >= this.plant.getStandardVehicle().getQtyWheelToProduce());
    }
    
    public void getPayment() {
        this.accSalary += this.salary * 24;
        this.plant.setCosts(this.plant.getCosts() + (this.salary * 24)); //Hay que revisar
    }
    
    public void stopRunning() {
        this.keepGoing = false;
    }
    
    // Getters and setters

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getDurationDay() {
        return durationDay;
    }

    public void setDurationDay(int durationDay) {
        this.durationDay = durationDay;
    }

    public float getProductionPerDay() {
        return productionPerDay;
    }

    public void setProductionPerDay(float productionPerDay) {
        this.productionPerDay = productionPerDay;
    }
    
    public PartsWarehouse getPartsWarehouse() {
        return partsWarehouse;
    }

    public void setPartsWarehouse(PartsWarehouse partsWarehouse) {
        this.partsWarehouse = partsWarehouse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAccSalary() {
        return accSalary;
    }

    public void setAccSalary(float accSalary) {
        this.accSalary = accSalary;
    }

    public float getProductionCounter() {
        return productionCounter;
    }

    public void setProductionCounter(float productionCounter) {
        this.productionCounter = productionCounter;
    }

    public boolean isKeepGoing() {
        return keepGoing;
    }

    public void setKeepGoing(boolean keepGoing) {
        this.keepGoing = keepGoing;
    }

    public CarsWarehouse getCarsWarehouse() {
        return carsWarehouse;
    }

    public void setCarsWarehouse(CarsWarehouse carsWarehouse) {
        this.carsWarehouse = carsWarehouse;
    }

    public CarsPlant getPlant() {
        return plant;
    }

    public void setPlant(CarsPlant plant) {
        this.plant = plant;
    }

}
