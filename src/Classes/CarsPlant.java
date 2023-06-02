/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.GUI;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class CarsPlant {
    
    private String displayName;
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
    private GUI gui;
    private boolean isFirst;
    private Semaphore counterMutex;
    private boolean isRunning;
    private boolean alreadyStarted;

    public CarsPlant(String displayName, int dayDuration, int maxEmployees, int dayCounter, int carsUntilAccessories, StandardVehicle standardVehicle, AccessoryVehicle accessoryVehicle, GUI gui, boolean isFirst) {
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.totalDays = 0;
        
        this.displayName = displayName;
        this.dayDuration = dayDuration;
        this.maxEmployees = maxEmployees;       
        this.dayCounter = dayCounter;
        this.daysToDeliver = dayCounter;
        this.carsUntilAccessories = carsUntilAccessories;
        this.standardVehicle = standardVehicle;
        this.accessoryVehicle = accessoryVehicle;
        
        this.EmpList = new Employee[maxEmployees];
        this.carsWarehouse = new CarsWarehouse();
        this.partsWarehouse = new PartsWarehouse(this.carsUntilAccessories);
        this.manager = new OperationsManager(20f, this);
        this.director = new PlantDirector(30f, this.manager, this);
        this.isFirst = isFirst;
        this.gui = gui;
        this.counterMutex = new Semaphore(1);
        this.isRunning = false;
    }
    
    public void run() {
        if (!this.isRunning && !this.alreadyStarted) {
            this.manager.start();
            this.director.start();
            this.initializeWorkers();
            this.isRunning = true;
            this.alreadyStarted = true;
        } else {
            JOptionPane.showMessageDialog(null, "No se puede correr la simulación");
        }
    }
    
    public void stop() {
        if (isRunning && this.alreadyStarted) {
            for (int i = 0; i < this.EmpList.length; i++) {
               if (this.EmpList[i] instanceof Employee) {
                   this.EmpList[i].stopRunning();
               }
           }
           this.director.stopRunning();
           this.manager.stopRunning();
           this.isRunning = false;
        } else {
            JOptionPane.showMessageDialog(null, "No se puede parar la simulación");
        }
    }
    
    public void initializeWorkers() {
        int counter = 0; 
        if (this.displayName.equals("bugatti")) {
            for (int i = 0; i < Integer.parseInt(gui.getAccessoriesEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.accesoryEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getBodyworksEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.bodyworkEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getChasisEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.chasisEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getMotorsEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.motorEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getWheelsEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.wheelEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getAssemblerEmployeeQtty2().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.assemblerEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }
        } else if (this.displayName.equals("maserati")) {
            for (int i = 0; i < Integer.parseInt(gui.getAccessoriesEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.accesoryEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getBodyworksEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.bodyworkEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getChasisEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.chasisEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getMotorsEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.motorEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getWheelsEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.wheelEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }

            for (int i = 0; i < Integer.parseInt(gui.getAssemblerEmployeeQtty1().getText()); i++) {
                Employee emp = createEmployee(EmployeeInformation.assemblerEmployee);
                this.EmpList[counter] = emp;
                counter++;
            }
        }
        
        for (int i = 0; i < this.EmpList.length; i++) {
            if (this.EmpList[i] instanceof Employee) {
                this.EmpList[i].start();
            }
        }
    }
    
    public Employee createEmployee(String type) {
        if (type.equals(EmployeeInformation.chasisEmployee)) {
            Employee emp = new Employee(EmployeeInformation.chasisEmployeeSalary, 
                    EmployeeInformation.chasisEmployee, EmployeeInformation.chasisEmployeeProduction, this);
            return emp;
        } else if (type.equals(EmployeeInformation.motorEmployee)) {
            Employee emp = new Employee(EmployeeInformation.motorEmployeeSalary, 
                    EmployeeInformation.motorEmployee, EmployeeInformation.motorEmployeeProduction, this);
            return emp;
        } else if (type.equals(EmployeeInformation.bodyworkEmployee)) {
            Employee emp = new Employee(EmployeeInformation.bodyworkEmployeeSalary, 
                    EmployeeInformation.bodyworkEmployee, EmployeeInformation.bodyworkProduction, this);
            return emp;
        } else if (type.equals(EmployeeInformation.wheelEmployee)) {
            Employee emp = new Employee(EmployeeInformation.wheelEmployeeSalary, 
                    EmployeeInformation.wheelEmployee, EmployeeInformation.wheelEmployeeProduction, this);
            return emp;
        } else if (type.equals(EmployeeInformation.accesoryEmployee)) {
            Employee emp = new Employee(EmployeeInformation.accesoryEmployeeSalary, 
                    EmployeeInformation.accesoryEmployee, EmployeeInformation.accesoryProduction, this);
            return emp;
        } else if (type.equals(EmployeeInformation.assemblerEmployee)) {
            Employee emp = new Employee(EmployeeInformation.assemblerEmployeeSalary, 
                    EmployeeInformation.assemblerEmployee, EmployeeInformation.assemblerProduction, this);
            return emp;
        }
        return null;
    }
    
    public void addEmployee(Employee employee) {
        boolean added = false;
        for (int i=0; i < this.EmpList.length; i++) {
            if (this.EmpList[i] == null && !added) {
                this.EmpList[i] = employee;
                if (isRunning) {
                    this.EmpList[i].start(); 
                }
                added = true;
            }
        } 
    }
    
    public void removeEmployee(String type) {
        boolean deleted = false;
        for (int i=0; i < this.EmpList.length; i++) {
            if (this.EmpList[i] != null && this.EmpList[i].getType().equals(type) && !deleted) {
                this.EmpList[i].stopRunning();
                this.EmpList[i] = null;
                deleted = true;
            }
        }
    }
    
    public float getDayDurationInMs() {
        float dayDurationInMs = this.dayDuration * 1000;
        return dayDurationInMs;
    }
    
    public void calculateNetIncome() {
        this.setNetIncome((int) (this.getGrossIncome() - this.getCosts()));
    }
    
    
     // Getters and setters
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {    
        this.displayName = displayName;
    }

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

    public Semaphore getCounterMutex() {
        return counterMutex;
    }

    public void setCounterMutex(Semaphore counterMutex) {
        this.counterMutex = counterMutex;
    }
    
}
