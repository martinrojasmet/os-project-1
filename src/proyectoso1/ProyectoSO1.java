/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoso1;

import Classes.AccessoryEmployee;
import Classes.BodyworkEmployee;
import Classes.PartsWarehouse;

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
        AccessoryEmployee acc = new AccessoryEmployee(true, ware);
        BodyworkEmployee bod = new BodyworkEmployee(true, ware);
        
        acc.start();
        bod.start();
        
    }
    
}
