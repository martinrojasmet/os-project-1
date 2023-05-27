package proyectoso1;

import Classes.PartsWarehouse;
import Classes.OperationsManager;
import Classes.CarsPlant;


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
        CarsPlant plant = new CarsPlant(2000, 19, 10);
        OperationsManager manager = new OperationsManager(10, plant);
        manager.start();
        
    }
    
}
