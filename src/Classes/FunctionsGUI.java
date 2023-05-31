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
                this.plant.calculateNetIncome();
            } catch (InterruptedException ex) {
                Logger.getLogger(FunctionsGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateDaysQty() {
        if (getPlantName().equals("maserati")) {
            this.gui.getDaysLeft1().setText(Integer.toString(this.plant.getDaysToDeliver())); 
            this.gui.getTotalDaysQtty1().setText(Integer.toString(this.plant.getTotalDays())); 
        } else if (getPlantName().equals("bugatti")) {
            this.gui.getDaysLeft2().setText(Integer.toString(this.plant.getDaysToDeliver())); 
            this.gui.getTotalDaysQtty2().setText(Integer.toString(this.plant.getTotalDays()));
        }
        
    }
    
    public void updatePartsDone() {
        if (getPlantName().equals("maserati")) {
            this.gui.getChasisQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getChasisDone()));
            this.gui.getMotorsQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getMotorsDone()));
            this.gui.getBodyworksQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getBodyworksDone()));
            this.gui.getWheelsQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getWheelsDone()));
            this.gui.getAccessoriesQtty1().setText(Integer.toString(this.plant.getPartsWarehouse().getAccessoriesDone()));
        } else if (getPlantName().equals("bugatti")) {
            this.gui.getChasisQtty2().setText(Integer.toString(this.plant.getPartsWarehouse().getChasisDone()));
            this.gui.getMotorsQtty2().setText(Integer.toString(this.plant.getPartsWarehouse().getMotorsDone()));
            this.gui.getBodyworksQtty2().setText(Integer.toString(this.plant.getPartsWarehouse().getBodyworksDone()));
            this.gui.getWheelsQtty2().setText(Integer.toString(this.plant.getPartsWarehouse().getWheelsDone()));
            this.gui.getAccessoriesQtty2().setText(Integer.toString(this.plant.getPartsWarehouse().getAccessoriesDone()));
        }      
    }
    
    public void updateCarsDone() {
        if (getPlantName().equals("maserati")) {
            this.gui.getStandardCarsQtty1().setText(Integer.toString(this.plant.getCarsWarehouse().getStandardCarsDone()));
            this.gui.getAccessoriesCarsQtty1().setText(Integer.toString(this.plant.getCarsWarehouse().getAccessoryCarsDone()));
            this.gui.getStandardCarsAvailable1().setText(Integer.toString(this.plant.getCarsWarehouse().getStandardCarsAvailable()));
            this.gui.getAccessoryCarsAvailable1().setText(Integer.toString(this.plant.getCarsWarehouse().getAccessoryCarsAvailable()));
        } else if (getPlantName().equals("bugatti")) {
            this.gui.getStandardCarsQtty2().setText(Integer.toString(this.plant.getCarsWarehouse().getStandardCarsDone()));
            this.gui.getAccessoriesCarsQtty2().setText(Integer.toString(this.plant.getCarsWarehouse().getAccessoryCarsDone()));
            this.gui.getStandardCarsAvailable2().setText(Integer.toString(this.plant.getCarsWarehouse().getStandardCarsAvailable()));
            this.gui.getAccessoryCarsAvailable2().setText(Integer.toString(this.plant.getCarsWarehouse().getAccessoryCarsAvailable()));
        }
        
    }
    
    public void updateManager() {
        if (getPlantName().equals("maserati")) {
            this.gui.getOpManagerFaults1().setText(Integer.toString(this.plant.getManager().getFaults()));
            this.gui.getOpManagerMoneyTaken1().setText("$" + Integer.toString(this.plant.getManager().getDiscountedSalary()));
            if (this.plant.getManager().isIsWorking()) {
                this.gui.getOpManagerJob1().setText("Trabajando");
            } else {
                this.gui.getOpManagerJob1().setText("Viendo carreras");
            } 
        } else if (getPlantName().equals("bugatti")) {
            this.gui.getOpManagerFaults2().setText(Integer.toString(this.plant.getManager().getFaults()));
            this.gui.getOpManagerMoneyTaken2().setText("$" + Integer.toString(this.plant.getManager().getDiscountedSalary()));
            if (this.plant.getManager().isIsWorking()) {
                this.gui.getOpManagerJob2().setText("Trabajando");
            } else {
                this.gui.getOpManagerJob2().setText("Viendo carreras");
            }
        }
        
    }
    
    public void updateDirector() {
        if (getPlantName().equals("maserati")) {
            if (this.plant.getDirector().isCheckingManager()) {
                this.gui.getPlantDirectorJob1().setText("Revisando manager");
            } else {
                this.gui.getPlantDirectorJob1().setText("Trabajando");
            }
        } else if (getPlantName().equals("bugatti")) {
            if (this.plant.getDirector().isCheckingManager()) {
                this.gui.getPlantDirectorJob2().setText("Revisando manager");
            } else {
                this.gui.getPlantDirectorJob2().setText("Trabajando");
            }
        }
        
    }
    
    public void updatePlantValues() {
        if (getPlantName().equals("maserati")) {
            this.gui.getCostsMaseratiDashboardValue().setText("$" + Integer.toString((int) this.plant.getCosts()));
            this.gui.getGrossIncomeMaseratiDashboardValue().setText("$" + Integer.toString((int) this.plant.getGrossIncome()));
            this.gui.getNetIncomeMaseratiDashboardValue().setText("$" + Integer.toString((int) this.plant.getNetIncome()));
            this.gui.getOperativeCostsValue1().setText("$" + Integer.toString(((int) this.plant.getCosts())));
            this.gui.getGrossIncomeValue1().setText("$" + Integer.toString(this.plant.getGrossIncome()));
            this.gui.getNetIncomeValue1().setText("$" + Integer.toString(this.plant.getNetIncome()));
        } else if (getPlantName().equals("bugatti")) {
            this.gui.getCostsBugattiDashboardValue().setText("$" + Integer.toString((int) this.plant.getCosts()));
            this.gui.getGrossIncomeBugattiDashboardValue().setText("$" + Integer.toString((int) this.plant.getGrossIncome()));
            this.gui.getNetIncomeBugattiDashboardValue().setText("$" + Integer.toString((int) this.plant.getNetIncome()));
            this.gui.getOperativeCostsValue2().setText("$" + Integer.toString(((int) this.plant.getCosts())));
            this.gui.getGrossIncomeValue2().setText("$" + Integer.toString(this.plant.getGrossIncome()));
            this.gui.getNetIncomeValue2().setText("$" + Integer.toString(this.plant.getNetIncome()));
        }
        
    }
    
    public String getPlantName() {
        return this.plant.getDisplayName();
    }
}
