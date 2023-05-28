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
                    sleep((long) this.plant.getDayDurationInMs());
                } else {
                    randomHour = this.getRandomHourInMs();
                    sleep(randomHour);
                    
                    //Aqui se corre el checkManager() cada min de los 25min para (No se si al ponerlo en la funcion checkManager falle asiq ue lo deje aca)
                    this.setCheckingManager(true);
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < twentyFiveMinutes) {
                        checkManager();
                        sleep(twentyFiveMinutes/25);
                    }
                    this.setCheckingManager(false);
                    
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
        if (checkingManager) {
            if (!this.manager.isIsWorking()) {
                this.setCheckingManager(false);
                this.manager.setAccSalary(this.manager.getAccSalary() - 50);
                this.manager.setFaults(this.manager.getFaults() + 1);
            }
        }
    }
    
    public void deliverCars() {
        this.plant.setDaysToDeliver(this.plant.getDayCounter());
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
