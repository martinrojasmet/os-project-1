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
    
    private AccessoryEmployee[] accEmpList;
    private AssemblerEmployee[] assEmpList;
    private BodyworkEmployee[] bodEmpList;
    private ChasisEmployee[] chaEmpList;
    private MotorEmployee[] motEmpList;
    
    private int maxEmployees;

    public Plant(int secondsForDay, int maxEmployees) {
        this.secondsForDay = secondsForDay;
        this.grossIncome = 0;
        this.costs = 0;
        this.netIncome = 0;
        this.maxEmployees = maxEmployees;
        this.accEmpList = new AccessoryEmployee[maxEmployees];
        this.assEmpList = new AssemblerEmployee[maxEmployees];
        this.bodEmpList = new BodyworkEmployee[maxEmployees];
        this.chaEmpList = new ChasisEmployee[maxEmployees];
        this.motEmpList = new MotorEmployee[maxEmployees];
    }
    
    
    public void start() {
        PartsWarehouse ware = new PartsWarehouse();
        AccessoryEmployee acc = new AccessoryEmployee(true, ware);
        BodyworkEmployee bod = new BodyworkEmployee(true, ware);
        
        acc.start();
        bod.start();
    }
}
