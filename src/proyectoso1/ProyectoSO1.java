/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoso1;

import Classes.AccessoryEmployee;

/**
 *
 * @author samer
 */
public class ProyectoSO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("prueba");
        
        AccessoryEmployee acc = new AccessoryEmployee(true);
        double text = acc.getSalary();
        System.out.println(text);
    }
    
}
