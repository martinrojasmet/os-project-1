package Interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Classes.AccessoryVehicle;
import Classes.CarsPlant;
import Classes.StandardVehicle;
import Classes.EmployeeInformation;
import Classes.FunctionsGUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author marti
 */
public class GUI extends javax.swing.JFrame {
    
    private CarsPlant maserati;
    private CarsPlant bugatti;
    private int dayDuration;
    private int dayCounter;
    private FunctionsGUI maseratiFunctions;
    private FunctionsGUI bugattiFunctions;
    /**
     * Creates new form GUI
     */
    public GUI() {
        this.dayDuration = 3000;
        this.dayCounter = 3;
        this.loadSetDaysJson();
        
        StandardVehicle standard = new StandardVehicle(3, 4, 2, 3, 500000);
        AccessoryVehicle accessory = new AccessoryVehicle(3, 4, 2, 3, 2, 600000);
        
        this.bugatti = new CarsPlant("bugatti", this.dayDuration, 17, this.dayCounter, 3, standard, accessory, this, true);
        this.maserati = new CarsPlant("maserati", this.dayDuration, 19, this.dayCounter, 5, standard, accessory, this, false);
        
        this.maseratiFunctions = new FunctionsGUI(this.maserati, this);
        this.bugattiFunctions = new FunctionsGUI(this.bugatti, this);
        
        initComponents();
        this.loadSetEmployeesJson();
        this.maseratiFunctions.start();
        this.bugattiFunctions.start();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                writeJson();
                e.getWindow().dispose();
                System.exit(0);
            }
        });
    }
    
    
    
    public void loadSetDaysJson() {
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader("src/Assets/Data.json")) {

            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            int dayDuration = ((Long) jsonObject.get("simulationDurationInSeconds")).intValue();
            int dayCounter = ((Long) jsonObject.get("vehicleDeliveryIntervalInDays")).intValue();
            
            this.dayDuration = dayDuration;
            this.dayCounter = dayCounter;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void loadSetEmployeesJson() {
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader("src/Assets/Data.json")) {

            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject initialEmployeeCount = (JSONObject) jsonObject.get("initialEmployeeCount1");
            int chasisInitial = ((Long) initialEmployeeCount.get("chasis")).intValue();
            int assemblerInitial = ((Long) initialEmployeeCount.get("assembler")).intValue();
            int bodyworkInitial = ((Long) initialEmployeeCount.get("bodywork")).intValue();
            int wheelsInitial = ((Long) initialEmployeeCount.get("wheels")).intValue();
            int accessoryInitial = ((Long) initialEmployeeCount.get("accessory")).intValue();
            int motorInitial = ((Long) initialEmployeeCount.get("motor")).intValue();
            
            JSONObject initialEmployeeCount2 = (JSONObject) jsonObject.get("initialEmployeeCount2");
            int chasisInitial2 = ((Long) initialEmployeeCount2.get("chasis")).intValue();
            int assemblerInitial2 = ((Long) initialEmployeeCount2.get("assembler")).intValue();
            int bodyworkInitial2 = ((Long) initialEmployeeCount2.get("bodywork")).intValue();
            int wheelsInitial2 = ((Long) initialEmployeeCount2.get("wheels")).intValue();
            int accessoryInitial2 = ((Long) initialEmployeeCount2.get("accessory")).intValue();
            int motorInitial2 = ((Long) initialEmployeeCount2.get("motor")).intValue();
            
            this.setValuesFromJson(chasisInitial, 
            assemblerInitial, bodyworkInitial, wheelsInitial, 
            accessoryInitial, motorInitial, chasisInitial2, 
            assemblerInitial2, bodyworkInitial2, wheelsInitial2, 
            accessoryInitial2, motorInitial2);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void writeJson() {
        //falta
    }
    
    public void setValuesFromJson(int chasisInitial, 
            int assemblerInitial, int bodyworkInitial, int wheelsInitial, 
            int accessoryInitial, int motorInitial, int chasisInitial2, 
            int assemblerInitial2, int bodyworkInitial2, int wheelsInitial2, 
            int accessoryInitial2, int motorInitial2) {
        
        this.getAccessoriesEmployeeQtty2().setText(String.valueOf(accessoryInitial2));
        this.getAccessoriesEmployeeQtty1().setText(String.valueOf(accessoryInitial));
        
        this.getBodyworksEmployeeQtty2().setText(String.valueOf(bodyworkInitial2));
        this.getBodyworksEmployeeQtty1().setText(String.valueOf(bodyworkInitial));
        
        this.getChasisEmployeeQtty2().setText(String.valueOf(chasisInitial2));
        this.getChasisEmployeeQtty1().setText(String.valueOf(chasisInitial));
        
        this.getMotorsEmployeeQtty2().setText(String.valueOf(motorInitial2));
        this.getMotorsEmployeeQtty1().setText(String.valueOf(motorInitial));
        
        this.getWheelsEmployeeQtty2().setText(String.valueOf(wheelsInitial2));
        this.getWheelsEmployeeQtty1().setText(String.valueOf(wheelsInitial));
        
        this.getAssemblerEmployeeQtty2().setText(String.valueOf(assemblerInitial2));
        this.getAssemblerEmployeeQtty1().setText(String.valueOf(assemblerInitial));
        
        int totalEmployeesRN = chasisInitial + assemblerInitial 
                + bodyworkInitial + wheelsInitial + accessoryInitial + motorInitial;
        int totalEmployeesRN2 = chasisInitial2 + assemblerInitial2
                + bodyworkInitial2 + wheelsInitial2 + accessoryInitial2 + motorInitial2;
        
        int employeeQtty2 = Integer.parseInt(EmployeeQtty2.getText());
        int employeeQtty1 = Integer.parseInt(EmployeeQtty1.getText());
        this.getEmployeeQtty2().setText(String.valueOf(employeeQtty2 - totalEmployeesRN2));
        this.getEmployeeQtty1().setText(String.valueOf(employeeQtty1 - totalEmployeesRN));
        
    }
    
    public CarsPlant getMaserati() {
        return maserati;
    }

    public void setMaserati(CarsPlant maserati) {
        this.maserati = maserati;
    }

    public CarsPlant getBugatti() {
        return bugatti;
    }

    public void setBugatti(CarsPlant bugatti) {
        this.bugatti = bugatti;
    }

    public JButton getRunSim1() {
        return RunSim1;
    }

    public void setRunSim1(JButton RunSim1) {
        this.RunSim1 = RunSim1;
    }

    public JButton getRunSim2() {
        return RunSim2;
    }

    public void setRunSim2(JButton RunSim2) {
        this.RunSim2 = RunSim2;
    }

    public JButton getStopSim1() {
        return StopSim1;
    }

    public void setStopSim1(JButton StopSim1) {
        this.StopSim1 = StopSim1;
    }

    public JButton getStopSim2() {
        return StopSim2;
    }

    public void setStopSim2(JButton StopSim2) {
        this.StopSim2 = StopSim2;
    }
    
    public JLabel getCostsBugattiDashboardValue() {
        return CostsBugattiDashboardValue;
    }

    public void setCostsBugattiDashboardValue(JLabel CostsBugattiDashboardValue) {
        this.CostsBugattiDashboardValue = CostsBugattiDashboardValue;
    }

    public JLabel getCostsMaseratiDashboardValue() {
        return CostsMaseratiDashboardValue;
    }

    public void setCostsMaseratiDashboardValue(JLabel CostsMaseratiDashboardValue) {
        this.CostsMaseratiDashboardValue = CostsMaseratiDashboardValue;
    }

    public JLabel getGrossIncomeBugattiDashboardValue() {
        return GrossIncomeBugattiDashboardValue;
    }

    public void setGrossIncomeBugattiDashboardValue(JLabel GrossIncomeBugattiDashboardValue) {
        this.GrossIncomeBugattiDashboardValue = GrossIncomeBugattiDashboardValue;
    }

    public JLabel getGrossIncomeMaseratiDashboardValue() {
        return GrossIncomeMaseratiDashboardValue;
    }

    public void setGrossIncomeMaseratiDashboardValue(JLabel GrossIncomeMaseratiDashboardValue) {
        this.GrossIncomeMaseratiDashboardValue = GrossIncomeMaseratiDashboardValue;
    }

    public JLabel getNetIncomeBugattiDashboardValue() {
        return NetIncomeBugattiDashboardValue;
    }

    public void setNetIncomeBugattiDashboardValue(JLabel NetIncomeBugattiDashboardValue) {
        this.NetIncomeBugattiDashboardValue = NetIncomeBugattiDashboardValue;
    }

    public JLabel getNetIncomeMaseratiDashboardValue() {
        return NetIncomeMaseratiDashboardValue;
    }

    public void setNetIncomeMaseratiDashboardValue(JLabel NetIncomeMaseratiDashboardValue) {
        this.NetIncomeMaseratiDashboardValue = NetIncomeMaseratiDashboardValue;
    }
    
    public JLabel getTotalDaysQtty1() {
        return TotalDaysQtty1;
    }

    public void setTotalDaysQtty1(JLabel TotalDaysQtty1) {
        this.TotalDaysQtty1 = TotalDaysQtty1;
    }

    public JLabel getOperativeCostsValue1() {
        return OperativeCostsValue1;
    }

    public void setOperativeCostsValue1(JLabel OperativeCostsValue1) {
        this.OperativeCostsValue1 = OperativeCostsValue1;
    }
    
    public JLabel getGrossIncomeValue1() {
        return GrossIncomeValue1;
    }

    public void setGrossIncomeValue1(JLabel GrossIncomeValue1) {
        this.GrossIncomeValue1 = GrossIncomeValue1;
    }

    public JLabel getNetIncomeValue1() {
        return NetIncomeValue1;
    }

    public void setNetIncomeValue1(JLabel NetIncomeValue1) {
        this.NetIncomeValue1 = NetIncomeValue1;
    }
    
    public JLabel getAccessoryCarsAvailable1() {
        return AccessoryCarsAvailable1;
    }

    public void setAccessoryCarsAvailable1(JLabel AccessoryCarsAvailable1) {
        this.AccessoryCarsAvailable1 = AccessoryCarsAvailable1;
    }

    public JLabel getStandardCarsAvailable1() {
        return StandardCarsAvailable1;
    }

    public void setStandardCarsAvailable1(JLabel StandardCarsAvailable1) {
        this.StandardCarsAvailable1 = StandardCarsAvailable1;
    }
    
    public JLabel getAccessoriesCarsQtty1() {
        return AccessoriesCarsQtty1;
    }

    public void setAccessoriesCarsQtty1(JLabel AccessoriesCarsQtty1) {
        this.AccessoriesCarsQtty1 = AccessoriesCarsQtty1;
    }

    public JLabel getAccessoriesCarsQttyTitle1() {
        return AccessoriesCarsQttyTitle1;
    }

    public void setAccessoriesCarsQttyTitle1(JLabel AccessoriesCarsQttyTitle1) {
        this.AccessoriesCarsQttyTitle1 = AccessoriesCarsQttyTitle1;
    }

    public JLabel getAccessoriesEmployeeQtty1() {
        return AccessoriesEmployeeQtty1;
    }

    public void setAccessoriesEmployeeQtty1(JLabel AccessoriesEmployeeQtty1) {
        this.AccessoriesEmployeeQtty1 = AccessoriesEmployeeQtty1;
    }

    public JButton getAccessoriesEmployeeQttyLess1() {
        return AccessoriesEmployeeQttyLess1;
    }

    public void setAccessoriesEmployeeQttyLess1(JButton AccessoriesEmployeeQttyLess1) {
        this.AccessoriesEmployeeQttyLess1 = AccessoriesEmployeeQttyLess1;
    }

    public JButton getAccessoriesEmployeeQttyPlus1() {
        return AccessoriesEmployeeQttyPlus1;
    }

    public void setAccessoriesEmployeeQttyPlus1(JButton AccessoriesEmployeeQttyPlus1) {
        this.AccessoriesEmployeeQttyPlus1 = AccessoriesEmployeeQttyPlus1;
    }

    public JLabel getAccessoriesEmployeeQttyTitle1() {
        return AccessoriesEmployeeQttyTitle1;
    }

    public void setAccessoriesEmployeeQttyTitle1(JLabel AccessoriesEmployeeQttyTitle1) {
        this.AccessoriesEmployeeQttyTitle1 = AccessoriesEmployeeQttyTitle1;
    }

    public JLabel getAccessoriesQtty1() {
        return AccessoriesQtty1;
    }

    public void setAccessoriesQtty1(JLabel AccessoriesQtty1) {
        this.AccessoriesQtty1 = AccessoriesQtty1;
    }

    public JLabel getAccessoriesQttyTitle1() {
        return AccessoriesQttyTitle1;
    }

    public void setAccessoriesQttyTitle1(JLabel AccessoriesQttyTitle1) {
        this.AccessoriesQttyTitle1 = AccessoriesQttyTitle1;
    }

    public JLabel getAssemblerEmployeeQtty1() {
        return AssemblerEmployeeQtty1;
    }

    public void setAssemblerEmployeeQtty1(JLabel AssemblerEmployeeQtty1) {
        this.AssemblerEmployeeQtty1 = AssemblerEmployeeQtty1;
    }

    public JLabel getAccessoriesCarsQtty2() {
        return AccessoriesCarsQtty2;
    }

    public void setAccessoriesCarsQtty2(JLabel AccessoriesCarsQtty2) {
        this.AccessoriesCarsQtty2 = AccessoriesCarsQtty2;
    }

    public JLabel getAccessoriesEmployeeQtty2() {
        return AccessoriesEmployeeQtty2;
    }

    public void setAccessoriesEmployeeQtty2(JLabel AccessoriesEmployeeQtty2) {
        this.AccessoriesEmployeeQtty2 = AccessoriesEmployeeQtty2;
    }

    public JButton getAccessoriesEmployeeQttyLess2() {
        return AccessoriesEmployeeQttyLess2;
    }

    public void setAccessoriesEmployeeQttyLess2(JButton AccessoriesEmployeeQttyLess2) {
        this.AccessoriesEmployeeQttyLess2 = AccessoriesEmployeeQttyLess2;
    }

    public JButton getAccessoriesEmployeeQttyPlus2() {
        return AccessoriesEmployeeQttyPlus2;
    }

    public void setAccessoriesEmployeeQttyPlus2(JButton AccessoriesEmployeeQttyPlus2) {
        this.AccessoriesEmployeeQttyPlus2 = AccessoriesEmployeeQttyPlus2;
    }

    public JLabel getAccessoriesQtty2() {
        return AccessoriesQtty2;
    }

    public void setAccessoriesQtty2(JLabel AccessoriesQtty2) {
        this.AccessoriesQtty2 = AccessoriesQtty2;
    }

    public JLabel getAccessoryCarsAvailable2() {
        return AccessoryCarsAvailable2;
    }

    public void setAccessoryCarsAvailable2(JLabel AccessoryCarsAvailable2) {
        this.AccessoryCarsAvailable2 = AccessoryCarsAvailable2;
    }

    public JLabel getAssemblerEmployeeQtty2() {
        return AssemblerEmployeeQtty2;
    }

    public void setAssemblerEmployeeQtty2(JLabel AssemblerEmployeeQtty2) {
        this.AssemblerEmployeeQtty2 = AssemblerEmployeeQtty2;
    }

    public JButton getAssemblerEmployeeQttyLess1() {
        return AssemblerEmployeeQttyLess1;
    }

    public void setAssemblerEmployeeQttyLess1(JButton AssemblerEmployeeQttyLess1) {
        this.AssemblerEmployeeQttyLess1 = AssemblerEmployeeQttyLess1;
    }

    public JButton getAssemblerEmployeeQttyLess2() {
        return AssemblerEmployeeQttyLess2;
    }

    public void setAssemblerEmployeeQttyLess2(JButton AssemblerEmployeeQttyLess2) {
        this.AssemblerEmployeeQttyLess2 = AssemblerEmployeeQttyLess2;
    }

    public JButton getAssemblerEmployeeQttyPlus1() {
        return AssemblerEmployeeQttyPlus1;
    }

    public void setAssemblerEmployeeQttyPlus1(JButton AssemblerEmployeeQttyPlus1) {
        this.AssemblerEmployeeQttyPlus1 = AssemblerEmployeeQttyPlus1;
    }

    public JButton getAssemblerEmployeeQttyPlus2() {
        return AssemblerEmployeeQttyPlus2;
    }

    public void setAssemblerEmployeeQttyPlus2(JButton AssemblerEmployeeQttyPlus2) {
        this.AssemblerEmployeeQttyPlus2 = AssemblerEmployeeQttyPlus2;
    }

    public JLabel getBodyworksEmployeeQtty1() {
        return BodyworksEmployeeQtty1;
    }

    public void setBodyworksEmployeeQtty1(JLabel BodyworksEmployeeQtty1) {
        this.BodyworksEmployeeQtty1 = BodyworksEmployeeQtty1;
    }

    public JLabel getBodyworksEmployeeQtty2() {
        return BodyworksEmployeeQtty2;
    }

    public void setBodyworksEmployeeQtty2(JLabel BodyworksEmployeeQtty2) {
        this.BodyworksEmployeeQtty2 = BodyworksEmployeeQtty2;
    }

    public JButton getBodyworksEmployeeQttyLess1() {
        return BodyworksEmployeeQttyLess1;
    }

    public void setBodyworksEmployeeQttyLess1(JButton BodyworksEmployeeQttyLess1) {
        this.BodyworksEmployeeQttyLess1 = BodyworksEmployeeQttyLess1;
    }

    public JButton getBodyworksEmployeeQttyLess2() {
        return BodyworksEmployeeQttyLess2;
    }

    public void setBodyworksEmployeeQttyLess2(JButton BodyworksEmployeeQttyLess2) {
        this.BodyworksEmployeeQttyLess2 = BodyworksEmployeeQttyLess2;
    }

    public JButton getBodyworksEmployeeQttyPlus1() {
        return BodyworksEmployeeQttyPlus1;
    }

    public void setBodyworksEmployeeQttyPlus1(JButton BodyworksEmployeeQttyPlus1) {
        this.BodyworksEmployeeQttyPlus1 = BodyworksEmployeeQttyPlus1;
    }

    public JButton getBodyworksEmployeeQttyPlus2() {
        return BodyworksEmployeeQttyPlus2;
    }

    public void setBodyworksEmployeeQttyPlus2(JButton BodyworksEmployeeQttyPlus2) {
        this.BodyworksEmployeeQttyPlus2 = BodyworksEmployeeQttyPlus2;
    }

    public JLabel getBodyworksQtty1() {
        return BodyworksQtty1;
    }

    public void setBodyworksQtty1(JLabel BodyworksQtty1) {
        this.BodyworksQtty1 = BodyworksQtty1;
    }

    public JLabel getBodyworksQtty2() {
        return BodyworksQtty2;
    }

    public void setBodyworksQtty2(JLabel BodyworksQtty2) {
        this.BodyworksQtty2 = BodyworksQtty2;
    }

    public JLabel getChasisEmployeeQtty1() {
        return ChasisEmployeeQtty1;
    }

    public void setChasisEmployeeQtty1(JLabel ChasisEmployeeQtty1) {
        this.ChasisEmployeeQtty1 = ChasisEmployeeQtty1;
    }

    public JLabel getChasisEmployeeQtty2() {
        return ChasisEmployeeQtty2;
    }

    public void setChasisEmployeeQtty2(JLabel ChasisEmployeeQtty2) {
        this.ChasisEmployeeQtty2 = ChasisEmployeeQtty2;
    }

    public JButton getChasisEmployeeQttyLess1() {
        return ChasisEmployeeQttyLess1;
    }

    public void setChasisEmployeeQttyLess1(JButton ChasisEmployeeQttyLess1) {
        this.ChasisEmployeeQttyLess1 = ChasisEmployeeQttyLess1;
    }

    public JButton getChasisEmployeeQttyLess2() {
        return ChasisEmployeeQttyLess2;
    }

    public void setChasisEmployeeQttyLess2(JButton ChasisEmployeeQttyLess2) {
        this.ChasisEmployeeQttyLess2 = ChasisEmployeeQttyLess2;
    }

    public JButton getChasisEmployeeQttyPlus1() {
        return ChasisEmployeeQttyPlus1;
    }

    public void setChasisEmployeeQttyPlus1(JButton ChasisEmployeeQttyPlus1) {
        this.ChasisEmployeeQttyPlus1 = ChasisEmployeeQttyPlus1;
    }

    public JButton getChasisEmployeeQttyPlus2() {
        return ChasisEmployeeQttyPlus2;
    }

    public void setChasisEmployeeQttyPlus2(JButton ChasisEmployeeQttyPlus2) {
        this.ChasisEmployeeQttyPlus2 = ChasisEmployeeQttyPlus2;
    }

    public JLabel getChasisQtty1() {
        return ChasisQtty1;
    }

    public void setChasisQtty1(JLabel ChasisQtty1) {
        this.ChasisQtty1 = ChasisQtty1;
    }

    public JLabel getChasisQtty2() {
        return ChasisQtty2;
    }

    public void setChasisQtty2(JLabel ChasisQtty2) {
        this.ChasisQtty2 = ChasisQtty2;
    }

    public JLabel getDaysLeft1() {
        return DaysLeft1;
    }

    public void setDaysLeft1(JLabel DaysLeft1) {
        this.DaysLeft1 = DaysLeft1;
    }

    public JLabel getDaysLeft2() {
        return DaysLeft2;
    }

    public void setDaysLeft2(JLabel DaysLeft2) {
        this.DaysLeft2 = DaysLeft2;
    }

    public JLabel getEmployeeQtty1() {
        return EmployeeQtty1;
    }

    public void setEmployeeQtty1(JLabel EmployeeQtty1) {
        this.EmployeeQtty1 = EmployeeQtty1;
    }

    public JLabel getEmployeeQtty2() {
        return EmployeeQtty2;
    }

    public void setEmployeeQtty2(JLabel EmployeeQtty2) {
        this.EmployeeQtty2 = EmployeeQtty2;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    public JLabel getGrossIncomeValue2() {
        return GrossIncomeValue2;
    }

    public void setGrossIncomeValue2(JLabel GrossIncomeValue2) {
        this.GrossIncomeValue2 = GrossIncomeValue2;
    }

    public JLabel getMaxAccessoriesQtty1() {
        return MaxAccessoriesQtty1;
    }

    public void setMaxAccessoriesQtty1(JLabel MaxAccessoriesQtty1) {
        this.MaxAccessoriesQtty1 = MaxAccessoriesQtty1;
    }

    public JLabel getMaxAccessoriesQtty2() {
        return MaxAccessoriesQtty2;
    }

    public void setMaxAccessoriesQtty2(JLabel MaxAccessoriesQtty2) {
        this.MaxAccessoriesQtty2 = MaxAccessoriesQtty2;
    }

    public JLabel getMaxBodyworksQtty1() {
        return MaxBodyworksQtty1;
    }

    public void setMaxBodyworksQtty1(JLabel MaxBodyworksQtty1) {
        this.MaxBodyworksQtty1 = MaxBodyworksQtty1;
    }

    public JLabel getMaxBodyworksQtty2() {
        return MaxBodyworksQtty2;
    }

    public void setMaxBodyworksQtty2(JLabel MaxBodyworksQtty2) {
        this.MaxBodyworksQtty2 = MaxBodyworksQtty2;
    }

    public JLabel getMaxChasisQtty1() {
        return MaxChasisQtty1;
    }

    public void setMaxChasisQtty1(JLabel MaxChasisQtty1) {
        this.MaxChasisQtty1 = MaxChasisQtty1;
    }

    public JLabel getMaxChasisQtty2() {
        return MaxChasisQtty2;
    }

    public void setMaxChasisQtty2(JLabel MaxChasisQtty2) {
        this.MaxChasisQtty2 = MaxChasisQtty2;
    }

    public JLabel getMaxMotorsQtty1() {
        return MaxMotorsQtty1;
    }

    public void setMaxMotorsQtty1(JLabel MaxMotorsQtty1) {
        this.MaxMotorsQtty1 = MaxMotorsQtty1;
    }

    public JLabel getMaxMotorsQtty2() {
        return MaxMotorsQtty2;
    }

    public void setMaxMotorsQtty2(JLabel MaxMotorsQtty2) {
        this.MaxMotorsQtty2 = MaxMotorsQtty2;
    }

    public JLabel getMaxWheelsQtty1() {
        return MaxWheelsQtty1;
    }

    public void setMaxWheelsQtty1(JLabel MaxWheelsQtty1) {
        this.MaxWheelsQtty1 = MaxWheelsQtty1;
    }

    public JLabel getMaxWheelsQtty2() {
        return MaxWheelsQtty2;
    }

    public void setMaxWheelsQtty2(JLabel MaxWheelsQtty2) {
        this.MaxWheelsQtty2 = MaxWheelsQtty2;
    }

    public JLabel getMotorsEmployeeQtty1() {
        return MotorsEmployeeQtty1;
    }

    public void setMotorsEmployeeQtty1(JLabel MotorsEmployeeQtty1) {
        this.MotorsEmployeeQtty1 = MotorsEmployeeQtty1;
    }

    public JLabel getMotorsEmployeeQtty2() {
        return MotorsEmployeeQtty2;
    }

    public void setMotorsEmployeeQtty2(JLabel MotorsEmployeeQtty2) {
        this.MotorsEmployeeQtty2 = MotorsEmployeeQtty2;
    }

    public JButton getMotorsEmployeeQttyLess1() {
        return MotorsEmployeeQttyLess1;
    }

    public void setMotorsEmployeeQttyLess1(JButton MotorsEmployeeQttyLess1) {
        this.MotorsEmployeeQttyLess1 = MotorsEmployeeQttyLess1;
    }

    public JButton getMotorsEmployeeQttyLess2() {
        return MotorsEmployeeQttyLess2;
    }

    public void setMotorsEmployeeQttyLess2(JButton MotorsEmployeeQttyLess2) {
        this.MotorsEmployeeQttyLess2 = MotorsEmployeeQttyLess2;
    }

    public JButton getMotorsEmployeeQttyPlus1() {
        return MotorsEmployeeQttyPlus1;
    }

    public void setMotorsEmployeeQttyPlus1(JButton MotorsEmployeeQttyPlus1) {
        this.MotorsEmployeeQttyPlus1 = MotorsEmployeeQttyPlus1;
    }

    public JButton getMotorsEmployeeQttyPlus2() {
        return MotorsEmployeeQttyPlus2;
    }

    public void setMotorsEmployeeQttyPlus2(JButton MotorsEmployeeQttyPlus2) {
        this.MotorsEmployeeQttyPlus2 = MotorsEmployeeQttyPlus2;
    }

    public JLabel getMotorsQtty1() {
        return MotorsQtty1;
    }

    public void setMotorsQtty1(JLabel MotorsQtty1) {
        this.MotorsQtty1 = MotorsQtty1;
    }

    public JLabel getMotorsQtty2() {
        return MotorsQtty2;
    }

    public void setMotorsQtty2(JLabel MotorsQtty2) {
        this.MotorsQtty2 = MotorsQtty2;
    }

    public JLabel getNetIncomeValue2() {
        return NetIncomeValue2;
    }

    public void setNetIncomeValue2(JLabel NetIncomeValue2) {
        this.NetIncomeValue2 = NetIncomeValue2;
    }

    public JLabel getOpManagerFaults1() {
        return OpManagerFaults1;
    }

    public void setOpManagerFaults1(JLabel OpManagerFaults1) {
        this.OpManagerFaults1 = OpManagerFaults1;
    }

    public JLabel getOpManagerFaults2() {
        return OpManagerFaults2;
    }

    public void setOpManagerFaults2(JLabel OpManagerFaults2) {
        this.OpManagerFaults2 = OpManagerFaults2;
    }

    public JLabel getOpManagerJob1() {
        return OpManagerJob1;
    }

    public void setOpManagerJob1(JLabel OpManagerJob1) {
        this.OpManagerJob1 = OpManagerJob1;
    }

    public JLabel getOpManagerJob2() {
        return OpManagerJob2;
    }

    public void setOpManagerJob2(JLabel OpManagerJob2) {
        this.OpManagerJob2 = OpManagerJob2;
    }

    public JLabel getOpManagerMoneyTaken1() {
        return OpManagerMoneyTaken1;
    }

    public void setOpManagerMoneyTaken1(JLabel OpManagerMoneyTaken1) {
        this.OpManagerMoneyTaken1 = OpManagerMoneyTaken1;
    }

    public JLabel getOpManagerMoneyTaken2() {
        return OpManagerMoneyTaken2;
    }

    public void setOpManagerMoneyTaken2(JLabel OpManagerMoneyTaken2) {
        this.OpManagerMoneyTaken2 = OpManagerMoneyTaken2;
    }

    public JLabel getOperativeCosts1() {
        return OperativeCosts1;
    }

    public void setOperativeCosts1(JLabel OperativeCosts1) {
        this.OperativeCosts1 = OperativeCosts1;
    }

    public JLabel getOperativeCosts2() {
        return OperativeCosts2;
    }

    public void setOperativeCosts2(JLabel OperativeCosts2) {
        this.OperativeCosts2 = OperativeCosts2;
    }

    public JLabel getOperativeCostsValue2() {
        return OperativeCostsValue2;
    }

    public void setOperativeCostsValue2(JLabel OperativeCostsValue2) {
        this.OperativeCostsValue2 = OperativeCostsValue2;
    }

    public JLabel getPlantDirectorJob1() {
        return PlantDirectorJob1;
    }

    public void setPlantDirectorJob1(JLabel PlantDirectorJob1) {
        this.PlantDirectorJob1 = PlantDirectorJob1;
    }

    public JLabel getPlantDirectorJob2() {
        return PlantDirectorJob2;
    }

    public void setPlantDirectorJob2(JLabel PlantDirectorJob2) {
        this.PlantDirectorJob2 = PlantDirectorJob2;
    }

    public JLabel getStandardCarsAvailable2() {
        return StandardCarsAvailable2;
    }

    public void setStandardCarsAvailable2(JLabel StandardCarsAvailable2) {
        this.StandardCarsAvailable2 = StandardCarsAvailable2;
    }

    public JLabel getStandardCarsQtty1() {
        return StandardCarsQtty1;
    }

    public void setStandardCarsQtty1(JLabel StandardCarsQtty1) {
        this.StandardCarsQtty1 = StandardCarsQtty1;
    }

    public JLabel getStandardCarsQtty2() {
        return StandardCarsQtty2;
    }

    public void setStandardCarsQtty2(JLabel StandardCarsQtty2) {
        this.StandardCarsQtty2 = StandardCarsQtty2;
    }

    public JLabel getTotalDays1() {
        return TotalDays1;
    }

    public void setTotalDays1(JLabel TotalDays1) {
        this.TotalDays1 = TotalDays1;
    }

    public JLabel getTotalDays2() {
        return TotalDays2;
    }

    public void setTotalDays2(JLabel TotalDays2) {
        this.TotalDays2 = TotalDays2;
    }

    public JLabel getTotalDaysQtty2() {
        return TotalDaysQtty2;
    }

    public void setTotalDaysQtty2(JLabel TotalDaysQtty2) {
        this.TotalDaysQtty2 = TotalDaysQtty2;
    }

    public JLabel getWheelsEmployeeQtty1() {
        return WheelsEmployeeQtty1;
    }

    public void setWheelsEmployeeQtty1(JLabel WheelsEmployeeQtty1) {
        this.WheelsEmployeeQtty1 = WheelsEmployeeQtty1;
    }

    public JLabel getWheelsEmployeeQtty2() {
        return WheelsEmployeeQtty2;
    }

    public void setWheelsEmployeeQtty2(JLabel WheelsEmployeeQtty2) {
        this.WheelsEmployeeQtty2 = WheelsEmployeeQtty2;
    }

    public JButton getWheelsEmployeeQttyLess1() {
        return WheelsEmployeeQttyLess1;
    }

    public void setWheelsEmployeeQttyLess1(JButton WheelsEmployeeQttyLess1) {
        this.WheelsEmployeeQttyLess1 = WheelsEmployeeQttyLess1;
    }

    public JButton getWheelsEmployeeQttyLess2() {
        return WheelsEmployeeQttyLess2;
    }

    public void setWheelsEmployeeQttyLess2(JButton WheelsEmployeeQttyLess2) {
        this.WheelsEmployeeQttyLess2 = WheelsEmployeeQttyLess2;
    }

    public JButton getWheelsEmployeeQttyPlus1() {
        return WheelsEmployeeQttyPlus1;
    }

    public void setWheelsEmployeeQttyPlus1(JButton WheelsEmployeeQttyPlus1) {
        this.WheelsEmployeeQttyPlus1 = WheelsEmployeeQttyPlus1;
    }

    public JButton getWheelsEmployeeQttyPlus2() {
        return WheelsEmployeeQttyPlus2;
    }

    public void setWheelsEmployeeQttyPlus2(JButton WheelsEmployeeQttyPlus2) {
        this.WheelsEmployeeQttyPlus2 = WheelsEmployeeQttyPlus2;
    }

    public JLabel getWheelsQtty1() {
        return WheelsQtty1;
    }

    public void setWheelsQtty1(JLabel WheelsQtty1) {
        this.WheelsQtty1 = WheelsQtty1;
    }

    public JLabel getWheelsQtty2() {
        return WheelsQtty2;
    }

    public void setWheelsQtty2(JLabel WheelsQtty2) {
        this.WheelsQtty2 = WheelsQtty2;
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        TitleProject = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        DaysMenu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TabbedPane = new javax.swing.JTabbedPane();
        Dashboard = new javax.swing.JPanel();
        MaseratiTitle = new javax.swing.JLabel();
        BugattiTitleDashboard = new javax.swing.JLabel();
        NetIncomeMaseratiDashboardTittle = new javax.swing.JLabel();
        CostsMaseratiDashboardTittle = new javax.swing.JLabel();
        GrossIncomeMaseratiDashboardTittle = new javax.swing.JLabel();
        CostsBugattiDashboardTittle = new javax.swing.JLabel();
        GrossIncomeBugattiDashboardTittle = new javax.swing.JLabel();
        NetIncomeBugattiDashboardTittle = new javax.swing.JLabel();
        NetIncomeBugattiDashboardValue = new javax.swing.JLabel();
        NetIncomeMaseratiDashboardValue = new javax.swing.JLabel();
        GrossIncomeBugattiDashboardValue = new javax.swing.JLabel();
        CostsBugattiDashboardValue = new javax.swing.JLabel();
        CostsMaseratiDashboardValue = new javax.swing.JLabel();
        GrossIncomeMaseratiDashboardValue = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Bugatti = new javax.swing.JPanel();
        ChasisEmployeeQtty2 = new javax.swing.JLabel();
        AssemblerEmployeeQttyTitle2 = new javax.swing.JLabel();
        EmployeeQtty2 = new javax.swing.JLabel();
        ChasisEmployeeQttyLess2 = new javax.swing.JButton();
        ChasisEmployeeQttyPlus2 = new javax.swing.JButton();
        BodyworksEmployeeQtty2 = new javax.swing.JLabel();
        BodyworksEmployeeQttyLess2 = new javax.swing.JButton();
        BodyworksEmployeeQttyPlus2 = new javax.swing.JButton();
        MotorsEmployeeQtty2 = new javax.swing.JLabel();
        MotorsEmployeeQttyLess2 = new javax.swing.JButton();
        MotorsEmployeeQttyPlus2 = new javax.swing.JButton();
        WheelsEmployeeQtty2 = new javax.swing.JLabel();
        WheelsEmployeeQttyLess2 = new javax.swing.JButton();
        WheelsEmployeeQttyPlus2 = new javax.swing.JButton();
        AccessoriesEmployeeQtty2 = new javax.swing.JLabel();
        AccessoriesEmployeeQttyLess2 = new javax.swing.JButton();
        AccessoriesEmployeeQttyPlus2 = new javax.swing.JButton();
        AssemblerEmployeeQtty2 = new javax.swing.JLabel();
        AssemblerEmployeeQttyLess2 = new javax.swing.JButton();
        AssemblerEmployeeQttyPlus2 = new javax.swing.JButton();
        EmployeeQttyTitle2 = new javax.swing.JLabel();
        BodyworksEmployeeQttyTitle2 = new javax.swing.JLabel();
        MotorsEmployeeQttyTitle2 = new javax.swing.JLabel();
        WheelsEmployeeQttyTitle2 = new javax.swing.JLabel();
        AccessoriesEmployeeQttyTitle2 = new javax.swing.JLabel();
        PlantDirectorJob2 = new javax.swing.JLabel();
        ChasisQtty2 = new javax.swing.JLabel();
        AccessoryCarsAvailableTitle2 = new javax.swing.JLabel();
        BodyworksQtty2 = new javax.swing.JLabel();
        MotorsQtty2 = new javax.swing.JLabel();
        WheelsQtty2 = new javax.swing.JLabel();
        AccessoriesQtty2 = new javax.swing.JLabel();
        AccessoryCarsAvailable2 = new javax.swing.JLabel();
        BodyworksQttyTitle2 = new javax.swing.JLabel();
        MotorsQttyTitles2 = new javax.swing.JLabel();
        WheelsQttyTitle2 = new javax.swing.JLabel();
        AccessoriesQttyTitle2 = new javax.swing.JLabel();
        MaxAccessoriesQttyTitle2 = new javax.swing.JLabel();
        AccessoriesCarsQttyTitle2 = new javax.swing.JLabel();
        AccessoriesCarsQtty2 = new javax.swing.JLabel();
        ChasisQttyTitle2 = new javax.swing.JLabel();
        MaxChasisQttyTitle2 = new javax.swing.JLabel();
        MaxBodyworksQttyTitle2 = new javax.swing.JLabel();
        MaxMotorsQttyTitles2 = new javax.swing.JLabel();
        MaxWheelsQttyTitle2 = new javax.swing.JLabel();
        MaxChasisQtty2 = new javax.swing.JLabel();
        MaxBodyworksQtty2 = new javax.swing.JLabel();
        MaxMotorsQtty2 = new javax.swing.JLabel();
        MaxWheelsQtty2 = new javax.swing.JLabel();
        MaxAccessoriesQtty2 = new javax.swing.JLabel();
        DaysLeft2 = new javax.swing.JLabel();
        PlantTitle2 = new javax.swing.JLabel();
        DaysLeftTitle2 = new javax.swing.JLabel();
        OpManagerTitle2 = new javax.swing.JLabel();
        EmployeeTitle2 = new javax.swing.JLabel();
        PlantDirectorTitle2 = new javax.swing.JLabel();
        ChasisEmployeeQttyTitle2 = new javax.swing.JLabel();
        PlantDirectorJobTitle2 = new javax.swing.JLabel();
        OpManagerJob2 = new javax.swing.JLabel();
        OpManagerJobTitle2 = new javax.swing.JLabel();
        OpManagerFaults2 = new javax.swing.JLabel();
        OpManagerFaultsTitle2 = new javax.swing.JLabel();
        OpManagerMoneyTaken2 = new javax.swing.JLabel();
        OpManagerMoneyTakenTitle2 = new javax.swing.JLabel();
        WarehouseTitle3 = new javax.swing.JLabel();
        RunSim2 = new javax.swing.JButton();
        StopSim2 = new javax.swing.JButton();
        TotalDays2 = new javax.swing.JLabel();
        TotalDaysQtty2 = new javax.swing.JLabel();
        NetIncomeValue2 = new javax.swing.JLabel();
        OperativeCosts2 = new javax.swing.JLabel();
        NetIncomeTittle2 = new javax.swing.JLabel();
        OperativeCostsValue2 = new javax.swing.JLabel();
        StandardCarsQttyTitle2 = new javax.swing.JLabel();
        StandardCarsAvailableTitle2 = new javax.swing.JLabel();
        StandardCarsQtty2 = new javax.swing.JLabel();
        StandardCarsAvailable2 = new javax.swing.JLabel();
        GrossIncomeTittle2 = new javax.swing.JLabel();
        GrossIncomeValue2 = new javax.swing.JLabel();
        Maserati = new javax.swing.JPanel();
        ChasisEmployeeQtty1 = new javax.swing.JLabel();
        AssemblerEmployeeQttyTitle1 = new javax.swing.JLabel();
        EmployeeQtty1 = new javax.swing.JLabel();
        ChasisEmployeeQttyLess1 = new javax.swing.JButton();
        ChasisEmployeeQttyPlus1 = new javax.swing.JButton();
        BodyworksEmployeeQtty1 = new javax.swing.JLabel();
        BodyworksEmployeeQttyLess1 = new javax.swing.JButton();
        BodyworksEmployeeQttyPlus1 = new javax.swing.JButton();
        MotorsEmployeeQtty1 = new javax.swing.JLabel();
        MotorsEmployeeQttyLess1 = new javax.swing.JButton();
        MotorsEmployeeQttyPlus1 = new javax.swing.JButton();
        WheelsEmployeeQtty1 = new javax.swing.JLabel();
        WheelsEmployeeQttyLess1 = new javax.swing.JButton();
        WheelsEmployeeQttyPlus1 = new javax.swing.JButton();
        AccessoriesEmployeeQtty1 = new javax.swing.JLabel();
        AccessoriesEmployeeQttyLess1 = new javax.swing.JButton();
        AccessoriesEmployeeQttyPlus1 = new javax.swing.JButton();
        AssemblerEmployeeQtty1 = new javax.swing.JLabel();
        AssemblerEmployeeQttyLess1 = new javax.swing.JButton();
        AssemblerEmployeeQttyPlus1 = new javax.swing.JButton();
        EmployeeQttyTitle1 = new javax.swing.JLabel();
        BodyworksEmployeeQttyTitle1 = new javax.swing.JLabel();
        MotorsEmployeeQttyTitle1 = new javax.swing.JLabel();
        WheelsEmployeeQttyTitle1 = new javax.swing.JLabel();
        AccessoriesEmployeeQttyTitle1 = new javax.swing.JLabel();
        PlantDirectorJob1 = new javax.swing.JLabel();
        ChasisQtty1 = new javax.swing.JLabel();
        AccessoryCarsAvailableTitle1 = new javax.swing.JLabel();
        BodyworksQtty1 = new javax.swing.JLabel();
        MotorsQtty1 = new javax.swing.JLabel();
        WheelsQtty1 = new javax.swing.JLabel();
        AccessoriesQtty1 = new javax.swing.JLabel();
        AccessoryCarsAvailable1 = new javax.swing.JLabel();
        BodyworksQttyTitle1 = new javax.swing.JLabel();
        MotorsQttyTitles1 = new javax.swing.JLabel();
        WheelsQttyTitle1 = new javax.swing.JLabel();
        AccessoriesQttyTitle1 = new javax.swing.JLabel();
        MaxAccessoriesQttyTitle1 = new javax.swing.JLabel();
        AccessoriesCarsQttyTitle1 = new javax.swing.JLabel();
        AccessoriesCarsQtty1 = new javax.swing.JLabel();
        ChasisQttyTitle1 = new javax.swing.JLabel();
        MaxChasisQttyTitle1 = new javax.swing.JLabel();
        MaxBodyworksQttyTitle1 = new javax.swing.JLabel();
        MaxMotorsQttyTitles1 = new javax.swing.JLabel();
        MaxWheelsQttyTitle1 = new javax.swing.JLabel();
        MaxChasisQtty1 = new javax.swing.JLabel();
        MaxBodyworksQtty1 = new javax.swing.JLabel();
        MaxMotorsQtty1 = new javax.swing.JLabel();
        MaxWheelsQtty1 = new javax.swing.JLabel();
        MaxAccessoriesQtty1 = new javax.swing.JLabel();
        DaysLeft1 = new javax.swing.JLabel();
        PlantTitle1 = new javax.swing.JLabel();
        DaysLeftTitle1 = new javax.swing.JLabel();
        OpManagerTitle1 = new javax.swing.JLabel();
        EmployeeTitle1 = new javax.swing.JLabel();
        PlantDirectorTitle1 = new javax.swing.JLabel();
        ChasisEmployeeQttyTitle1 = new javax.swing.JLabel();
        PlantDirectorJobTitle1 = new javax.swing.JLabel();
        OpManagerJob1 = new javax.swing.JLabel();
        OpManagerJobTitle1 = new javax.swing.JLabel();
        OpManagerFaults1 = new javax.swing.JLabel();
        OpManagerFaultsTitle1 = new javax.swing.JLabel();
        OpManagerMoneyTaken1 = new javax.swing.JLabel();
        OpManagerMoneyTakenTitle1 = new javax.swing.JLabel();
        WarehouseTitle2 = new javax.swing.JLabel();
        RunSim1 = new javax.swing.JButton();
        StopSim1 = new javax.swing.JButton();
        TotalDays1 = new javax.swing.JLabel();
        TotalDaysQtty1 = new javax.swing.JLabel();
        NetIncomeValue1 = new javax.swing.JLabel();
        OperativeCosts1 = new javax.swing.JLabel();
        NetIncomeTittle1 = new javax.swing.JLabel();
        OperativeCostsValue1 = new javax.swing.JLabel();
        StandardCarsQttyTitle1 = new javax.swing.JLabel();
        StandardCarsAvailableTitle1 = new javax.swing.JLabel();
        StandardCarsQtty1 = new javax.swing.JLabel();
        StandardCarsAvailable1 = new javax.swing.JLabel();
        GrossIncomeTittle1 = new javax.swing.JLabel();
        GrossIncomeValue1 = new javax.swing.JLabel();
        Days = new javax.swing.JPanel();
        SecondsForDay = new javax.swing.JLabel();
        LessSecondsForDay = new javax.swing.JButton();
        PlusSecondsForDay = new javax.swing.JButton();
        DaysForDelivery = new javax.swing.JLabel();
        DaysForDeliveryLess = new javax.swing.JButton();
        DaysForDeliveryPlus = new javax.swing.JButton();
        DaysTitle1 = new javax.swing.JLabel();
        ChasisEmployeeQttyTitle4 = new javax.swing.JLabel();
        DaysForDeliveryTitle1 = new javax.swing.JLabel();
        imageCarDays = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleProject.setBackground(new java.awt.Color(51, 51, 51));
        TitleProject.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Proyecto 1 Sistemas Operativos");
        TitleProject.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 280, 40));

        Panel.add(TitleProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, -1));

        MenuBar.setBackground(new java.awt.Color(153, 153, 153));
        MenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dashboard");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuBar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Planta Bugatti");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuBar.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 110, -1));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Planta Maserati");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuBar.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        DaysMenu.setBackground(new java.awt.Color(204, 204, 204));
        DaysMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DaysMenuMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Días");

        javax.swing.GroupLayout DaysMenuLayout = new javax.swing.GroupLayout(DaysMenu);
        DaysMenu.setLayout(DaysMenuLayout);
        DaysMenuLayout.setHorizontalGroup(
            DaysMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        DaysMenuLayout.setVerticalGroup(
            DaysMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DaysMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuBar.add(DaysMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        Panel.add(MenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 540));

        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MaseratiTitle.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        MaseratiTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaseratiTitle.setText("Maserati");
        Dashboard.add(MaseratiTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 280, 90));

        BugattiTitleDashboard.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        BugattiTitleDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BugattiTitleDashboard.setText("Bugatti");
        Dashboard.add(BugattiTitleDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 280, 90));

        NetIncomeMaseratiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        NetIncomeMaseratiDashboardTittle.setText("Ganancias netas");
        Dashboard.add(NetIncomeMaseratiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 410, -1, -1));

        CostsMaseratiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        CostsMaseratiDashboardTittle.setText("Costos operativos");
        Dashboard.add(CostsMaseratiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

        GrossIncomeMaseratiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        GrossIncomeMaseratiDashboardTittle.setText("Ganancias brutas");
        Dashboard.add(GrossIncomeMaseratiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, -1, -1));

        CostsBugattiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        CostsBugattiDashboardTittle.setText("Costos operativos");
        Dashboard.add(CostsBugattiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        GrossIncomeBugattiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        GrossIncomeBugattiDashboardTittle.setText("Ganancias brutas");
        Dashboard.add(GrossIncomeBugattiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        NetIncomeBugattiDashboardTittle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        NetIncomeBugattiDashboardTittle.setText("Ganancias netas");
        Dashboard.add(NetIncomeBugattiDashboardTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        NetIncomeBugattiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NetIncomeBugattiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NetIncomeBugattiDashboardValue.setText("0");
        Dashboard.add(NetIncomeBugattiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 180, -1));

        NetIncomeMaseratiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NetIncomeMaseratiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NetIncomeMaseratiDashboardValue.setText("0");
        Dashboard.add(NetIncomeMaseratiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, 180, -1));

        GrossIncomeBugattiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        GrossIncomeBugattiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GrossIncomeBugattiDashboardValue.setText("0");
        Dashboard.add(GrossIncomeBugattiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 180, -1));

        CostsBugattiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CostsBugattiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CostsBugattiDashboardValue.setText("0");
        Dashboard.add(CostsBugattiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 180, -1));

        CostsMaseratiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CostsMaseratiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CostsMaseratiDashboardValue.setText("0");
        Dashboard.add(CostsMaseratiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 220, -1));

        GrossIncomeMaseratiDashboardValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        GrossIncomeMaseratiDashboardValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GrossIncomeMaseratiDashboardValue.setText("0");
        Dashboard.add(GrossIncomeMaseratiDashboardValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, 200, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Samer Ghattas");
        Dashboard.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Martin Rojas");
        Dashboard.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        TabbedPane.addTab("tab1", Dashboard);

        Bugatti.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChasisEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQtty2.setText("1");
        Bugatti.add(ChasisEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 221, 50, 30));

        AssemblerEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQttyTitle2.setText("Empleados en ens.");
        Bugatti.add(AssemblerEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 471, 160, 30));

        EmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeQtty2.setText(String.valueOf(this.bugatti.getMaxEmployees()));
        Bugatti.add(EmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 50, 30));

        ChasisEmployeeQttyLess2.setText("-");
        ChasisEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(ChasisEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 221, 50, 30));

        ChasisEmployeeQttyPlus2.setText("+");
        ChasisEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(ChasisEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 221, 50, 30));

        BodyworksEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQtty2.setText("1");
        Bugatti.add(BodyworksEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 271, 50, 30));

        BodyworksEmployeeQttyLess2.setText("-");
        BodyworksEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(BodyworksEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 271, 50, 30));

        BodyworksEmployeeQttyPlus2.setText("+");
        BodyworksEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(BodyworksEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 271, 50, 30));

        MotorsEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQtty2.setText("1");
        Bugatti.add(MotorsEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 321, 50, 30));

        MotorsEmployeeQttyLess2.setText("-");
        MotorsEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(MotorsEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 321, 50, 30));

        MotorsEmployeeQttyPlus2.setText("+");
        MotorsEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(MotorsEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 321, 50, 30));

        WheelsEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQtty2.setText("1");
        Bugatti.add(WheelsEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 371, 50, 30));

        WheelsEmployeeQttyLess2.setText("-");
        WheelsEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(WheelsEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 371, 50, 30));

        WheelsEmployeeQttyPlus2.setText("+");
        WheelsEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(WheelsEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 371, 50, 30));

        AccessoriesEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQtty2.setText("1");
        Bugatti.add(AccessoriesEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 421, 50, 30));

        AccessoriesEmployeeQttyLess2.setText("-");
        AccessoriesEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(AccessoriesEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 421, 50, 30));

        AccessoriesEmployeeQttyPlus2.setText("+");
        AccessoriesEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(AccessoriesEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 421, 50, 30));

        AssemblerEmployeeQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQtty2.setText("1");
        Bugatti.add(AssemblerEmployeeQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 471, 50, 30));

        AssemblerEmployeeQttyLess2.setText("-");
        AssemblerEmployeeQttyLess2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyLess2ActionPerformed(evt);
            }
        });
        Bugatti.add(AssemblerEmployeeQttyLess2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 471, 50, 30));

        AssemblerEmployeeQttyPlus2.setText("+");
        AssemblerEmployeeQttyPlus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyPlus2ActionPerformed(evt);
            }
        });
        Bugatti.add(AssemblerEmployeeQttyPlus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 471, 50, 30));

        EmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        EmployeeQttyTitle2.setText("Cantidad de empleados:");
        Bugatti.add(EmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 170, 30));

        BodyworksEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQttyTitle2.setText("Empleados en carrocería");
        Bugatti.add(BodyworksEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 271, 160, 30));

        MotorsEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQttyTitle2.setText("Empleados en motor");
        Bugatti.add(MotorsEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 321, 160, 30));

        WheelsEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQttyTitle2.setText("Empleados en ruedas");
        Bugatti.add(WheelsEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 371, 160, 30));

        AccessoriesEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQttyTitle2.setText("Empleados en accesorios");
        Bugatti.add(AccessoriesEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 421, 160, 30));

        PlantDirectorJob2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PlantDirectorJob2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJob2.setText("Nada");
        Bugatti.add(PlantDirectorJob2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 240, 30));

        ChasisQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisQtty2.setText(Integer.toString(this.maserati.getPartsWarehouse().getChasisDone()));
        Bugatti.add(ChasisQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 151, 50, 30));

        AccessoryCarsAvailableTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoryCarsAvailableTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoryCarsAvailableTitle2.setText("Carros con accesorios disponibles:");
        Bugatti.add(AccessoryCarsAvailableTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 210, 30));

        BodyworksQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getBodyworksDone()));
        Bugatti.add(BodyworksQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 201, 50, 30));

        MotorsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsQtty2.setText(Integer.toString(this.maserati.getPartsWarehouse().getMotorsDone()));
        Bugatti.add(MotorsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 251, 50, 30));

        WheelsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsQtty2.setText(Integer.toString(this.maserati.getPartsWarehouse().getWheelsDone()));
        Bugatti.add(WheelsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 301, 50, 30));

        AccessoriesQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesQtty2.setText(Integer.toString(this.maserati.getPartsWarehouse().getAccessoriesDone()));
        Bugatti.add(AccessoriesQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 351, 50, 30));

        AccessoryCarsAvailable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoryCarsAvailable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoryCarsAvailable2.setText(Integer.toString(this.maserati.getCarsWarehouse().getAccessoryCarsDone()));
        Bugatti.add(AccessoryCarsAvailable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, 50, 30));

        BodyworksQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        BodyworksQttyTitle2.setText("Carrocerías:");
        Bugatti.add(BodyworksQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 201, 80, 30));

        MotorsQttyTitles2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQttyTitles2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MotorsQttyTitles2.setText("Motores:");
        Bugatti.add(MotorsQttyTitles2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 251, 80, 30));

        WheelsQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        WheelsQttyTitle2.setText("Ruedas:");
        Bugatti.add(WheelsQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 301, 80, 30));

        AccessoriesQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesQttyTitle2.setText("Accesorios:");
        Bugatti.add(AccessoriesQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 351, 80, 30));

        MaxAccessoriesQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxAccessoriesQttyTitle2.setText("Máx.");
        Bugatti.add(MaxAccessoriesQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 351, 40, 30));

        AccessoriesCarsQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesCarsQttyTitle2.setText("Carros con accesorios hechos:");
        Bugatti.add(AccessoriesCarsQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 190, 30));

        AccessoriesCarsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesCarsQtty2.setText(Integer.toString(this.maserati.getCarsWarehouse().getAccessoryCarsDone()));
        Bugatti.add(AccessoriesCarsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 50, 30));

        ChasisQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ChasisQttyTitle2.setText("Chasis:");
        Bugatti.add(ChasisQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 151, 80, 30));

        MaxChasisQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxChasisQttyTitle2.setText("Máx.");
        Bugatti.add(MaxChasisQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 151, 40, 30));

        MaxBodyworksQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxBodyworksQttyTitle2.setText("Máx.");
        Bugatti.add(MaxBodyworksQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 201, 40, 30));

        MaxMotorsQttyTitles2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQttyTitles2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxMotorsQttyTitles2.setText("Máx.");
        Bugatti.add(MaxMotorsQttyTitles2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 251, 40, 30));

        MaxWheelsQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxWheelsQttyTitle2.setText("Máx.");
        Bugatti.add(MaxWheelsQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 301, 40, 30));

        MaxChasisQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxChasisQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getLimitChasis()));
        Bugatti.add(MaxChasisQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 151, 50, 30));

        MaxBodyworksQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxBodyworksQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getLimitBodyworks()));
        Bugatti.add(MaxBodyworksQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 201, 50, 30));

        MaxMotorsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxMotorsQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getLimitMotors()));
        Bugatti.add(MaxMotorsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 251, 50, 30));

        MaxWheelsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxWheelsQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getLimitWheels()));
        Bugatti.add(MaxWheelsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 301, 50, 30));

        MaxAccessoriesQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxAccessoriesQtty2.setText(Integer.toString(this.bugatti.getPartsWarehouse().getLimitAccessories()));
        Bugatti.add(MaxAccessoriesQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 351, 50, 30));

        DaysLeft2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeft2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaysLeft2.setText(Integer.toString(this.bugatti.getDaysToDeliver()));
        Bugatti.add(DaysLeft2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 60, 50));

        PlantTitle2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        PlantTitle2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PlantTitle2.setText("Bugatti");
        Bugatti.add(PlantTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 6, 280, -1));

        DaysLeftTitle2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeftTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        DaysLeftTitle2.setText("Días para la entrega:");
        Bugatti.add(DaysLeftTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 19, 200, 50));

        OpManagerTitle2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        OpManagerTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerTitle2.setText("Gerente de operaciones");
        Bugatti.add(OpManagerTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 350, 50));

        EmployeeTitle2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        EmployeeTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeTitle2.setText("Empleados");
        Bugatti.add(EmployeeTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 350, 50));

        PlantDirectorTitle2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PlantDirectorTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorTitle2.setText("Director de la planta");
        Bugatti.add(PlantDirectorTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 350, 50));

        ChasisEmployeeQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQttyTitle2.setText("Empleados en chasis");
        Bugatti.add(ChasisEmployeeQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 221, 160, 30));

        PlantDirectorJobTitle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PlantDirectorJobTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJobTitle2.setText("¿Qué está haciendo?");
        Bugatti.add(PlantDirectorJobTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 240, 30));

        OpManagerJob2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerJob2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJob2.setText("Nada");
        Bugatti.add(OpManagerJob2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 240, 30));

        OpManagerJobTitle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OpManagerJobTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJobTitle2.setText("¿Qué está haciendo?");
        Bugatti.add(OpManagerJobTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 240, 30));

        OpManagerFaults2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaults2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerFaults2.setText(String.valueOf(this.bugatti.getManager().getFaults()));
        Bugatti.add(OpManagerFaults2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, 40, 30));

        OpManagerFaultsTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaultsTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerFaultsTitle2.setText("Faltas:");
        Bugatti.add(OpManagerFaultsTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 150, 30));

        OpManagerMoneyTaken2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTaken2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerMoneyTaken2.setText(String.valueOf(this.bugatti.getManager().getFaults()*50));
        Bugatti.add(OpManagerMoneyTaken2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 40, 30));

        OpManagerMoneyTakenTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTakenTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerMoneyTakenTitle2.setText("Dinero descontado:");
        Bugatti.add(OpManagerMoneyTakenTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 150, 30));

        WarehouseTitle3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        WarehouseTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WarehouseTitle3.setText("Almacén");
        Bugatti.add(WarehouseTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 91, 280, 50));

        RunSim2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RunSim2.setText("Correr simulación");
        RunSim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunSim2ActionPerformed(evt);
            }
        });
        Bugatti.add(RunSim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 140, 30));

        StopSim2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StopSim2.setText("Parar simulación");
        StopSim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopSim2ActionPerformed(evt);
            }
        });
        Bugatti.add(StopSim2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, 30));

        TotalDays2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalDays2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        TotalDays2.setText("Días totales:");
        Bugatti.add(TotalDays2, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 52, 198, 50));

        TotalDaysQtty2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalDaysQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalDaysQtty2.setText(Integer.toString(this.bugatti.getTotalDays()));
        Bugatti.add(TotalDaysQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 60, 50));

        NetIncomeValue2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NetIncomeValue2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NetIncomeValue2.setText("$" + Float.toString(this.maserati.getGrossIncome()));
        Bugatti.add(NetIncomeValue2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 170, 35));

        OperativeCosts2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OperativeCosts2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OperativeCosts2.setText("Costos operativos:");
        Bugatti.add(OperativeCosts2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 179, 35));

        NetIncomeTittle2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NetIncomeTittle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        NetIncomeTittle2.setText("Ganancias netas:");
        Bugatti.add(NetIncomeTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 179, 35));

        OperativeCostsValue2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OperativeCostsValue2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        OperativeCostsValue2.setText("$" + Float.toString(this.maserati.getCosts()));
        Bugatti.add(OperativeCostsValue2, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 50, 170, 35));

        StandardCarsQttyTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQttyTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StandardCarsQttyTitle2.setText("Carros estándar hechos:");
        Bugatti.add(StandardCarsQttyTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 180, 30));

        StandardCarsAvailableTitle2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsAvailableTitle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StandardCarsAvailableTitle2.setText("Carros estándar disponibles:");
        Bugatti.add(StandardCarsAvailableTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 180, 30));

        StandardCarsQtty2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQtty2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StandardCarsQtty2.setText(Integer.toString(this.maserati.getCarsWarehouse().getStandardCarsDone()));
        Bugatti.add(StandardCarsQtty2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 50, 30));

        StandardCarsAvailable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsAvailable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StandardCarsAvailable2.setText(Integer.toString(this.maserati.getCarsWarehouse().getStandardCarsDone()));
        Bugatti.add(StandardCarsAvailable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 50, 30));

        GrossIncomeTittle2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GrossIncomeTittle2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        GrossIncomeTittle2.setText("Ganancias brutas:");
        Bugatti.add(GrossIncomeTittle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 179, 35));

        GrossIncomeValue2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GrossIncomeValue2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GrossIncomeValue2.setText("$" + Float.toString(this.maserati.getGrossIncome()));
        Bugatti.add(GrossIncomeValue2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 170, 35));

        TabbedPane.addTab("tab2", Bugatti);

        Maserati.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChasisEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQtty1.setText("1");
        Maserati.add(ChasisEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 221, 50, 30));

        AssemblerEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQttyTitle1.setText("Empleados en ens.");
        Maserati.add(AssemblerEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 471, 160, 30));

        EmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeQtty1.setText(String.valueOf(this.maserati.getMaxEmployees()));
        Maserati.add(EmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 50, 30));

        ChasisEmployeeQttyLess1.setText("-");
        ChasisEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(ChasisEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 221, 50, 30));

        ChasisEmployeeQttyPlus1.setText("+");
        ChasisEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(ChasisEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 221, 50, 30));

        BodyworksEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQtty1.setText("1");
        Maserati.add(BodyworksEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 271, 50, 30));

        BodyworksEmployeeQttyLess1.setText("-");
        BodyworksEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(BodyworksEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 271, 50, 30));

        BodyworksEmployeeQttyPlus1.setText("+");
        BodyworksEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(BodyworksEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 271, 50, 30));

        MotorsEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQtty1.setText("1");
        Maserati.add(MotorsEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 321, 50, 30));

        MotorsEmployeeQttyLess1.setText("-");
        MotorsEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(MotorsEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 321, 50, 30));

        MotorsEmployeeQttyPlus1.setText("+");
        MotorsEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(MotorsEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 321, 50, 30));

        WheelsEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQtty1.setText("1");
        Maserati.add(WheelsEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 371, 50, 30));

        WheelsEmployeeQttyLess1.setText("-");
        WheelsEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(WheelsEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 371, 50, 30));

        WheelsEmployeeQttyPlus1.setText("+");
        WheelsEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(WheelsEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 371, 50, 30));

        AccessoriesEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQtty1.setText("1");
        Maserati.add(AccessoriesEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 421, 50, 30));

        AccessoriesEmployeeQttyLess1.setText("-");
        AccessoriesEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(AccessoriesEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 421, 50, 30));

        AccessoriesEmployeeQttyPlus1.setText("+");
        AccessoriesEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(AccessoriesEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 421, 50, 30));

        AssemblerEmployeeQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQtty1.setText("1");
        Maserati.add(AssemblerEmployeeQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 471, 50, 30));

        AssemblerEmployeeQttyLess1.setText("-");
        AssemblerEmployeeQttyLess1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyLess1ActionPerformed(evt);
            }
        });
        Maserati.add(AssemblerEmployeeQttyLess1, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 471, 50, 30));

        AssemblerEmployeeQttyPlus1.setText("+");
        AssemblerEmployeeQttyPlus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyPlus1ActionPerformed(evt);
            }
        });
        Maserati.add(AssemblerEmployeeQttyPlus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 471, 50, 30));

        EmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        EmployeeQttyTitle1.setText("Cantidad de empleados:");
        Maserati.add(EmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 170, 30));

        BodyworksEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQttyTitle1.setText("Empleados en carrocería");
        Maserati.add(BodyworksEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 271, 160, 30));

        MotorsEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQttyTitle1.setText("Empleados en motor");
        Maserati.add(MotorsEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 321, 160, 30));

        WheelsEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQttyTitle1.setText("Empleados en ruedas");
        Maserati.add(WheelsEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 371, 160, 30));

        AccessoriesEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQttyTitle1.setText("Empleados en accesorios");
        Maserati.add(AccessoriesEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 421, 160, 30));

        PlantDirectorJob1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PlantDirectorJob1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJob1.setText("Nada");
        Maserati.add(PlantDirectorJob1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 240, 30));

        ChasisQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getChasisDone()));
        Maserati.add(ChasisQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 151, 50, 30));

        AccessoryCarsAvailableTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoryCarsAvailableTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoryCarsAvailableTitle1.setText("Carros con accesorios disponibles:");
        Maserati.add(AccessoryCarsAvailableTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 210, 30));

        BodyworksQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getBodyworksDone()));
        Maserati.add(BodyworksQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 201, 50, 30));

        MotorsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getMotorsDone()));
        Maserati.add(MotorsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 251, 50, 30));

        WheelsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getWheelsDone()));
        Maserati.add(WheelsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 301, 50, 30));

        AccessoriesQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getAccessoriesDone()));
        Maserati.add(AccessoriesQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 351, 50, 30));

        AccessoryCarsAvailable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoryCarsAvailable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoryCarsAvailable1.setText(Integer.toString(this.maserati.getCarsWarehouse().getAccessoryCarsDone()));
        Maserati.add(AccessoryCarsAvailable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, 50, 30));

        BodyworksQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        BodyworksQttyTitle1.setText("Carrocerías:");
        Maserati.add(BodyworksQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 201, 80, 30));

        MotorsQttyTitles1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQttyTitles1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MotorsQttyTitles1.setText("Motores:");
        Maserati.add(MotorsQttyTitles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 251, 80, 30));

        WheelsQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        WheelsQttyTitle1.setText("Ruedas:");
        Maserati.add(WheelsQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 301, 80, 30));

        AccessoriesQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesQttyTitle1.setText("Accesorios:");
        Maserati.add(AccessoriesQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 351, 80, 30));

        MaxAccessoriesQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxAccessoriesQttyTitle1.setText("Máx.");
        Maserati.add(MaxAccessoriesQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 351, 40, 30));

        AccessoriesCarsQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesCarsQttyTitle1.setText("Carros con accesorios hechos:");
        Maserati.add(AccessoriesCarsQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 190, 30));

        AccessoriesCarsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesCarsQtty1.setText(Integer.toString(this.maserati.getCarsWarehouse().getAccessoryCarsDone()));
        Maserati.add(AccessoriesCarsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 50, 30));

        ChasisQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ChasisQttyTitle1.setText("Chasis:");
        Maserati.add(ChasisQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 151, 80, 30));

        MaxChasisQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxChasisQttyTitle1.setText("Máx.");
        Maserati.add(MaxChasisQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 151, 40, 30));

        MaxBodyworksQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxBodyworksQttyTitle1.setText("Máx.");
        Maserati.add(MaxBodyworksQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 201, 40, 30));

        MaxMotorsQttyTitles1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQttyTitles1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxMotorsQttyTitles1.setText("Máx.");
        Maserati.add(MaxMotorsQttyTitles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 251, 40, 30));

        MaxWheelsQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxWheelsQttyTitle1.setText("Máx.");
        Maserati.add(MaxWheelsQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 301, 40, 30));

        MaxChasisQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxChasisQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getLimitChasis()));
        Maserati.add(MaxChasisQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 151, 50, 30));

        MaxBodyworksQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxBodyworksQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getLimitBodyworks()));
        Maserati.add(MaxBodyworksQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 201, 50, 30));

        MaxMotorsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxMotorsQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getLimitMotors()));
        Maserati.add(MaxMotorsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 251, 50, 30));

        MaxWheelsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxWheelsQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getLimitWheels()));
        Maserati.add(MaxWheelsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 301, 50, 30));

        MaxAccessoriesQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxAccessoriesQtty1.setText(Integer.toString(this.maserati.getPartsWarehouse().getLimitAccessories()));
        Maserati.add(MaxAccessoriesQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 351, 50, 30));

        DaysLeft1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeft1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaysLeft1.setText(Integer.toString(this.maserati.getDaysToDeliver()));
        Maserati.add(DaysLeft1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 60, 50));

        PlantTitle1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        PlantTitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PlantTitle1.setText("Maserati");
        Maserati.add(PlantTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 6, 280, -1));

        DaysLeftTitle1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeftTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        DaysLeftTitle1.setText("Días para la entrega:");
        Maserati.add(DaysLeftTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 19, 200, 50));

        OpManagerTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        OpManagerTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerTitle1.setText("Gerente de operaciones");
        Maserati.add(OpManagerTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 350, 50));

        EmployeeTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        EmployeeTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeTitle1.setText("Empleados");
        Maserati.add(EmployeeTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 350, 50));

        PlantDirectorTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PlantDirectorTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorTitle1.setText("Director de la planta");
        Maserati.add(PlantDirectorTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 350, 50));

        ChasisEmployeeQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQttyTitle1.setText("Empleados en chasis");
        Maserati.add(ChasisEmployeeQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 221, 160, 30));

        PlantDirectorJobTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PlantDirectorJobTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJobTitle1.setText("¿Qué está haciendo?");
        Maserati.add(PlantDirectorJobTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 240, 30));

        OpManagerJob1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerJob1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJob1.setText("Nada");
        Maserati.add(OpManagerJob1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 240, 30));

        OpManagerJobTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OpManagerJobTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJobTitle1.setText("¿Qué está haciendo?");
        Maserati.add(OpManagerJobTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 240, 30));

        OpManagerFaults1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaults1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerFaults1.setText(String.valueOf(this.maserati.getManager().getFaults()));
        Maserati.add(OpManagerFaults1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, 40, 30));

        OpManagerFaultsTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaultsTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerFaultsTitle1.setText("Faltas:");
        Maserati.add(OpManagerFaultsTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 150, 30));

        OpManagerMoneyTaken1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTaken1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerMoneyTaken1.setText(String.valueOf(this.maserati.getManager().getFaults()*50));
        Maserati.add(OpManagerMoneyTaken1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 40, 30));

        OpManagerMoneyTakenTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTakenTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerMoneyTakenTitle1.setText("Dinero descontado:");
        Maserati.add(OpManagerMoneyTakenTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 150, 30));

        WarehouseTitle2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        WarehouseTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WarehouseTitle2.setText("Almacén");
        Maserati.add(WarehouseTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 91, 280, 50));

        RunSim1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RunSim1.setText("Correr simulación");
        RunSim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunSim1ActionPerformed(evt);
            }
        });
        Maserati.add(RunSim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 140, 30));

        StopSim1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StopSim1.setText("Parar simulación");
        StopSim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopSim1ActionPerformed(evt);
            }
        });
        Maserati.add(StopSim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, 30));

        TotalDays1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalDays1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        TotalDays1.setText("Días totales:");
        Maserati.add(TotalDays1, new org.netbeans.lib.awtextra.AbsoluteConstraints(756, 52, 198, 50));

        TotalDaysQtty1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TotalDaysQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalDaysQtty1.setText(Integer.toString(this.maserati.getTotalDays()));
        Maserati.add(TotalDaysQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 60, 50));

        NetIncomeValue1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NetIncomeValue1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NetIncomeValue1.setText("$" + Float.toString(this.maserati.getGrossIncome()));
        Maserati.add(NetIncomeValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 170, 35));

        OperativeCosts1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OperativeCosts1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OperativeCosts1.setText("Costos operativos:");
        Maserati.add(OperativeCosts1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 179, 35));

        NetIncomeTittle1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NetIncomeTittle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        NetIncomeTittle1.setText("Ganancias netas:");
        Maserati.add(NetIncomeTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 179, 35));

        OperativeCostsValue1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OperativeCostsValue1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        OperativeCostsValue1.setText("$" + Float.toString(this.maserati.getCosts()));
        Maserati.add(OperativeCostsValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 50, 170, 35));

        StandardCarsQttyTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQttyTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StandardCarsQttyTitle1.setText("Carros estándar hechos:");
        Maserati.add(StandardCarsQttyTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 180, 30));

        StandardCarsAvailableTitle1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsAvailableTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StandardCarsAvailableTitle1.setText("Carros estándar disponibles:");
        Maserati.add(StandardCarsAvailableTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 180, 30));

        StandardCarsQtty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQtty1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StandardCarsQtty1.setText(Integer.toString(this.maserati.getCarsWarehouse().getStandardCarsDone()));
        Maserati.add(StandardCarsQtty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 50, 30));

        StandardCarsAvailable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsAvailable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StandardCarsAvailable1.setText(Integer.toString(this.maserati.getCarsWarehouse().getStandardCarsDone()));
        Maserati.add(StandardCarsAvailable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 50, 30));

        GrossIncomeTittle1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GrossIncomeTittle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        GrossIncomeTittle1.setText("Ganancias brutas:");
        Maserati.add(GrossIncomeTittle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 179, 35));

        GrossIncomeValue1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GrossIncomeValue1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GrossIncomeValue1.setText("$" + Float.toString(this.maserati.getGrossIncome()));
        Maserati.add(GrossIncomeValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 170, 35));

        TabbedPane.addTab("tab3", Maserati);

        Days.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SecondsForDay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SecondsForDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SecondsForDay.setText(String.valueOf(this.dayDuration));
        Days.add(SecondsForDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 60, 40));

        LessSecondsForDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LessSecondsForDay.setText("-");
        LessSecondsForDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LessSecondsForDayActionPerformed(evt);
            }
        });
        Days.add(LessSecondsForDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 60, 40));

        PlusSecondsForDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PlusSecondsForDay.setText("+");
        PlusSecondsForDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlusSecondsForDayActionPerformed(evt);
            }
        });
        Days.add(PlusSecondsForDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 60, 40));

        DaysForDelivery.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        DaysForDelivery.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaysForDelivery.setText(String.valueOf(this.dayCounter));
        Days.add(DaysForDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 60, 30));

        DaysForDeliveryLess.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DaysForDeliveryLess.setText("-");
        DaysForDeliveryLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DaysForDeliveryLessActionPerformed(evt);
            }
        });
        Days.add(DaysForDeliveryLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 60, 40));

        DaysForDeliveryPlus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DaysForDeliveryPlus.setText("+");
        DaysForDeliveryPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DaysForDeliveryPlusActionPerformed(evt);
            }
        });
        Days.add(DaysForDeliveryPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 60, 40));

        DaysTitle1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        DaysTitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DaysTitle1.setText("Días");
        Days.add(DaysTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 6, 280, -1));

        ChasisEmployeeQttyTitle4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ChasisEmployeeQttyTitle4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQttyTitle4.setText("Segundos por día");
        Days.add(ChasisEmployeeQttyTitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 260, 30));

        DaysForDeliveryTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        DaysForDeliveryTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaysForDeliveryTitle1.setText("Días para la entrega");
        Days.add(DaysForDeliveryTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 260, 30));

        imageCarDays.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/BugattiBackground.jpg"))); // NOI18N
        imageCarDays.setText("imageCarDays");
        Days.add(imageCarDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 630, 440));

        TabbedPane.addTab("tab4", Days);

        Panel.add(TabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 1030, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        TabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        TabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        TabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void DaysMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DaysMenuMouseClicked
        TabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_DaysMenuMouseClicked

    private void ChasisEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyLess1ActionPerformed
        String originalString = ChasisEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        ChasisEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_ChasisEmployeeQttyLess1ActionPerformed

    private void ChasisEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyPlus1ActionPerformed
        String originalString = ChasisEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        ChasisEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_ChasisEmployeeQttyPlus1ActionPerformed

    private void BodyworksEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyLess1ActionPerformed
        String originalString = BodyworksEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        BodyworksEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_BodyworksEmployeeQttyLess1ActionPerformed

    private void BodyworksEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyPlus1ActionPerformed
        String originalString = BodyworksEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        BodyworksEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_BodyworksEmployeeQttyPlus1ActionPerformed

    private void MotorsEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyLess1ActionPerformed
        String originalString = MotorsEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        MotorsEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_MotorsEmployeeQttyLess1ActionPerformed

    private void MotorsEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyPlus1ActionPerformed
        String originalString = MotorsEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        MotorsEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_MotorsEmployeeQttyPlus1ActionPerformed

    private void WheelsEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyLess1ActionPerformed
        String originalString = WheelsEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        WheelsEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_WheelsEmployeeQttyLess1ActionPerformed

    private void WheelsEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyPlus1ActionPerformed
        String originalString = WheelsEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        WheelsEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_WheelsEmployeeQttyPlus1ActionPerformed

    private void AccessoriesEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyLess1ActionPerformed
        String originalString = AccessoriesEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        AccessoriesEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AccessoriesEmployeeQttyLess1ActionPerformed

    private void AccessoriesEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyPlus1ActionPerformed
        String originalString = AccessoriesEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        AccessoriesEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AccessoriesEmployeeQttyPlus1ActionPerformed

    private void AssemblerEmployeeQttyLess1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyLess1ActionPerformed
        String originalString = AssemblerEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        AssemblerEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AssemblerEmployeeQttyLess1ActionPerformed

    private void AssemblerEmployeeQttyPlus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyPlus1ActionPerformed
        String originalString = AssemblerEmployeeQtty1.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty1.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty1.setText(String.valueOf(employeeQtty));
        AssemblerEmployeeQtty1.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AssemblerEmployeeQttyPlus1ActionPerformed

    private void RunSim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunSim1ActionPerformed
        this.maserati.run();
    }//GEN-LAST:event_RunSim1ActionPerformed

    private void StopSim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopSim1ActionPerformed
        this.maserati.stop();
    }//GEN-LAST:event_StopSim1ActionPerformed

    private void ChasisEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyLess2ActionPerformed
        String originalString = ChasisEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        ChasisEmployeeQtty2.setText(String.valueOf(newNumber));
        
    }//GEN-LAST:event_ChasisEmployeeQttyLess2ActionPerformed

    private void ChasisEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyPlus2ActionPerformed
        String originalString = ChasisEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        ChasisEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_ChasisEmployeeQttyPlus2ActionPerformed

    private void BodyworksEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyLess2ActionPerformed
        String originalString = BodyworksEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        BodyworksEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_BodyworksEmployeeQttyLess2ActionPerformed

    private void BodyworksEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyPlus2ActionPerformed
        String originalString = BodyworksEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        BodyworksEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_BodyworksEmployeeQttyPlus2ActionPerformed

    private void MotorsEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyLess2ActionPerformed
        String originalString = MotorsEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        MotorsEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_MotorsEmployeeQttyLess2ActionPerformed

    private void MotorsEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyPlus2ActionPerformed
        String originalString = MotorsEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        MotorsEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_MotorsEmployeeQttyPlus2ActionPerformed

    private void WheelsEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyLess2ActionPerformed
        String originalString = WheelsEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        WheelsEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_WheelsEmployeeQttyLess2ActionPerformed

    private void WheelsEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyPlus2ActionPerformed
        String originalString = WheelsEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        WheelsEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_WheelsEmployeeQttyPlus2ActionPerformed

    private void AccessoriesEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyLess2ActionPerformed
        String originalString = AccessoriesEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        AccessoriesEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AccessoriesEmployeeQttyLess2ActionPerformed

    private void AccessoriesEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyPlus2ActionPerformed
        String originalString = AccessoriesEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        AccessoriesEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AccessoriesEmployeeQttyPlus2ActionPerformed

    private void AssemblerEmployeeQttyLess2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyLess2ActionPerformed
        String originalString = AssemblerEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (newNumber > 1) {
            newNumber--;
            employeeQtty++;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        AssemblerEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AssemblerEmployeeQttyLess2ActionPerformed

    private void AssemblerEmployeeQttyPlus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyPlus2ActionPerformed
        String originalString = AssemblerEmployeeQtty2.getText();
        int employeeQtty = Integer.parseInt(EmployeeQtty2.getText());
        int newNumber = Integer.parseInt(originalString);
        if (employeeQtty > 0) {
            newNumber++;
            employeeQtty--;
        }
        EmployeeQtty2.setText(String.valueOf(employeeQtty));
        AssemblerEmployeeQtty2.setText(String.valueOf(newNumber));
    }//GEN-LAST:event_AssemblerEmployeeQttyPlus2ActionPerformed

    private void RunSim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunSim2ActionPerformed
        this.bugatti.run();
    }//GEN-LAST:event_RunSim2ActionPerformed

    private void StopSim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopSim2ActionPerformed
        this.bugatti.stop();
    }//GEN-LAST:event_StopSim2ActionPerformed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        TabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void LessSecondsForDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LessSecondsForDayActionPerformed
        int number = Integer.parseInt(SecondsForDay.getText());
        if (number > 1) {
            number--;
            SecondsForDay.setText(String.valueOf(number));
        }
    }//GEN-LAST:event_LessSecondsForDayActionPerformed

    private void PlusSecondsForDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlusSecondsForDayActionPerformed
        int number = Integer.parseInt(SecondsForDay.getText());
        number++;
        SecondsForDay.setText(String.valueOf(number));
    }//GEN-LAST:event_PlusSecondsForDayActionPerformed

    private void DaysForDeliveryLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DaysForDeliveryLessActionPerformed
        int number = Integer.parseInt(DaysForDelivery.getText());
        if (number > 1) {
            number--;
            DaysForDelivery.setText(String.valueOf(number));
        }
    }//GEN-LAST:event_DaysForDeliveryLessActionPerformed

    private void DaysForDeliveryPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DaysForDeliveryPlusActionPerformed
        int number = Integer.parseInt(DaysForDelivery.getText());
        number++;
        DaysForDelivery.setText(String.valueOf(number));
    }//GEN-LAST:event_DaysForDeliveryPlusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AccessoriesCarsQtty1;
    private javax.swing.JLabel AccessoriesCarsQtty2;
    private javax.swing.JLabel AccessoriesCarsQttyTitle1;
    private javax.swing.JLabel AccessoriesCarsQttyTitle2;
    private javax.swing.JLabel AccessoriesEmployeeQtty1;
    private javax.swing.JLabel AccessoriesEmployeeQtty2;
    private javax.swing.JButton AccessoriesEmployeeQttyLess1;
    private javax.swing.JButton AccessoriesEmployeeQttyLess2;
    private javax.swing.JButton AccessoriesEmployeeQttyPlus1;
    private javax.swing.JButton AccessoriesEmployeeQttyPlus2;
    private javax.swing.JLabel AccessoriesEmployeeQttyTitle1;
    private javax.swing.JLabel AccessoriesEmployeeQttyTitle2;
    private javax.swing.JLabel AccessoriesQtty1;
    private javax.swing.JLabel AccessoriesQtty2;
    private javax.swing.JLabel AccessoriesQttyTitle1;
    private javax.swing.JLabel AccessoriesQttyTitle2;
    private javax.swing.JLabel AccessoryCarsAvailable1;
    private javax.swing.JLabel AccessoryCarsAvailable2;
    private javax.swing.JLabel AccessoryCarsAvailableTitle1;
    private javax.swing.JLabel AccessoryCarsAvailableTitle2;
    private javax.swing.JLabel AssemblerEmployeeQtty1;
    private javax.swing.JLabel AssemblerEmployeeQtty2;
    private javax.swing.JButton AssemblerEmployeeQttyLess1;
    private javax.swing.JButton AssemblerEmployeeQttyLess2;
    private javax.swing.JButton AssemblerEmployeeQttyPlus1;
    private javax.swing.JButton AssemblerEmployeeQttyPlus2;
    private javax.swing.JLabel AssemblerEmployeeQttyTitle1;
    private javax.swing.JLabel AssemblerEmployeeQttyTitle2;
    private javax.swing.JLabel BodyworksEmployeeQtty1;
    private javax.swing.JLabel BodyworksEmployeeQtty2;
    private javax.swing.JButton BodyworksEmployeeQttyLess1;
    private javax.swing.JButton BodyworksEmployeeQttyLess2;
    private javax.swing.JButton BodyworksEmployeeQttyPlus1;
    private javax.swing.JButton BodyworksEmployeeQttyPlus2;
    private javax.swing.JLabel BodyworksEmployeeQttyTitle1;
    private javax.swing.JLabel BodyworksEmployeeQttyTitle2;
    private javax.swing.JLabel BodyworksQtty1;
    private javax.swing.JLabel BodyworksQtty2;
    private javax.swing.JLabel BodyworksQttyTitle1;
    private javax.swing.JLabel BodyworksQttyTitle2;
    private javax.swing.JPanel Bugatti;
    private javax.swing.JLabel BugattiTitleDashboard;
    private javax.swing.JLabel ChasisEmployeeQtty1;
    private javax.swing.JLabel ChasisEmployeeQtty2;
    private javax.swing.JButton ChasisEmployeeQttyLess1;
    private javax.swing.JButton ChasisEmployeeQttyLess2;
    private javax.swing.JButton ChasisEmployeeQttyPlus1;
    private javax.swing.JButton ChasisEmployeeQttyPlus2;
    private javax.swing.JLabel ChasisEmployeeQttyTitle1;
    private javax.swing.JLabel ChasisEmployeeQttyTitle2;
    private javax.swing.JLabel ChasisEmployeeQttyTitle4;
    private javax.swing.JLabel ChasisQtty1;
    private javax.swing.JLabel ChasisQtty2;
    private javax.swing.JLabel ChasisQttyTitle1;
    private javax.swing.JLabel ChasisQttyTitle2;
    private javax.swing.JLabel CostsBugattiDashboardTittle;
    private javax.swing.JLabel CostsBugattiDashboardValue;
    private javax.swing.JLabel CostsMaseratiDashboardTittle;
    private javax.swing.JLabel CostsMaseratiDashboardValue;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Days;
    private javax.swing.JLabel DaysForDelivery;
    private javax.swing.JButton DaysForDeliveryLess;
    private javax.swing.JButton DaysForDeliveryPlus;
    private javax.swing.JLabel DaysForDeliveryTitle1;
    private javax.swing.JLabel DaysLeft1;
    private javax.swing.JLabel DaysLeft2;
    private javax.swing.JLabel DaysLeftTitle1;
    private javax.swing.JLabel DaysLeftTitle2;
    private javax.swing.JPanel DaysMenu;
    private javax.swing.JLabel DaysTitle1;
    private javax.swing.JLabel EmployeeQtty1;
    private javax.swing.JLabel EmployeeQtty2;
    private javax.swing.JLabel EmployeeQttyTitle1;
    private javax.swing.JLabel EmployeeQttyTitle2;
    private javax.swing.JLabel EmployeeTitle1;
    private javax.swing.JLabel EmployeeTitle2;
    private javax.swing.JLabel GrossIncomeBugattiDashboardTittle;
    private javax.swing.JLabel GrossIncomeBugattiDashboardValue;
    private javax.swing.JLabel GrossIncomeMaseratiDashboardTittle;
    private javax.swing.JLabel GrossIncomeMaseratiDashboardValue;
    private javax.swing.JLabel GrossIncomeTittle1;
    private javax.swing.JLabel GrossIncomeTittle2;
    private javax.swing.JLabel GrossIncomeValue1;
    private javax.swing.JLabel GrossIncomeValue2;
    private javax.swing.JButton LessSecondsForDay;
    private javax.swing.JPanel Maserati;
    private javax.swing.JLabel MaseratiTitle;
    private javax.swing.JLabel MaxAccessoriesQtty1;
    private javax.swing.JLabel MaxAccessoriesQtty2;
    private javax.swing.JLabel MaxAccessoriesQttyTitle1;
    private javax.swing.JLabel MaxAccessoriesQttyTitle2;
    private javax.swing.JLabel MaxBodyworksQtty1;
    private javax.swing.JLabel MaxBodyworksQtty2;
    private javax.swing.JLabel MaxBodyworksQttyTitle1;
    private javax.swing.JLabel MaxBodyworksQttyTitle2;
    private javax.swing.JLabel MaxChasisQtty1;
    private javax.swing.JLabel MaxChasisQtty2;
    private javax.swing.JLabel MaxChasisQttyTitle1;
    private javax.swing.JLabel MaxChasisQttyTitle2;
    private javax.swing.JLabel MaxMotorsQtty1;
    private javax.swing.JLabel MaxMotorsQtty2;
    private javax.swing.JLabel MaxMotorsQttyTitles1;
    private javax.swing.JLabel MaxMotorsQttyTitles2;
    private javax.swing.JLabel MaxWheelsQtty1;
    private javax.swing.JLabel MaxWheelsQtty2;
    private javax.swing.JLabel MaxWheelsQttyTitle1;
    private javax.swing.JLabel MaxWheelsQttyTitle2;
    private javax.swing.JPanel MenuBar;
    private javax.swing.JLabel MotorsEmployeeQtty1;
    private javax.swing.JLabel MotorsEmployeeQtty2;
    private javax.swing.JButton MotorsEmployeeQttyLess1;
    private javax.swing.JButton MotorsEmployeeQttyLess2;
    private javax.swing.JButton MotorsEmployeeQttyPlus1;
    private javax.swing.JButton MotorsEmployeeQttyPlus2;
    private javax.swing.JLabel MotorsEmployeeQttyTitle1;
    private javax.swing.JLabel MotorsEmployeeQttyTitle2;
    private javax.swing.JLabel MotorsQtty1;
    private javax.swing.JLabel MotorsQtty2;
    private javax.swing.JLabel MotorsQttyTitles1;
    private javax.swing.JLabel MotorsQttyTitles2;
    private javax.swing.JLabel NetIncomeBugattiDashboardTittle;
    private javax.swing.JLabel NetIncomeBugattiDashboardValue;
    private javax.swing.JLabel NetIncomeMaseratiDashboardTittle;
    private javax.swing.JLabel NetIncomeMaseratiDashboardValue;
    private javax.swing.JLabel NetIncomeTittle1;
    private javax.swing.JLabel NetIncomeTittle2;
    private javax.swing.JLabel NetIncomeValue1;
    private javax.swing.JLabel NetIncomeValue2;
    private javax.swing.JLabel OpManagerFaults1;
    private javax.swing.JLabel OpManagerFaults2;
    private javax.swing.JLabel OpManagerFaultsTitle1;
    private javax.swing.JLabel OpManagerFaultsTitle2;
    private javax.swing.JLabel OpManagerJob1;
    private javax.swing.JLabel OpManagerJob2;
    private javax.swing.JLabel OpManagerJobTitle1;
    private javax.swing.JLabel OpManagerJobTitle2;
    private javax.swing.JLabel OpManagerMoneyTaken1;
    private javax.swing.JLabel OpManagerMoneyTaken2;
    private javax.swing.JLabel OpManagerMoneyTakenTitle1;
    private javax.swing.JLabel OpManagerMoneyTakenTitle2;
    private javax.swing.JLabel OpManagerTitle1;
    private javax.swing.JLabel OpManagerTitle2;
    private javax.swing.JLabel OperativeCosts1;
    private javax.swing.JLabel OperativeCosts2;
    private javax.swing.JLabel OperativeCostsValue1;
    private javax.swing.JLabel OperativeCostsValue2;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel PlantDirectorJob1;
    private javax.swing.JLabel PlantDirectorJob2;
    private javax.swing.JLabel PlantDirectorJobTitle1;
    private javax.swing.JLabel PlantDirectorJobTitle2;
    private javax.swing.JLabel PlantDirectorTitle1;
    private javax.swing.JLabel PlantDirectorTitle2;
    private javax.swing.JLabel PlantTitle1;
    private javax.swing.JLabel PlantTitle2;
    private javax.swing.JButton PlusSecondsForDay;
    private javax.swing.JButton RunSim1;
    private javax.swing.JButton RunSim2;
    private javax.swing.JLabel SecondsForDay;
    private javax.swing.JLabel StandardCarsAvailable1;
    private javax.swing.JLabel StandardCarsAvailable2;
    private javax.swing.JLabel StandardCarsAvailableTitle1;
    private javax.swing.JLabel StandardCarsAvailableTitle2;
    private javax.swing.JLabel StandardCarsQtty1;
    private javax.swing.JLabel StandardCarsQtty2;
    private javax.swing.JLabel StandardCarsQttyTitle1;
    private javax.swing.JLabel StandardCarsQttyTitle2;
    private javax.swing.JButton StopSim1;
    private javax.swing.JButton StopSim2;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel TitleProject;
    private javax.swing.JLabel TotalDays1;
    private javax.swing.JLabel TotalDays2;
    private javax.swing.JLabel TotalDaysQtty1;
    private javax.swing.JLabel TotalDaysQtty2;
    private javax.swing.JLabel WarehouseTitle2;
    private javax.swing.JLabel WarehouseTitle3;
    private javax.swing.JLabel WheelsEmployeeQtty1;
    private javax.swing.JLabel WheelsEmployeeQtty2;
    private javax.swing.JButton WheelsEmployeeQttyLess1;
    private javax.swing.JButton WheelsEmployeeQttyLess2;
    private javax.swing.JButton WheelsEmployeeQttyPlus1;
    private javax.swing.JButton WheelsEmployeeQttyPlus2;
    private javax.swing.JLabel WheelsEmployeeQttyTitle1;
    private javax.swing.JLabel WheelsEmployeeQttyTitle2;
    private javax.swing.JLabel WheelsQtty1;
    private javax.swing.JLabel WheelsQtty2;
    private javax.swing.JLabel WheelsQttyTitle1;
    private javax.swing.JLabel WheelsQttyTitle2;
    private javax.swing.JLabel imageCarDays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
