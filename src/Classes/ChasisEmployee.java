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
public class ChasisEmployee extends Thread {
    
    private int salary;
    private double daysToDo;
    
    private boolean isType1;

    public ChasisEmployee(boolean isType1) {
        this.salary = 10;
        if (isType1) {
            this.daysToDo = 4;
        } else {
            this.daysToDo = 0;
        }
    }
    
    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getSalary() {
        return salary;
    }

    public double getDaysToDo() {
        return daysToDo;
    }
    
}
