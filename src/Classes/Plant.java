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
public class Plant {
    
    private int secondsForDay;
    
    private int grossIncome;
    private int costs;
    private int netIncome;
    
    private Employee[] EmpList;
    
    private int maxEmployees;

    public Plant(int secondsForDay, int maxEmployees) {
        this.secondsForDay = secondsForDay;
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.maxEmployees = maxEmployees;
        this.EmpList = new Employee[maxEmployees];
    }
    
    
    public void start() {
        PartsWarehouse ware = new PartsWarehouse();
        
    }
}
