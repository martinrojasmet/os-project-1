/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.GUI;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marti
 */
public class CarsPlant {
    
    private int dayDuration;
    private int daysToDeliver;
    private int totalDays;    
    private int grossIncome;
    private float costs;
    private int netIncome;
    private int dayCounter;    
    private Employee[] EmpList;    
    private int maxEmployees;
    private CarsWarehouse carsWarehouse;
    private PartsWarehouse partsWarehouse;
    private StandardVehicle standardVehicle;
    private AccessoryVehicle accessoryVehicle;
    private int carsUntilAccessories;
    private OperationsManager manager;
    private PlantDirector director;
    private boolean keepGoing;
    private GUI gui;
    private boolean isFirst;

    public CarsPlant(int dayDuration, int maxEmployees, int dayCounter, int carsUntilAccessories, StandardVehicle standardVehicle, AccessoryVehicle accessoryVehicle, GUI gui, boolean isFirst) {
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.totalDays = 0;
        
        this.dayDuration = dayDuration;
        this.maxEmployees = maxEmployees;       
        this.dayCounter = dayCounter;
        this.daysToDeliver = dayCounter;
        this.carsUntilAccessories = carsUntilAccessories;
        
        this.EmpList = new Employee[maxEmployees];
        this.carsWarehouse = new CarsWarehouse();
        this.partsWarehouse = new PartsWarehouse(this.carsUntilAccessories);
        this.manager = new OperationsManager(20f, this);
        this.director = new PlantDirector(30f, this.manager, this);
        this.keepGoing = true;
        this.isFirst = isFirst;
        this.gui = gui;
    }
    
    public void run() {
        this.initializeWorkers();
    }
    
    public void stop() {
        this.keepGoing = false;
        for (int i = 0; i < this.EmpList.length; i++) {
            if (this.EmpList[i] instanceof Employee) {
                this.EmpList[i].stopRunning(); //No se si esto sea concurrente
            }
        }
    }
    
    public void initializeWorkers() {
        int counter = 0;
        if (this.isFirst) {
            for (int i = 0; i < Integer.parseInt(gui.getAccessoriesEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.accesoryEmployeeSalary,
                        EmployeeInformation.accesoryEmployee, EmployeeInformation.accesoryProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getBodyworksEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.bodyworkEmployeeSalary,
                        EmployeeInformation.bodyworkEmployee, EmployeeInformation.accesoryProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getChasisEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.chasisEmployeeSalary,
                        EmployeeInformation.chasisEmployee, EmployeeInformation.chasisEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getMotorsEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.motorEmployeeSalary,
                        EmployeeInformation.motorEmployee, EmployeeInformation.motorEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getWheelsEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.wheelEmployeeSalary,
                        EmployeeInformation.wheelEmployee, EmployeeInformation.wheelEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getAssemblerEmployeeQtty().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.assemblerEmployeeSalary,
                        EmployeeInformation.assemblerEmployee, EmployeeInformation.assemblerProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }
        } else {
            for (int i = 0; i < Integer.parseInt(gui.getAccessoriesEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.accesoryEmployeeSalary,
                        EmployeeInformation.accesoryEmployee, EmployeeInformation.accesoryProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getBodyworksEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.bodyworkEmployeeSalary,
                        EmployeeInformation.bodyworkEmployee, EmployeeInformation.accesoryProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getChasisEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.chasisEmployeeSalary,
                        EmployeeInformation.chasisEmployee, EmployeeInformation.chasisEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getMotorsEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.motorEmployeeSalary,
                        EmployeeInformation.motorEmployee, EmployeeInformation.motorEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getWheelsEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.wheelEmployeeSalary,
                        EmployeeInformation.wheelEmployee, EmployeeInformation.wheelEmployeeProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getAssemblerEmployeeQtty1().getText()); i++) {
                Employee emp = new Employee(EmployeeInformation.assemblerEmployeeSalary,
                        EmployeeInformation.assemblerEmployee, EmployeeInformation.assemblerProduction, this);
                this.EmpList[counter] = emp;
                counter++;
            }
        }
        
        //espera para que no haya un null pointer exception
        try {

            sleep(7000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < this.EmpList.length; i++) {
            if (this.EmpList[i] instanceof Employee) {
                this.EmpList[i].start(); //No se si esto sea concurrente
            }
        }
    }
    
    public float getDayDurationInMs() {
        float dayDurationInMs = this.dayDuration * 1000;
        return dayDurationInMs;
    }

    // Getters and setters
    
    public int getDayDuration() {
        return dayDuration;
    }

    public void setDyDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public float getCosts() {
        return costs;
    }

    public void setCosts(float costs) {
        this.costs = costs;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    public Employee[] getEmpList() {
        return EmpList;
    }

    public void setEmpList(Employee[] EmpList) {
        this.EmpList = EmpList;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    public int getDaysToDeliver() {
        return daysToDeliver;
    }

    public void setDaysToDeliver(int daysToDeliver) {
        this.daysToDeliver = daysToDeliver;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public CarsWarehouse getCarsWarehouse() {
        return carsWarehouse;
    }

    public void setCarsWarehouse(CarsWarehouse carsWarehouse) {
        this.carsWarehouse = carsWarehouse;
    }

    public PartsWarehouse getPartsWarehouse() {
        return partsWarehouse;
    }

    public void setPartsWarehouse(PartsWarehouse partsWarehouse) {
        this.partsWarehouse = partsWarehouse;
    }

    public StandardVehicle getStandardVehicle() {
        return standardVehicle;
    }

    public void setStandardVehicle(StandardVehicle standardVehicle) {
        this.standardVehicle = standardVehicle;
    }

    public AccessoryVehicle getAccessoryVehicle() {
        return accessoryVehicle;
    }

    public void setAccessoryVehicle(AccessoryVehicle accessoryVehicle) {
        this.accessoryVehicle = accessoryVehicle;
    }

    public int getCarsUntilAccessories() {
        return carsUntilAccessories;
    }

    public void setCarsUntilAccessories(int carsUntilAccessories) {
        this.carsUntilAccessories = carsUntilAccessories;
    }

    public OperationsManager getManager() {
        return manager;
    }

    public void setManager(OperationsManager manager) {
        this.manager = manager;
    }

    public PlantDirector getDirector() {
        return director;
    }

    public void setDirector(PlantDirector director) {
        this.director = director;
    }
    
}
