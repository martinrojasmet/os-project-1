package proyectoso1;

import Classes.PartsWarehouse;
import Classes.OperationsManager;
import Classes.CarsPlant;
import Classes.PlantDirector;

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
        CarsPlant plant = new CarsPlant(1, 19, 10);
        OperationsManager manager = new OperationsManager(50, plant);
        PlantDirector director = new PlantDirector(30, manager, plant);
        manager.start();
        director.start();
        
        
    }
    
}
