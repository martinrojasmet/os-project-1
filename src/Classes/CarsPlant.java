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
    private boolean isBugatti;
    private int carPrice;
    private int carAccessoriesPrice;

    public CarsPlant(int dayDuration, int maxEmployees, int dayCounter, boolean isBugatti) {
        this.dayDuration = dayDuration;
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.maxEmployees = maxEmployees;
        this.EmpList = new Employee[maxEmployees];
        
        this.dayCounter = dayCounter;
        this.daysToDeliver = dayCounter;
        this.totalDays = 0;
        this.carsWarehouse = new CarsWarehouse();
        this.partsWarehouse = new PartsWarehouse(isBugatti);
        this.isBugatti = isBugatti;
        
        if (isBugatti) {
            this.carPrice = 550000;
            this.carAccessoriesPrice = 600000;
            
        } else {
            this.carPrice = 350000;
            this.carAccessoriesPrice = 700000;
        }
    }
    
    
    public void start() {
        
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

    public boolean isIsBugatti() {
        return isBugatti;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    public int getCarAccessoriesPrice() {
        return carAccessoriesPrice;
    }

    public void setCarAccessoriesPrice(int carAccessoriesPrice) {
        this.carAccessoriesPrice = carAccessoriesPrice;
    }
}
