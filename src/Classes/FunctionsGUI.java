package Classes;

import Interfaces.GUI;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author samer
 */
public class FunctionsGUI extends Thread {
    private CarsPlant plant;
    private GUI gui;

    public FunctionsGUI(CarsPlant plant, GUI gui) {
        this.plant = plant;
        this.gui = gui;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                sleep(20);
                updatePlantValues();
                updateDaysQty();
                updatePartsDone();
                updateCarsDone();
                updateManager();
                updateDirector();
            } catch (InterruptedException ex) {
                Logger.getLogger(FunctionsGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateDaysQty() {
        this.gui.getDaysLeft1().setText(Integer.toString(this.plant.getDaysToDeliver())); 
        this.gui.getTotalDaysQtty1().setText(Integer.toString(this.plant.getTotalDays())); 
    }
    
    public void updatePartsDone() {
        this.gui.getChasisQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getChasisDone()));
        this.gui.getMotorsQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getMotorsDone()));
        this.gui.getBodyworksQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getBodyworksDone()));
        this.gui.getWheelsQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getWheelsDone()));
        this.gui.getAccessoriesQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getAccessoriesDone()));
    }
    
    public void updateCarsDone() {
        this.gui.getStandardCarsQtty1().setText(Integer.toString(this.plant.getCarsWarehouse().getStandardCarsDone()));
        this.gui.getAccessoriesCarsQtty1().setText(Integer.toString(this.plant.getCarsWarehouse().getAccessoryCarsDone()));
    }
    
    public void updateManager() {
        this.gui.getOpManagerFaults1().setText(Integer.toString(this.plant.getManager().getFaults()));
        if (this.plant.getManager().isIsWorking()) {
            this.gui.getOpManagerJob1().setText("Trabajando");
        } else {
            this.gui.getOpManagerJob1().setText("Viendo carreras");
        }
    }
    
    public void updateDirector() {
        if (this.plant.getDirector().isCheckingManager()) {
            this.gui.getPlantDirectorJob1().setText("Revisando manager");
        } else {
            this.gui.getPlantDirectorJob1().setText("Trabajando");
        }
    }
    
    public void updatePlantValues() {
        this.gui.getOperativeCostsValue1().setText("$" + Integer.toString(((int) this.plant.getCosts())));
        this.gui.getGrossIncomeValue1().setText("$" + Integer.toString(this.plant.getGrossIncome()));
    }
}
