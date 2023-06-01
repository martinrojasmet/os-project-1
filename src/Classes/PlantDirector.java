package Classes;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samer
 */
public class PlantDirector extends Thread {
    private float salary;
    private float accSalary;
    private CarsPlant plant;
    private OperationsManager manager;
    private boolean checkingManager;
    private boolean keepGoing;
    
    public PlantDirector(float salary, OperationsManager manager, CarsPlant plant) {
        this.salary = salary;
        this.accSalary = 0;
        this.plant = plant;
        this.manager = manager;
        this.checkingManager = false;
        this.keepGoing = true;
    }
    
    @Override
    public void run() {
        long randomHour;
        long twentyFiveMinutes = this.getTwentyFiveMinutesInMs();
        while(this.keepGoing) {
            try {
                
                if (this.plant.getDaysToDeliver() == 0) {
                    deliverCars();                   
                } else {
                    randomHour = this.getRandomHourInMs();
                    sleep(randomHour);
                    this.setCheckingManager(true);
                    checkManager();
                    long remainingDay = (long) (this.plant.getDayDurationInMs() - (randomHour + twentyFiveMinutes));
                    sleep(remainingDay);
                }
                
                getPayment();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PlantDirector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void getPayment() {
        this.accSalary += this.salary * 24;
        this.plant.setCosts(this.plant.getCosts() + (this.salary * 24)); //Hay que revisar
    }
    
    public void checkManager() {
        long twentyFiveMinutes = getTwentyFiveMinutesInMs();
        if (checkingManager) {
            if (!this.manager.isIsWorking()) {
                try {
                    sleep(twentyFiveMinutes);
                    this.setCheckingManager(false);
                    this.manager.setAccSalary(this.manager.getAccSalary() - 50);
                    this.manager.setDiscountedSalary(this.manager.getDiscountedSalary() + 50);
                    this.manager.setFaults(this.manager.getFaults() + 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlantDirector.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {                   
                    sleep(twentyFiveMinutes);
                    this.setCheckingManager(false);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlantDirector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    public void deliverCars() {
        int standardIncome;
        int accessoryIncome;
        try {
            // Resetea contador
            this.plant.getCounterMutex().acquire();
            this.plant.setDaysToDeliver(this.plant.getDayCounter());
            this.plant.getCounterMutex().release();
            
            // Accede al almacen de carros y registra las ganancias
            this.plant.getCarsWarehouse().getSemaphore().acquire();
            sleep((long) this.plant.getDayDurationInMs());
            standardIncome = (int) (this.plant.getCarsWarehouse().getStandardCarsAvailable() * this.plant.getStandardVehicle().getPrice());
            accessoryIncome = (int) (this.plant.getCarsWarehouse().getAccessoryCarsAvailable() * this.plant.getAccessoryVehicle().getPrice());
            this.plant.setGrossIncome(this.plant.getGrossIncome() + standardIncome + accessoryIncome);
            this.plant.getCarsWarehouse().setStandardCarsAvailable(0);
            this.plant.getCarsWarehouse().setAccessoryCarsAvailable(0);
            this.plant.getCarsWarehouse().getSemaphore().release();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlantDirector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getRandomHourInMs() {
        long hourInMs = (long) (this.plant.getDayDurationInMs() / 24);
        Random random = new Random();
        long randomHour = random.nextInt(24);
        return randomHour * hourInMs;
    }
    
    public long getTwentyFiveMinutesInMs() {
        int dayInMinutes = 1440;
        long twentyFiveMinutes = (long) ((25 * this.plant.getDayDurationInMs())/dayInMinutes);
        return twentyFiveMinutes;
    }
    
    public void stopRunning() {
        this.keepGoing = false;
    }
    
    // Getters and setters

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getAccSalary() {
        return accSalary;
    }

    public void setAccSalary(float accSalary) {
        this.accSalary = accSalary;
    }

    public CarsPlant getPlant() {
        return plant;
    }

    public void setPlant(CarsPlant plant) {
        this.plant = plant;
    }

    public OperationsManager getManager() {
        return manager;
    }

    public void setManager(OperationsManager manager) {
        this.manager = manager;
    }

    public boolean isCheckingManager() {
        return checkingManager;
    }

    public void setCheckingManager(boolean checkingManager) {
        this.checkingManager = checkingManager;
    }
    
}
