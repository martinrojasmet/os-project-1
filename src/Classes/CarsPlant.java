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
    
    private int grossIncome;
    private int costs;
    private int netIncome;
    private int dayCounter;
    
    private Employee[] EmpList;
    
    private int maxEmployees;

    public CarsPlant(int dayDuration, int maxEmployees, int dayCounter) {
        this.dayDuration = dayDuration;
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.maxEmployees = maxEmployees;
        this.EmpList = new Employee[maxEmployees];
        this.dayCounter = dayCounter;
    }
    
    
    public void start() {
        PartsWarehouse ware = new PartsWarehouse();
        
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

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
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
      
}
