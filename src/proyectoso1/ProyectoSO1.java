/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoso1;

import Classes.PartsWarehouse;
import Classes.OperationsManager;


/**
 *
 * @author samer
 */
public class ProyectoSO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PartsWarehouse ware = new PartsWarehouse();
        OperationsManager manager = new OperationsManager(10, 2000, 10);
        manager.start();
        
    }
    
}
