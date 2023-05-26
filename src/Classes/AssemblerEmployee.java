/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.concurrent.Semaphore;

/**
 *
 * @author marti
 */
public class AssemblerEmployee extends Thread {
    
    private int salary;
    private double daysToDo;
        
    private Semaphore semaphore;

    public AssemblerEmployee() {
        this.semaphore = semaphore;
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
