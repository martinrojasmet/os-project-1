/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

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

    public CarsPlant(int dayDuration, int maxEmployees, int dayCounter, int carsUntilAccessories, StandardVehicle standardVehicle, AccessoryVehicle accessoryVehicle) {
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
    }
    
    public void initializeWorkers() {
        
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
    
}
