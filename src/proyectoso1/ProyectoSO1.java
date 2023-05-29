package proyectoso1;

import Classes.PartsWarehouse;
import Classes.CarsPlant;
import Classes.CarsWarehouse;
import Classes.Employee;
import Classes.EmployeeTypes;
import Classes.StandardVehicle;
import Classes.AccessoryVehicle;
import Classes.OperationsManager;
import Classes.PlantDirector;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samer
 */
public class ProyectoSO1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StandardVehicle standard = new StandardVehicle(3, 4, 2, 3, 500000);
        AccessoryVehicle accessory = new AccessoryVehicle(3, 4, 2, 3, 2, 500000);
        CarsPlant plant = new CarsPlant(2000, 19, 10, 5, standard, accessory);
        OperationsManager manager = new OperationsManager(50, plant);
        PlantDirector director = new PlantDirector(30, manager, plant);
        manager.start();
        director.start();
        Employee emp1 = new Employee(10f, "chasis", 2f, plant);
        Employee emp2 = new Employee(10f, "chasis", 3f, plant);
        int dur = 500;
        try {

            sleep(dur);

        } catch (InterruptedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        emp1.start();
        emp2.start();
        try {

            sleep(8000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        emp1.stopRunning();
        emp2.stopRunning();
        System.out.println(plant.getCosts());
        System.out.println(plant.getPartsWarehouse().getChasisDone());
    }
    
}
