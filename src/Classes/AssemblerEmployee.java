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
public class AssemblerEmployee extends Thread {
    
    private int salary;
    private double daysToDo;

    public AssemblerEmployee() {
        this.salary = 25;
        this.daysToDo = 2;
    }
    
    public int getSalary() {
        return salary;
    }

    public double getDaysToDo() {
        return daysToDo;
    }
    
}
