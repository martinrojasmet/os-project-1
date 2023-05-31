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
/**
 *
 * @author marti
 */
public class GUI extends javax.swing.JFrame {
    
    private CarsPlant maserati;
    private CarsPlant bugatti;
    private int dayDuration;
    private int dayCounter;
    /**
     * Creates new form GUI
     */
    public GUI() {
        this.dayDuration = 3000;
        this.dayCounter = 3;
        this.loadSetDaysJson();
        
        StandardVehicle standard = new StandardVehicle(3, 4, 2, 3, 500000);
        AccessoryVehicle accessory = new AccessoryVehicle(3, 4, 2, 3, 2, 500000);
        
        this.bugatti = new CarsPlant(2, 19, 10, 5, standard, accessory, this, true);
        this.maserati = new CarsPlant(1, 19, 10, 5, standard, accessory, this, false);
        
        this.functions = new FunctionsGUI(this.maserati, this);
        
        initComponents();
        this.loadSetEmployeesJson();
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

            JSONObject initialEmployeeCount = (JSONObject) jsonObject.get("initialEmployeeCount");
            int chasisInitial = ((Long) initialEmployeeCount.get("chasis")).intValue();
            int assemblerInitial = ((Long) initialEmployeeCount.get("assembler")).intValue();
            int bodyworkInitial = ((Long) initialEmployeeCount.get("bodywork")).intValue();
            int wheelsInitial = ((Long) initialEmployeeCount.get("wheels")).intValue();
            int accessoryInitial = ((Long) initialEmployeeCount.get("accessory")).intValue();
            int motorInitial = ((Long) initialEmployeeCount.get("motor")).intValue();
            
            this.setValuesFromJson(dayDuration, dayCounter, chasisInitial, 
            assemblerInitial, bodyworkInitial,wheelsInitial, 
            accessoryInitial, motorInitial);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    public void setValuesFromJson(int dayDuration, int dayCounter, int chasisInitial, 
            int assemblerInitial, int bodyworkInitial, int wheelsInitial, 
            int accessoryInitial, int motorInitial) {
        
        this.getAccessoriesEmployeeQtty().setText(String.valueOf(accessoryInitial));
        this.getAccessoriesEmployeeQtty1().setText(String.valueOf(accessoryInitial));
        
        this.getBodyworksEmployeeQtty().setText(String.valueOf(bodyworkInitial));
        this.getBodyworksEmployeeQtty1().setText(String.valueOf(bodyworkInitial));
        
        this.getChasisEmployeeQtty().setText(String.valueOf(chasisInitial));
        this.getChasisEmployeeQtty1().setText(String.valueOf(chasisInitial));
        
        this.getMotorsEmployeeQtty().setText(String.valueOf(motorInitial));
        this.getMotorsEmployeeQtty1().setText(String.valueOf(motorInitial));
        
        this.getWheelsEmployeeQtty().setText(String.valueOf(wheelsInitial));
        this.getWheelsEmployeeQtty1().setText(String.valueOf(wheelsInitial));
        
        this.getAssemblerEmployeeQtty().setText(String.valueOf(assemblerInitial));
        this.getAssemblerEmployeeQtty1().setText(String.valueOf(assemblerInitial));
        
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
    
    public JLabel getAccessoriesCarsQtty() {
        return AccessoriesCarsQtty;
    }

    public void setAccessoriesCarsQtty(JLabel AccessoriesCarsQtty) {
        this.AccessoriesCarsQtty = AccessoriesCarsQtty;
    }

    public JLabel getAccessoriesCarsQtty1() {
        return AccessoriesCarsQtty1;
    }

    public void setAccessoriesCarsQtty1(JLabel AccessoriesCarsQtty1) {
        this.AccessoriesCarsQtty1 = AccessoriesCarsQtty1;
    }

    public JLabel getAccessoriesCarsQttyTitle() {
        return AccessoriesCarsQttyTitle;
    }

    public void setAccessoriesCarsQttyTitle(JLabel AccessoriesCarsQttyTitle) {
        this.AccessoriesCarsQttyTitle = AccessoriesCarsQttyTitle;
    }

    public JLabel getAccessoriesCarsQttyTitle1() {
        return AccessoriesCarsQttyTitle1;
    }

    public void setAccessoriesCarsQttyTitle1(JLabel AccessoriesCarsQttyTitle1) {
        this.AccessoriesCarsQttyTitle1 = AccessoriesCarsQttyTitle1;
    }

    public JLabel getAccessoriesEmployeeQtty() {
        return AccessoriesEmployeeQtty;
    }

    public void setAccessoriesEmployeeQtty(JLabel AccessoriesEmployeeQtty) {
        this.AccessoriesEmployeeQtty = AccessoriesEmployeeQtty;
    }

    public JLabel getAccessoriesEmployeeQtty1() {
        return AccessoriesEmployeeQtty1;
    }

    public void setAccessoriesEmployeeQtty1(JLabel AccessoriesEmployeeQtty1) {
        this.AccessoriesEmployeeQtty1 = AccessoriesEmployeeQtty1;
    }

    public JButton getAccessoriesEmployeeQttyLess() {
        return AccessoriesEmployeeQttyLess;
    }

    public void setAccessoriesEmployeeQttyLess(JButton AccessoriesEmployeeQttyLess) {
        this.AccessoriesEmployeeQttyLess = AccessoriesEmployeeQttyLess;
    }

    public JButton getAccessoriesEmployeeQttyLess1() {
        return AccessoriesEmployeeQttyLess1;
    }

    public void setAccessoriesEmployeeQttyLess1(JButton AccessoriesEmployeeQttyLess1) {
        this.AccessoriesEmployeeQttyLess1 = AccessoriesEmployeeQttyLess1;
    }

    public JButton getAccessoriesEmployeeQttyPlus() {
        return AccessoriesEmployeeQttyPlus;
    }

    public void setAccessoriesEmployeeQttyPlus(JButton AccessoriesEmployeeQttyPlus) {
        this.AccessoriesEmployeeQttyPlus = AccessoriesEmployeeQttyPlus;
    }

    public JButton getAccessoriesEmployeeQttyPlus1() {
        return AccessoriesEmployeeQttyPlus1;
    }

    public void setAccessoriesEmployeeQttyPlus1(JButton AccessoriesEmployeeQttyPlus1) {
        this.AccessoriesEmployeeQttyPlus1 = AccessoriesEmployeeQttyPlus1;
    }

    public JLabel getAccessoriesEmployeeQttyTitle() {
        return AccessoriesEmployeeQttyTitle;
    }

    public void setAccessoriesEmployeeQttyTitle(JLabel AccessoriesEmployeeQttyTitle) {
        this.AccessoriesEmployeeQttyTitle = AccessoriesEmployeeQttyTitle;
    }

    public JLabel getAccessoriesEmployeeQttyTitle1() {
        return AccessoriesEmployeeQttyTitle1;
    }

    public void setAccessoriesEmployeeQttyTitle1(JLabel AccessoriesEmployeeQttyTitle1) {
        this.AccessoriesEmployeeQttyTitle1 = AccessoriesEmployeeQttyTitle1;
    }

    public JLabel getAccessoriesQtty() {
        return AccessoriesQtty;
    }

    public void setAccessoriesQtty(JLabel AccessoriesQtty) {
        this.AccessoriesQtty = AccessoriesQtty;
    }

    public JLabel getAccessoriesQtty1() {
        return AccessoriesQtty1;
    }

    public void setAccessoriesQtty1(JLabel AccessoriesQtty1) {
        this.AccessoriesQtty1 = AccessoriesQtty1;
    }

    public JLabel getAccessoriesQttyTitle() {
        return AccessoriesQttyTitle;
    }

    public void setAccessoriesQttyTitle(JLabel AccessoriesQttyTitle) {
        this.AccessoriesQttyTitle = AccessoriesQttyTitle;
    }

    public JLabel getAccessoriesQttyTitle1() {
        return AccessoriesQttyTitle1;
    }

    public void setAccessoriesQttyTitle1(JLabel AccessoriesQttyTitle1) {
        this.AccessoriesQttyTitle1 = AccessoriesQttyTitle1;
    }

    public JLabel getAssemblerEmployeeQtty() {
        return AssemblerEmployeeQtty;
    }

    public void setAssemblerEmployeeQtty(JLabel AssemblerEmployeeQtty) {
        this.AssemblerEmployeeQtty = AssemblerEmployeeQtty;
    }

    public JLabel getAssemblerEmployeeQtty1() {
        return AssemblerEmployeeQtty1;
    }

    public void setAssemblerEmployeeQtty1(JLabel AssemblerEmployeeQtty1) {
        this.AssemblerEmployeeQtty1 = AssemblerEmployeeQtty1;
    }

    public JButton getAssemblerEmployeeQttyLess() {
        return AssemblerEmployeeQttyLess;
    }

    public void setAssemblerEmployeeQttyLess(JButton AssemblerEmployeeQttyLess) {
        this.AssemblerEmployeeQttyLess = AssemblerEmployeeQttyLess;
    }

    public JButton getAssemblerEmployeeQttyLess1() {
        return AssemblerEmployeeQttyLess1;
    }

    public void setAssemblerEmployeeQttyLess1(JButton AssemblerEmployeeQttyLess1) {
        this.AssemblerEmployeeQttyLess1 = AssemblerEmployeeQttyLess1;
    }

    public JButton getAssemblerEmployeeQttyPlus() {
        return AssemblerEmployeeQttyPlus;
    }

    public void setAssemblerEmployeeQttyPlus(JButton AssemblerEmployeeQttyPlus) {
        this.AssemblerEmployeeQttyPlus = AssemblerEmployeeQttyPlus;
    }

    public JButton getAssemblerEmployeeQttyPlus1() {
        return AssemblerEmployeeQttyPlus1;
    }

    public void setAssemblerEmployeeQttyPlus1(JButton AssemblerEmployeeQttyPlus1) {
        this.AssemblerEmployeeQttyPlus1 = AssemblerEmployeeQttyPlus1;
    }

    public JLabel getAssemblerEmployeeQttyTitle() {
        return AssemblerEmployeeQttyTitle;
    }

    public void setAssemblerEmployeeQttyTitle(JLabel AssemblerEmployeeQttyTitle) {
        this.AssemblerEmployeeQttyTitle = AssemblerEmployeeQttyTitle;
    }

    public JLabel getAssemblerEmployeeQttyTitle1() {
        return AssemblerEmployeeQttyTitle1;
    }

    public void setAssemblerEmployeeQttyTitle1(JLabel AssemblerEmployeeQttyTitle1) {
        this.AssemblerEmployeeQttyTitle1 = AssemblerEmployeeQttyTitle1;
    }

    public JLabel getBodyworksEmployeeQtty() {
        return BodyworksEmployeeQtty;
    }

    public void setBodyworksEmployeeQtty(JLabel BodyworksEmployeeQtty) {
        this.BodyworksEmployeeQtty = BodyworksEmployeeQtty;
    }

    public JLabel getBodyworksEmployeeQtty1() {
        return BodyworksEmployeeQtty1;
    }

    public void setBodyworksEmployeeQtty1(JLabel BodyworksEmployeeQtty1) {
        this.BodyworksEmployeeQtty1 = BodyworksEmployeeQtty1;
    }

    public JButton getBodyworksEmployeeQttyLess() {
        return BodyworksEmployeeQttyLess;
    }

    public void setBodyworksEmployeeQttyLess(JButton BodyworksEmployeeQttyLess) {
        this.BodyworksEmployeeQttyLess = BodyworksEmployeeQttyLess;
    }

    public JButton getBodyworksEmployeeQttyLess1() {
        return BodyworksEmployeeQttyLess1;
    }

    public void setBodyworksEmployeeQttyLess1(JButton BodyworksEmployeeQttyLess1) {
        this.BodyworksEmployeeQttyLess1 = BodyworksEmployeeQttyLess1;
    }

    public JButton getBodyworksEmployeeQttyPlus() {
        return BodyworksEmployeeQttyPlus;
    }

    public void setBodyworksEmployeeQttyPlus(JButton BodyworksEmployeeQttyPlus) {
        this.BodyworksEmployeeQttyPlus = BodyworksEmployeeQttyPlus;
    }

    public JButton getBodyworksEmployeeQttyPlus1() {
        return BodyworksEmployeeQttyPlus1;
    }

    public void setBodyworksEmployeeQttyPlus1(JButton BodyworksEmployeeQttyPlus1) {
        this.BodyworksEmployeeQttyPlus1 = BodyworksEmployeeQttyPlus1;
    }

    public JLabel getBodyworksEmployeeQttyTitle() {
        return BodyworksEmployeeQttyTitle;
    }

    public void setBodyworksEmployeeQttyTitle(JLabel BodyworksEmployeeQttyTitle) {
        this.BodyworksEmployeeQttyTitle = BodyworksEmployeeQttyTitle;
    }

    public JLabel getBodyworksEmployeeQttyTitle1() {
        return BodyworksEmployeeQttyTitle1;
    }

    public void setBodyworksEmployeeQttyTitle1(JLabel BodyworksEmployeeQttyTitle1) {
        this.BodyworksEmployeeQttyTitle1 = BodyworksEmployeeQttyTitle1;
    }

    public JLabel getBodyworksQtty() {
        return BodyworksQtty;
    }

    public void setBodyworksQtty(JLabel BodyworksQtty) {
        this.BodyworksQtty = BodyworksQtty;
    }

    public JLabel getBodyworksQtty1() {
        return BodyworksQtty1;
    }

    public void setBodyworksQtty1(JLabel BodyworksQtty1) {
        this.BodyworksQtty1 = BodyworksQtty1;
    }

    public JLabel getBodyworksQttyTitle() {
        return BodyworksQttyTitle;
    }

    public void setBodyworksQttyTitle(JLabel BodyworksQttyTitle) {
        this.BodyworksQttyTitle = BodyworksQttyTitle;
    }

    public JLabel getBodyworksQttyTitle1() {
        return BodyworksQttyTitle1;
    }

    public void setBodyworksQttyTitle1(JLabel BodyworksQttyTitle1) {
        this.BodyworksQttyTitle1 = BodyworksQttyTitle1;
    }

    public void setBugatti(JPanel Bugatti) {
        this.Bugatti = Bugatti;
    }

    public JLabel getChasisEmployeeQtty() {
        return ChasisEmployeeQtty;
    }

    public void setChasisEmployeeQtty(JLabel ChasisEmployeeQtty) {
        this.ChasisEmployeeQtty = ChasisEmployeeQtty;
    }

    public JLabel getChasisEmployeeQtty1() {
        return ChasisEmployeeQtty1;
    }

    public void setChasisEmployeeQtty1(JLabel ChasisEmployeeQtty1) {
        this.ChasisEmployeeQtty1 = ChasisEmployeeQtty1;
    }

    public JButton getChasisEmployeeQttyLess() {
        return ChasisEmployeeQttyLess;
    }

    public void setChasisEmployeeQttyLess(JButton ChasisEmployeeQttyLess) {
        this.ChasisEmployeeQttyLess = ChasisEmployeeQttyLess;
    }

    public JButton getChasisEmployeeQttyLess1() {
        return ChasisEmployeeQttyLess1;
    }

    public void setChasisEmployeeQttyLess1(JButton ChasisEmployeeQttyLess1) {
        this.ChasisEmployeeQttyLess1 = ChasisEmployeeQttyLess1;
    }

    public JButton getChasisEmployeeQttyPlus() {
        return ChasisEmployeeQttyPlus;
    }

    public void setChasisEmployeeQttyPlus(JButton ChasisEmployeeQttyPlus) {
        this.ChasisEmployeeQttyPlus = ChasisEmployeeQttyPlus;
    }

    public JButton getChasisEmployeeQttyPlus1() {
        return ChasisEmployeeQttyPlus1;
    }

    public void setChasisEmployeeQttyPlus1(JButton ChasisEmployeeQttyPlus1) {
        this.ChasisEmployeeQttyPlus1 = ChasisEmployeeQttyPlus1;
    }

    public JLabel getChasisEmployeeQttyTitle() {
        return ChasisEmployeeQttyTitle;
    }

    public void setChasisEmployeeQttyTitle(JLabel ChasisEmployeeQttyTitle) {
        this.ChasisEmployeeQttyTitle = ChasisEmployeeQttyTitle;
    }

    public JLabel getChasisEmployeeQttyTitle1() {
        return ChasisEmployeeQttyTitle1;
    }

    public void setChasisEmployeeQttyTitle1(JLabel ChasisEmployeeQttyTitle1) {
        this.ChasisEmployeeQttyTitle1 = ChasisEmployeeQttyTitle1;
    }

    public JLabel getChasisQtty() {
        return ChasisQtty;
    }

    public void setChasisQtty(JLabel ChasisQtty) {
        this.ChasisQtty = ChasisQtty;
    }

    public JLabel getChasisQtty1() {
        return ChasisQtty1;
    }

    public void setChasisQtty1(JLabel ChasisQtty1) {
        this.ChasisQtty1 = ChasisQtty1;
    }

    public JLabel getChasisQttyTitle() {
        return ChasisQttyTitle;
    }

    public void setChasisQttyTitle(JLabel ChasisQttyTitle) {
        this.ChasisQttyTitle = ChasisQttyTitle;
    }

    public JLabel getChasisQttyTitle1() {
        return ChasisQttyTitle1;
    }

    public void setChasisQttyTitle1(JLabel ChasisQttyTitle1) {
        this.ChasisQttyTitle1 = ChasisQttyTitle1;
    }

    public JLabel getDaysLeft() {
        return DaysLeft;
    }

    public void setDaysLeft(JLabel DaysLeft) {
        this.DaysLeft = DaysLeft;
    }

    public JLabel getDaysLeft1() {
        return DaysLeft1;
    }

    public void setDaysLeft1(JLabel DaysLeft1) {
        this.DaysLeft1 = DaysLeft1;
    }

    public JLabel getDaysLeftTitle() {
        return DaysLeftTitle;
    }

    public void setDaysLeftTitle(JLabel DaysLeftTitle) {
        this.DaysLeftTitle = DaysLeftTitle;
    }

    public JLabel getDaysLeftTitle1() {
        return DaysLeftTitle1;
    }

    public void setDaysLeftTitle1(JLabel DaysLeftTitle1) {
        this.DaysLeftTitle1 = DaysLeftTitle1;
    }

    public JLabel getEmployeeQtty() {
        return EmployeeQtty;
    }

    public void setEmployeeQtty(JLabel EmployeeQtty) {
        this.EmployeeQtty = EmployeeQtty;
    }

    public JLabel getEmployeeQtty1() {
        return EmployeeQtty1;
    }

    public void setEmployeeQtty1(JLabel EmployeeQtty1) {
        this.EmployeeQtty1 = EmployeeQtty1;
    }

    public JLabel getEmployeeQttyTitle() {
        return EmployeeQttyTitle;
    }

    public void setEmployeeQttyTitle(JLabel EmployeeQttyTitle) {
        this.EmployeeQttyTitle = EmployeeQttyTitle;
    }

    public JLabel getEmployeeQttyTitle1() {
        return EmployeeQttyTitle1;
    }

    public void setEmployeeQttyTitle1(JLabel EmployeeQttyTitle1) {
        this.EmployeeQttyTitle1 = EmployeeQttyTitle1;
    }

    public JLabel getEmployeeTitle() {
        return EmployeeTitle;
    }

    public void setEmployeeTitle(JLabel EmployeeTitle) {
        this.EmployeeTitle = EmployeeTitle;
    }

    public JLabel getEmployeeTitle1() {
        return EmployeeTitle1;
    }

    public void setEmployeeTitle1(JLabel EmployeeTitle1) {
        this.EmployeeTitle1 = EmployeeTitle1;
    }

    public void setMaserati(JPanel Maserati) {
        this.Maserati = Maserati;
    }

    public JLabel getMaxAccessoriesQtty() {
        return MaxAccessoriesQtty;
    }

    public void setMaxAccessoriesQtty(JLabel MaxAccessoriesQtty) {
        this.MaxAccessoriesQtty = MaxAccessoriesQtty;
    }

    public JLabel getMaxAccessoriesQtty1() {
        return MaxAccessoriesQtty1;
    }

    public void setMaxAccessoriesQtty1(JLabel MaxAccessoriesQtty1) {
        this.MaxAccessoriesQtty1 = MaxAccessoriesQtty1;
    }

    public JLabel getMaxAccessoriesQttyTitle() {
        return MaxAccessoriesQttyTitle;
    }

    public void setMaxAccessoriesQttyTitle(JLabel MaxAccessoriesQttyTitle) {
        this.MaxAccessoriesQttyTitle = MaxAccessoriesQttyTitle;
    }

    public JLabel getMaxAccessoriesQttyTitle1() {
        return MaxAccessoriesQttyTitle1;
    }

    public void setMaxAccessoriesQttyTitle1(JLabel MaxAccessoriesQttyTitle1) {
        this.MaxAccessoriesQttyTitle1 = MaxAccessoriesQttyTitle1;
    }

    public JLabel getMaxBodyworksQtty() {
        return MaxBodyworksQtty;
    }

    public void setMaxBodyworksQtty(JLabel MaxBodyworksQtty) {
        this.MaxBodyworksQtty = MaxBodyworksQtty;
    }

    public JLabel getMaxBodyworksQtty1() {
        return MaxBodyworksQtty1;
    }

    public void setMaxBodyworksQtty1(JLabel MaxBodyworksQtty1) {
        this.MaxBodyworksQtty1 = MaxBodyworksQtty1;
    }

    public JLabel getMaxBodyworksQttyTitle() {
        return MaxBodyworksQttyTitle;
    }

    public void setMaxBodyworksQttyTitle(JLabel MaxBodyworksQttyTitle) {
        this.MaxBodyworksQttyTitle = MaxBodyworksQttyTitle;
    }

    public JLabel getMaxBodyworksQttyTitle1() {
        return MaxBodyworksQttyTitle1;
    }

    public void setMaxBodyworksQttyTitle1(JLabel MaxBodyworksQttyTitle1) {
        this.MaxBodyworksQttyTitle1 = MaxBodyworksQttyTitle1;
    }

    public JLabel getMaxChasisQtty() {
        return MaxChasisQtty;
    }

    public void setMaxChasisQtty(JLabel MaxChasisQtty) {
        this.MaxChasisQtty = MaxChasisQtty;
    }

    public JLabel getMaxChasisQtty1() {
        return MaxChasisQtty1;
    }

    public void setMaxChasisQtty1(JLabel MaxChasisQtty1) {
        this.MaxChasisQtty1 = MaxChasisQtty1;
    }

    public JLabel getMaxChasisQttyTitle() {
        return MaxChasisQttyTitle;
    }

    public void setMaxChasisQttyTitle(JLabel MaxChasisQttyTitle) {
        this.MaxChasisQttyTitle = MaxChasisQttyTitle;
    }

    public JLabel getMaxChasisQttyTitle1() {
        return MaxChasisQttyTitle1;
    }

    public void setMaxChasisQttyTitle1(JLabel MaxChasisQttyTitle1) {
        this.MaxChasisQttyTitle1 = MaxChasisQttyTitle1;
    }

    public JLabel getMaxMotorsQtty() {
        return MaxMotorsQtty;
    }

    public void setMaxMotorsQtty(JLabel MaxMotorsQtty) {
        this.MaxMotorsQtty = MaxMotorsQtty;
    }

    public JLabel getMaxMotorsQtty1() {
        return MaxMotorsQtty1;
    }

    public void setMaxMotorsQtty1(JLabel MaxMotorsQtty1) {
        this.MaxMotorsQtty1 = MaxMotorsQtty1;
    }

    public JLabel getMaxMotorsQttyTitles() {
        return MaxMotorsQttyTitles;
    }

    public void setMaxMotorsQttyTitles(JLabel MaxMotorsQttyTitles) {
        this.MaxMotorsQttyTitles = MaxMotorsQttyTitles;
    }

    public JLabel getMaxMotorsQttyTitles1() {
        return MaxMotorsQttyTitles1;
    }

    public void setMaxMotorsQttyTitles1(JLabel MaxMotorsQttyTitles1) {
        this.MaxMotorsQttyTitles1 = MaxMotorsQttyTitles1;
    }

    public JLabel getMaxWheelsQtty() {
        return MaxWheelsQtty;
    }

    public void setMaxWheelsQtty(JLabel MaxWheelsQtty) {
        this.MaxWheelsQtty = MaxWheelsQtty;
    }

    public JLabel getMaxWheelsQtty1() {
        return MaxWheelsQtty1;
    }

    public void setMaxWheelsQtty1(JLabel MaxWheelsQtty1) {
        this.MaxWheelsQtty1 = MaxWheelsQtty1;
    }

    public JLabel getMaxWheelsQttyTitle() {
        return MaxWheelsQttyTitle;
    }

    public void setMaxWheelsQttyTitle(JLabel MaxWheelsQttyTitle) {
        this.MaxWheelsQttyTitle = MaxWheelsQttyTitle;
    }

    public JLabel getMaxWheelsQttyTitle1() {
        return MaxWheelsQttyTitle1;
    }

    public void setMaxWheelsQttyTitle1(JLabel MaxWheelsQttyTitle1) {
        this.MaxWheelsQttyTitle1 = MaxWheelsQttyTitle1;
    }

    public JPanel getMenu() {
        return Menu;
    }

    public void setMenu(JPanel Menu) {
        this.Menu = Menu;
    }

    public void setMenuBar(JPanel MenuBar) {
        this.MenuBar = MenuBar;
    }

    public JLabel getMotorsEmployeeQtty() {
        return MotorsEmployeeQtty;
    }

    public void setMotorsEmployeeQtty(JLabel MotorsEmployeeQtty) {
        this.MotorsEmployeeQtty = MotorsEmployeeQtty;
    }

    public JLabel getMotorsEmployeeQtty1() {
        return MotorsEmployeeQtty1;
    }

    public void setMotorsEmployeeQtty1(JLabel MotorsEmployeeQtty1) {
        this.MotorsEmployeeQtty1 = MotorsEmployeeQtty1;
    }

    public JButton getMotorsEmployeeQttyLess() {
        return MotorsEmployeeQttyLess;
    }

    public void setMotorsEmployeeQttyLess(JButton MotorsEmployeeQttyLess) {
        this.MotorsEmployeeQttyLess = MotorsEmployeeQttyLess;
    }

    public JButton getMotorsEmployeeQttyLess1() {
        return MotorsEmployeeQttyLess1;
    }

    public void setMotorsEmployeeQttyLess1(JButton MotorsEmployeeQttyLess1) {
        this.MotorsEmployeeQttyLess1 = MotorsEmployeeQttyLess1;
    }

    public JButton getMotorsEmployeeQttyPlus() {
        return MotorsEmployeeQttyPlus;
    }

    public void setMotorsEmployeeQttyPlus(JButton MotorsEmployeeQttyPlus) {
        this.MotorsEmployeeQttyPlus = MotorsEmployeeQttyPlus;
    }

    public JButton getMotorsEmployeeQttyPlus1() {
        return MotorsEmployeeQttyPlus1;
    }

    public void setMotorsEmployeeQttyPlus1(JButton MotorsEmployeeQttyPlus1) {
        this.MotorsEmployeeQttyPlus1 = MotorsEmployeeQttyPlus1;
    }

    public JLabel getMotorsEmployeeQttyTitle() {
        return MotorsEmployeeQttyTitle;
    }

    public void setMotorsEmployeeQttyTitle(JLabel MotorsEmployeeQttyTitle) {
        this.MotorsEmployeeQttyTitle = MotorsEmployeeQttyTitle;
    }

    public JLabel getMotorsEmployeeQttyTitle1() {
        return MotorsEmployeeQttyTitle1;
    }

    public void setMotorsEmployeeQttyTitle1(JLabel MotorsEmployeeQttyTitle1) {
        this.MotorsEmployeeQttyTitle1 = MotorsEmployeeQttyTitle1;
    }

    public JLabel getMotorsQtty() {
        return MotorsQtty;
    }

    public void setMotorsQtty(JLabel MotorsQtty) {
        this.MotorsQtty = MotorsQtty;
    }

    public JLabel getMotorsQtty1() {
        return MotorsQtty1;
    }

    public void setMotorsQtty1(JLabel MotorsQtty1) {
        this.MotorsQtty1 = MotorsQtty1;
    }

    public JLabel getMotorsQttyTitles() {
        return MotorsQttyTitles;
    }

    public void setMotorsQttyTitles(JLabel MotorsQttyTitles) {
        this.MotorsQttyTitles = MotorsQttyTitles;
    }

    public JLabel getMotorsQttyTitles1() {
        return MotorsQttyTitles1;
    }

    public void setMotorsQttyTitles1(JLabel MotorsQttyTitles1) {
        this.MotorsQttyTitles1 = MotorsQttyTitles1;
    }

    public JLabel getOpManagerFaults() {
        return OpManagerFaults;
    }

    public void setOpManagerFaults(JLabel OpManagerFaults) {
        this.OpManagerFaults = OpManagerFaults;
    }

    public JLabel getOpManagerFaults1() {
        return OpManagerFaults1;
    }

    public void setOpManagerFaults1(JLabel OpManagerFaults1) {
        this.OpManagerFaults1 = OpManagerFaults1;
    }

    public JLabel getOpManagerFaultsTitle() {
        return OpManagerFaultsTitle;
    }

    public void setOpManagerFaultsTitle(JLabel OpManagerFaultsTitle) {
        this.OpManagerFaultsTitle = OpManagerFaultsTitle;
    }

    public JLabel getOpManagerFaultsTitle1() {
        return OpManagerFaultsTitle1;
    }

    public void setOpManagerFaultsTitle1(JLabel OpManagerFaultsTitle1) {
        this.OpManagerFaultsTitle1 = OpManagerFaultsTitle1;
    }

    public JLabel getOpManagerJob() {
        return OpManagerJob;
    }

    public void setOpManagerJob(JLabel OpManagerJob) {
        this.OpManagerJob = OpManagerJob;
    }

    public JLabel getOpManagerJob1() {
        return OpManagerJob1;
    }

    public void setOpManagerJob1(JLabel OpManagerJob1) {
        this.OpManagerJob1 = OpManagerJob1;
    }

    public JLabel getOpManagerJobTitle() {
        return OpManagerJobTitle;
    }

    public void setOpManagerJobTitle(JLabel OpManagerJobTitle) {
        this.OpManagerJobTitle = OpManagerJobTitle;
    }

    public JLabel getOpManagerJobTitle1() {
        return OpManagerJobTitle1;
    }

    public void setOpManagerJobTitle1(JLabel OpManagerJobTitle1) {
        this.OpManagerJobTitle1 = OpManagerJobTitle1;
    }

    public JLabel getOpManagerMoneyTaken() {
        return OpManagerMoneyTaken;
    }

    public void setOpManagerMoneyTaken(JLabel OpManagerMoneyTaken) {
        this.OpManagerMoneyTaken = OpManagerMoneyTaken;
    }

    public JLabel getOpManagerMoneyTaken1() {
        return OpManagerMoneyTaken1;
    }

    public void setOpManagerMoneyTaken1(JLabel OpManagerMoneyTaken1) {
        this.OpManagerMoneyTaken1 = OpManagerMoneyTaken1;
    }

    public JLabel getOpManagerMoneyTakenTitle() {
        return OpManagerMoneyTakenTitle;
    }

    public void setOpManagerMoneyTakenTitle(JLabel OpManagerMoneyTakenTitle) {
        this.OpManagerMoneyTakenTitle = OpManagerMoneyTakenTitle;
    }

    public JLabel getOpManagerMoneyTakenTitle1() {
        return OpManagerMoneyTakenTitle1;
    }

    public void setOpManagerMoneyTakenTitle1(JLabel OpManagerMoneyTakenTitle1) {
        this.OpManagerMoneyTakenTitle1 = OpManagerMoneyTakenTitle1;
    }

    public JLabel getOpManagerTitle() {
        return OpManagerTitle;
    }

    public void setOpManagerTitle(JLabel OpManagerTitle) {
        this.OpManagerTitle = OpManagerTitle;
    }

    public JLabel getOpManagerTitle1() {
        return OpManagerTitle1;
    }

    public void setOpManagerTitle1(JLabel OpManagerTitle1) {
        this.OpManagerTitle1 = OpManagerTitle1;
    }

    public JPanel getPanel() {
        return Panel;
    }

    public void setPanel(JPanel Panel) {
        this.Panel = Panel;
    }

    public JLabel getPlantDirectorJob() {
        return PlantDirectorJob;
    }

    public void setPlantDirectorJob(JLabel PlantDirectorJob) {
        this.PlantDirectorJob = PlantDirectorJob;
    }

    public JLabel getPlantDirectorJob1() {
        return PlantDirectorJob1;
    }

    public void setPlantDirectorJob1(JLabel PlantDirectorJob1) {
        this.PlantDirectorJob1 = PlantDirectorJob1;
    }

    public JLabel getPlantDirectorJobTitle() {
        return PlantDirectorJobTitle;
    }

    public void setPlantDirectorJobTitle(JLabel PlantDirectorJobTitle) {
        this.PlantDirectorJobTitle = PlantDirectorJobTitle;
    }

    public JLabel getPlantDirectorJobTitle1() {
        return PlantDirectorJobTitle1;
    }

    public void setPlantDirectorJobTitle1(JLabel PlantDirectorJobTitle1) {
        this.PlantDirectorJobTitle1 = PlantDirectorJobTitle1;
    }

    public JLabel getPlantDirectorTitle() {
        return PlantDirectorTitle;
    }

    public void setPlantDirectorTitle(JLabel PlantDirectorTitle) {
        this.PlantDirectorTitle = PlantDirectorTitle;
    }

    public JLabel getPlantDirectorTitle1() {
        return PlantDirectorTitle1;
    }

    public void setPlantDirectorTitle1(JLabel PlantDirectorTitle1) {
        this.PlantDirectorTitle1 = PlantDirectorTitle1;
    }

    public JLabel getPlantTitle() {
        return PlantTitle;
    }

    public void setPlantTitle(JLabel PlantTitle) {
        this.PlantTitle = PlantTitle;
    }

    public JLabel getPlantTitle1() {
        return PlantTitle1;
    }

    public void setPlantTitle1(JLabel PlantTitle1) {
        this.PlantTitle1 = PlantTitle1;
    }

    public JButton getRunSim1() {
        return RunSim1;
    }

    public void setRunSim1(JButton RunSim1) {
        this.RunSim1 = RunSim1;
    }

    public JLabel getStandardCarsQtty() {
        return StandardCarsQtty;
    }

    public void setStandardCarsQtty(JLabel StandardCarsQtty) {
        this.StandardCarsQtty = StandardCarsQtty;
    }

    public JLabel getStandardCarsQtty1() {
        return StandardCarsQtty1;
    }

    public void setStandardCarsQtty1(JLabel StandardCarsQtty1) {
        this.StandardCarsQtty1 = StandardCarsQtty1;
    }
    
    public JLabel getStandardCarsQttyTitle() {
        return StandardCarsQttyTitle;
    }

    public void setStandardCarsQttyTitle(JLabel StandardCarsQttyTitle) {
        this.StandardCarsQttyTitle = StandardCarsQttyTitle;
    }

    public JLabel getStandardCarsQttyTitle1() {
        return AccessoryCarsAvailableTitle1;
    }

    public void setStandardCarsQttyTitle1(JLabel StandardCarsQttyTitle1) {
        this.AccessoryCarsAvailableTitle1 = StandardCarsQttyTitle1;
    }

    public JButton getStopSim1() {
        return StopSim1;
    }

    public void setStopSim1(JButton StopSim1) {
        this.StopSim1 = StopSim1;
    }

    public JTabbedPane getTabbedPane() {
        return TabbedPane;
    }

    public void setTabbedPane(JTabbedPane TabbedPane) {
        this.TabbedPane = TabbedPane;
    }

    public JPanel getTitleProject() {
        return TitleProject;
    }

    public void setTitleProject(JPanel TitleProject) {
        this.TitleProject = TitleProject;
    }

    public JLabel getWarehouseTitle1() {
        return WarehouseTitle1;
    }

    public void setWarehouseTitle1(JLabel WarehouseTitle1) {
        this.WarehouseTitle1 = WarehouseTitle1;
    }

    public JLabel getWarehouseTitle2() {
        return WarehouseTitle2;
    }

    public void setWarehouseTitle2(JLabel WarehouseTitle2) {
        this.WarehouseTitle2 = WarehouseTitle2;
    }

    public JLabel getWheelsEmployeeQtty() {
        return WheelsEmployeeQtty;
    }

    public void setWheelsEmployeeQtty(JLabel WheelsEmployeeQtty) {
        this.WheelsEmployeeQtty = WheelsEmployeeQtty;
    }

    public JLabel getWheelsEmployeeQtty1() {
        return WheelsEmployeeQtty1;
    }

    public void setWheelsEmployeeQtty1(JLabel WheelsEmployeeQtty1) {
        this.WheelsEmployeeQtty1 = WheelsEmployeeQtty1;
    }

    public JButton getWheelsEmployeeQttyLess() {
        return WheelsEmployeeQttyLess;
    }

    public void setWheelsEmployeeQttyLess(JButton WheelsEmployeeQttyLess) {
        this.WheelsEmployeeQttyLess = WheelsEmployeeQttyLess;
    }

    public JButton getWheelsEmployeeQttyLess1() {
        return WheelsEmployeeQttyLess1;
    }

    public void setWheelsEmployeeQttyLess1(JButton WheelsEmployeeQttyLess1) {
        this.WheelsEmployeeQttyLess1 = WheelsEmployeeQttyLess1;
    }

    public JButton getWheelsEmployeeQttyPlus() {
        return WheelsEmployeeQttyPlus;
    }

    public void setWheelsEmployeeQttyPlus(JButton WheelsEmployeeQttyPlus) {
        this.WheelsEmployeeQttyPlus = WheelsEmployeeQttyPlus;
    }

    public JButton getWheelsEmployeeQttyPlus1() {
        return WheelsEmployeeQttyPlus1;
    }

    public void setWheelsEmployeeQttyPlus1(JButton WheelsEmployeeQttyPlus1) {
        this.WheelsEmployeeQttyPlus1 = WheelsEmployeeQttyPlus1;
    }

    public JLabel getWheelsEmployeeQttyTitle() {
        return WheelsEmployeeQttyTitle;
    }

    public void setWheelsEmployeeQttyTitle(JLabel WheelsEmployeeQttyTitle) {
        this.WheelsEmployeeQttyTitle = WheelsEmployeeQttyTitle;
    }

    public JLabel getWheelsEmployeeQttyTitle1() {
        return WheelsEmployeeQttyTitle1;
    }

    public void setWheelsEmployeeQttyTitle1(JLabel WheelsEmployeeQttyTitle1) {
        this.WheelsEmployeeQttyTitle1 = WheelsEmployeeQttyTitle1;
    }

    public JLabel getWheelsQtty() {
        return WheelsQtty;
    }

    public void setWheelsQtty(JLabel WheelsQtty) {
        this.WheelsQtty = WheelsQtty;
    }

    public JLabel getWheelsQtty1() {
        return WheelsQtty1;
    }

    public void setWheelsQtty1(JLabel WheelsQtty1) {
        this.WheelsQtty1 = WheelsQtty1;
    }

    public JLabel getWheelsQttyTitle() {
        return WheelsQttyTitle;
    }

    public void setWheelsQttyTitle(JLabel WheelsQttyTitle) {
        this.WheelsQttyTitle = WheelsQttyTitle;
    }

    public JLabel getWheelsQttyTitle1() {
        return WheelsQttyTitle1;
    }

    public void setWheelsQttyTitle1(JLabel WheelsQttyTitle1) {
        this.WheelsQttyTitle1 = WheelsQttyTitle1;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public void setjPanel3(JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    public JPanel getjPanel7() {
        return jPanel7;
    }

    public void setjPanel7(JPanel jPanel7) {
        this.jPanel7 = jPanel7;
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
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TabbedPane = new javax.swing.JTabbedPane();
        Menu = new javax.swing.JPanel();
        Bugatti = new javax.swing.JPanel();
        ChasisEmployeeQtty = new javax.swing.JLabel();
        AssemblerEmployeeQttyTitle = new javax.swing.JLabel();
        EmployeeQtty = new javax.swing.JLabel();
        ChasisEmployeeQttyLess = new javax.swing.JButton();
        ChasisEmployeeQttyPlus = new javax.swing.JButton();
        BodyworksEmployeeQtty = new javax.swing.JLabel();
        BodyworksEmployeeQttyLess = new javax.swing.JButton();
        BodyworksEmployeeQttyPlus = new javax.swing.JButton();
        MotorsEmployeeQtty = new javax.swing.JLabel();
        MotorsEmployeeQttyLess = new javax.swing.JButton();
        MotorsEmployeeQttyPlus = new javax.swing.JButton();
        WheelsEmployeeQtty = new javax.swing.JLabel();
        WheelsEmployeeQttyLess = new javax.swing.JButton();
        WheelsEmployeeQttyPlus = new javax.swing.JButton();
        AccessoriesEmployeeQtty = new javax.swing.JLabel();
        AccessoriesEmployeeQttyLess = new javax.swing.JButton();
        AccessoriesEmployeeQttyPlus = new javax.swing.JButton();
        AssemblerEmployeeQtty = new javax.swing.JLabel();
        AssemblerEmployeeQttyLess = new javax.swing.JButton();
        AssemblerEmployeeQttyPlus = new javax.swing.JButton();
        EmployeeQttyTitle = new javax.swing.JLabel();
        BodyworksEmployeeQttyTitle = new javax.swing.JLabel();
        MotorsEmployeeQttyTitle = new javax.swing.JLabel();
        WheelsEmployeeQttyTitle = new javax.swing.JLabel();
        AccessoriesEmployeeQttyTitle = new javax.swing.JLabel();
        PlantDirectorJob = new javax.swing.JLabel();
        ChasisQtty = new javax.swing.JLabel();
        StandardCarsQttyTitle = new javax.swing.JLabel();
        BodyworksQtty = new javax.swing.JLabel();
        MotorsQtty = new javax.swing.JLabel();
        WheelsQtty = new javax.swing.JLabel();
        AccessoriesQtty = new javax.swing.JLabel();
        StandardCarsQtty = new javax.swing.JLabel();
        BodyworksQttyTitle = new javax.swing.JLabel();
        MotorsQttyTitles = new javax.swing.JLabel();
        WheelsQttyTitle = new javax.swing.JLabel();
        AccessoriesQttyTitle = new javax.swing.JLabel();
        MaxAccessoriesQttyTitle = new javax.swing.JLabel();
        AccessoriesCarsQttyTitle = new javax.swing.JLabel();
        AccessoriesCarsQtty = new javax.swing.JLabel();
        ChasisQttyTitle = new javax.swing.JLabel();
        MaxChasisQttyTitle = new javax.swing.JLabel();
        MaxBodyworksQttyTitle = new javax.swing.JLabel();
        MaxMotorsQttyTitles = new javax.swing.JLabel();
        MaxWheelsQttyTitle = new javax.swing.JLabel();
        MaxChasisQtty = new javax.swing.JLabel();
        MaxBodyworksQtty = new javax.swing.JLabel();
        MaxMotorsQtty = new javax.swing.JLabel();
        MaxWheelsQtty = new javax.swing.JLabel();
        MaxAccessoriesQtty = new javax.swing.JLabel();
        DaysLeft = new javax.swing.JLabel();
        PlantTitle = new javax.swing.JLabel();
        DaysLeftTitle = new javax.swing.JLabel();
        OpManagerTitle = new javax.swing.JLabel();
        EmployeeTitle = new javax.swing.JLabel();
        PlantDirectorTitle = new javax.swing.JLabel();
        ChasisEmployeeQttyTitle = new javax.swing.JLabel();
        PlantDirectorJobTitle = new javax.swing.JLabel();
        OpManagerJob = new javax.swing.JLabel();
        OpManagerJobTitle = new javax.swing.JLabel();
        OpManagerFaults = new javax.swing.JLabel();
        OpManagerFaultsTitle = new javax.swing.JLabel();
        OpManagerMoneyTaken = new javax.swing.JLabel();
        OpManagerMoneyTakenTitle = new javax.swing.JLabel();
        WarehouseTitle1 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleProject.setBackground(new java.awt.Color(51, 51, 51));
        TitleProject.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Proyecto 1 Sistemas Operativos");
        TitleProject.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 280, 40));

        Panel.add(TitleProject, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 40));

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
        jLabel2.setText("Menu");
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

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Planta Maserati");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuBar.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        Panel.add(MenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 540));

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1025, Short.MAX_VALUE)
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        TabbedPane.addTab("tab1", Menu);

        Bugatti.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChasisEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQtty.setText("1");
        Bugatti.add(ChasisEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, 50, 30));

        AssemblerEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQttyTitle.setText("Empleados en ens.");
        Bugatti.add(AssemblerEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 160, 30));

        EmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeQtty.setText("0");
        Bugatti.add(EmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 150, 50, 30));

        ChasisEmployeeQttyLess.setText("-");
        ChasisEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(ChasisEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 50, 30));

        ChasisEmployeeQttyPlus.setText("+");
        ChasisEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChasisEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(ChasisEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 220, 50, 30));

        BodyworksEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQtty.setText("1");
        Bugatti.add(BodyworksEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 270, 50, 30));

        BodyworksEmployeeQttyLess.setText("-");
        BodyworksEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(BodyworksEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 50, 30));

        BodyworksEmployeeQttyPlus.setText("+");
        BodyworksEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BodyworksEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(BodyworksEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 270, 50, 30));

        MotorsEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQtty.setText("1");
        Bugatti.add(MotorsEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, 50, 30));

        MotorsEmployeeQttyLess.setText("-");
        MotorsEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(MotorsEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, 50, 30));

        MotorsEmployeeQttyPlus.setText("+");
        MotorsEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MotorsEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(MotorsEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 320, 50, 30));

        WheelsEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQtty.setText("1");
        Bugatti.add(WheelsEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 370, 50, 30));

        WheelsEmployeeQttyLess.setText("-");
        WheelsEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(WheelsEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, 50, 30));

        WheelsEmployeeQttyPlus.setText("+");
        WheelsEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WheelsEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(WheelsEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 370, 50, 30));

        AccessoriesEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQtty.setText("1");
        Bugatti.add(AccessoriesEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 420, 50, 30));

        AccessoriesEmployeeQttyLess.setText("-");
        AccessoriesEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(AccessoriesEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 420, 50, 30));

        AccessoriesEmployeeQttyPlus.setText("+");
        AccessoriesEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessoriesEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(AccessoriesEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 420, 50, 30));

        AssemblerEmployeeQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AssemblerEmployeeQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AssemblerEmployeeQtty.setText("1");
        Bugatti.add(AssemblerEmployeeQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 470, 50, 30));

        AssemblerEmployeeQttyLess.setText("-");
        AssemblerEmployeeQttyLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyLessActionPerformed(evt);
            }
        });
        Bugatti.add(AssemblerEmployeeQttyLess, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 470, 50, 30));

        AssemblerEmployeeQttyPlus.setText("+");
        AssemblerEmployeeQttyPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssemblerEmployeeQttyPlusActionPerformed(evt);
            }
        });
        Bugatti.add(AssemblerEmployeeQttyPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 470, 50, 30));

        EmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        EmployeeQttyTitle.setText("Cantidad de empleados:");
        Bugatti.add(EmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 150, 30));

        BodyworksEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksEmployeeQttyTitle.setText("Empleados en carrocería");
        Bugatti.add(BodyworksEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 160, 30));

        MotorsEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsEmployeeQttyTitle.setText("Empleados en motor");
        Bugatti.add(MotorsEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, 160, 30));

        WheelsEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsEmployeeQttyTitle.setText("Empleados en ruedas");
        Bugatti.add(WheelsEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 160, 30));

        AccessoriesEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesEmployeeQttyTitle.setText("Empleados en accesorios");
        Bugatti.add(AccessoriesEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, 160, 30));

        PlantDirectorJob.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PlantDirectorJob.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJob.setText("Trabajando");
        Bugatti.add(PlantDirectorJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 240, 30));

        ChasisQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisQtty.setText("1");
        Bugatti.add(ChasisQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 50, 30));

        StandardCarsQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StandardCarsQttyTitle.setText("Carros estándar:");
        Bugatti.add(StandardCarsQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 150, 30));

        BodyworksQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BodyworksQtty.setText("1");
        Bugatti.add(BodyworksQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 50, 30));

        MotorsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotorsQtty.setText("1");
        Bugatti.add(MotorsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 50, 30));

        WheelsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WheelsQtty.setText("1");
        Bugatti.add(WheelsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 50, 30));

        AccessoriesQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesQtty.setText("1");
        Bugatti.add(AccessoriesQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 50, 30));

        StandardCarsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StandardCarsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StandardCarsQtty.setText("1");
        Bugatti.add(StandardCarsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 50, 30));

        BodyworksQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BodyworksQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        BodyworksQttyTitle.setText("Carrocerías:");
        Bugatti.add(BodyworksQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 80, 30));

        MotorsQttyTitles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MotorsQttyTitles.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MotorsQttyTitles.setText("Motores:");
        Bugatti.add(MotorsQttyTitles, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 80, 30));

        WheelsQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        WheelsQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        WheelsQttyTitle.setText("Ruedas:");
        Bugatti.add(WheelsQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 80, 30));

        AccessoriesQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesQttyTitle.setText("Accesorios:");
        Bugatti.add(AccessoriesQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 80, 30));

        MaxAccessoriesQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxAccessoriesQttyTitle.setText("Máx.");
        Bugatti.add(MaxAccessoriesQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 40, 30));

        AccessoriesCarsQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        AccessoriesCarsQttyTitle.setText("Carros con accesorios:");
        Bugatti.add(AccessoriesCarsQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 150, 30));

        AccessoriesCarsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AccessoriesCarsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccessoriesCarsQtty.setText("1");
        Bugatti.add(AccessoriesCarsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 50, 30));

        ChasisQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ChasisQttyTitle.setText("Chasis:");
        Bugatti.add(ChasisQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        MaxChasisQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxChasisQttyTitle.setText("Máx.");
        Bugatti.add(MaxChasisQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 40, 30));

        MaxBodyworksQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxBodyworksQttyTitle.setText("Máx.");
        Bugatti.add(MaxBodyworksQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 40, 30));

        MaxMotorsQttyTitles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQttyTitles.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxMotorsQttyTitles.setText("Máx.");
        Bugatti.add(MaxMotorsQttyTitles, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 40, 30));

        MaxWheelsQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        MaxWheelsQttyTitle.setText("Máx.");
        Bugatti.add(MaxWheelsQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 40, 30));

        MaxChasisQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxChasisQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxChasisQtty.setText("1");
        Bugatti.add(MaxChasisQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 50, 30));

        MaxBodyworksQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxBodyworksQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxBodyworksQtty.setText("1");
        Bugatti.add(MaxBodyworksQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 50, 30));

        MaxMotorsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxMotorsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxMotorsQtty.setText("1");
        Bugatti.add(MaxMotorsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 50, 30));

        MaxWheelsQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxWheelsQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxWheelsQtty.setText("1");
        Bugatti.add(MaxWheelsQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 50, 30));

        MaxAccessoriesQtty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MaxAccessoriesQtty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxAccessoriesQtty.setText("1");
        Bugatti.add(MaxAccessoriesQtty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 50, 30));

        DaysLeft.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DaysLeft.setText("0");
        Bugatti.add(DaysLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 60, 50));

        PlantTitle.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        PlantTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PlantTitle.setText("Bugatti");
        Bugatti.add(PlantTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, 60));

        DaysLeftTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DaysLeftTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        DaysLeftTitle.setText("Días para la entrega:");
        Bugatti.add(DaysLeftTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 200, 50));

        OpManagerTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        OpManagerTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerTitle.setText("Gerente de operaciones");
        Bugatti.add(OpManagerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 350, 50));

        EmployeeTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        EmployeeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmployeeTitle.setText("Empleados");
        Bugatti.add(EmployeeTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 350, 50));

        PlantDirectorTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PlantDirectorTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorTitle.setText("Director de la planta");
        Bugatti.add(PlantDirectorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 350, 50));

        ChasisEmployeeQttyTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChasisEmployeeQttyTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChasisEmployeeQttyTitle.setText("Empleados en chasis");
        Bugatti.add(ChasisEmployeeQttyTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 220, 160, 30));

        PlantDirectorJobTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PlantDirectorJobTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlantDirectorJobTitle.setText("¿Qué está haciendo?");
        Bugatti.add(PlantDirectorJobTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 240, 30));

        OpManagerJob.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerJob.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJob.setText("Trabajando");
        Bugatti.add(OpManagerJob, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 240, 30));

        OpManagerJobTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        OpManagerJobTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerJobTitle.setText("¿Qué está haciendo?");
        Bugatti.add(OpManagerJobTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 240, 30));

        OpManagerFaults.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaults.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerFaults.setText("0");
        Bugatti.add(OpManagerFaults, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 40, 30));

        OpManagerFaultsTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerFaultsTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerFaultsTitle.setText("Faltas:");
        Bugatti.add(OpManagerFaultsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 150, 30));

        OpManagerMoneyTaken.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTaken.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpManagerMoneyTaken.setText("0");
        Bugatti.add(OpManagerMoneyTaken, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 110, 30));

        OpManagerMoneyTakenTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        OpManagerMoneyTakenTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        OpManagerMoneyTakenTitle.setText("Dinero descontado:");
        Bugatti.add(OpManagerMoneyTakenTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 150, 30));

        WarehouseTitle1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        WarehouseTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WarehouseTitle1.setText("Almacén");
        Bugatti.add(WarehouseTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 280, 50));

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
        EmployeeQtty1.setText(String.valueOf(this.maserati.getMaxEmployees()-6));
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

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        TabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void ChasisEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyLessActionPerformed
        
    }//GEN-LAST:event_ChasisEmployeeQttyLessActionPerformed

    private void ChasisEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChasisEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChasisEmployeeQttyPlusActionPerformed

    private void BodyworksEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BodyworksEmployeeQttyLessActionPerformed

    private void BodyworksEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BodyworksEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BodyworksEmployeeQttyPlusActionPerformed

    private void MotorsEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotorsEmployeeQttyLessActionPerformed

    private void MotorsEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorsEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotorsEmployeeQttyPlusActionPerformed

    private void WheelsEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WheelsEmployeeQttyLessActionPerformed

    private void WheelsEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WheelsEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WheelsEmployeeQttyPlusActionPerformed

    private void AccessoriesEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccessoriesEmployeeQttyLessActionPerformed

    private void AccessoriesEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccessoriesEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccessoriesEmployeeQttyPlusActionPerformed

    private void AssemblerEmployeeQttyLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyLessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AssemblerEmployeeQttyLessActionPerformed

    private void AssemblerEmployeeQttyPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssemblerEmployeeQttyPlusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AssemblerEmployeeQttyPlusActionPerformed

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
    private javax.swing.JLabel AccessoriesCarsQtty;
    private javax.swing.JLabel AccessoriesCarsQtty1;
    private javax.swing.JLabel AccessoriesCarsQttyTitle;
    private javax.swing.JLabel AccessoriesCarsQttyTitle1;
    private javax.swing.JLabel AccessoriesEmployeeQtty;
    private javax.swing.JLabel AccessoriesEmployeeQtty1;
    private javax.swing.JButton AccessoriesEmployeeQttyLess;
    private javax.swing.JButton AccessoriesEmployeeQttyLess1;
    private javax.swing.JButton AccessoriesEmployeeQttyPlus;
    private javax.swing.JButton AccessoriesEmployeeQttyPlus1;
    private javax.swing.JLabel AccessoriesEmployeeQttyTitle;
    private javax.swing.JLabel AccessoriesEmployeeQttyTitle1;
    private javax.swing.JLabel AccessoriesQtty;
    private javax.swing.JLabel AccessoriesQtty1;
    private javax.swing.JLabel AccessoriesQttyTitle;
    private javax.swing.JLabel AccessoriesQttyTitle1;
    private javax.swing.JLabel AccessoryCarsAvailable1;
    private javax.swing.JLabel AccessoryCarsAvailableTitle1;
    private javax.swing.JLabel AssemblerEmployeeQtty;
    private javax.swing.JLabel AssemblerEmployeeQtty1;
    private javax.swing.JButton AssemblerEmployeeQttyLess;
    private javax.swing.JButton AssemblerEmployeeQttyLess1;
    private javax.swing.JButton AssemblerEmployeeQttyPlus;
    private javax.swing.JButton AssemblerEmployeeQttyPlus1;
    private javax.swing.JLabel AssemblerEmployeeQttyTitle;
    private javax.swing.JLabel AssemblerEmployeeQttyTitle1;
    private javax.swing.JLabel BodyworksEmployeeQtty;
    private javax.swing.JLabel BodyworksEmployeeQtty1;
    private javax.swing.JButton BodyworksEmployeeQttyLess;
    private javax.swing.JButton BodyworksEmployeeQttyLess1;
    private javax.swing.JButton BodyworksEmployeeQttyPlus;
    private javax.swing.JButton BodyworksEmployeeQttyPlus1;
    private javax.swing.JLabel BodyworksEmployeeQttyTitle;
    private javax.swing.JLabel BodyworksEmployeeQttyTitle1;
    private javax.swing.JLabel BodyworksQtty;
    private javax.swing.JLabel BodyworksQtty1;
    private javax.swing.JLabel BodyworksQttyTitle;
    private javax.swing.JLabel BodyworksQttyTitle1;
    private javax.swing.JPanel Bugatti;
    private javax.swing.JLabel ChasisEmployeeQtty;
    private javax.swing.JLabel ChasisEmployeeQtty1;
    private javax.swing.JButton ChasisEmployeeQttyLess;
    private javax.swing.JButton ChasisEmployeeQttyLess1;
    private javax.swing.JButton ChasisEmployeeQttyPlus;
    private javax.swing.JButton ChasisEmployeeQttyPlus1;
    private javax.swing.JLabel ChasisEmployeeQttyTitle;
    private javax.swing.JLabel ChasisEmployeeQttyTitle1;
    private javax.swing.JLabel ChasisQtty;
    private javax.swing.JLabel ChasisQtty1;
    private javax.swing.JLabel ChasisQttyTitle;
    private javax.swing.JLabel ChasisQttyTitle1;
    private javax.swing.JLabel DaysLeft;
    private javax.swing.JLabel DaysLeft1;
    private javax.swing.JLabel DaysLeftTitle;
    private javax.swing.JLabel DaysLeftTitle1;
    private javax.swing.JLabel EmployeeQtty;
    private javax.swing.JLabel EmployeeQtty1;
    private javax.swing.JLabel EmployeeQttyTitle;
    private javax.swing.JLabel EmployeeQttyTitle1;
    private javax.swing.JLabel EmployeeTitle;
    private javax.swing.JLabel EmployeeTitle1;
    private javax.swing.JLabel GrossIncomeTittle1;
    private javax.swing.JLabel GrossIncomeValue1;
    private javax.swing.JPanel Maserati;
    private javax.swing.JLabel MaxAccessoriesQtty;
    private javax.swing.JLabel MaxAccessoriesQtty1;
    private javax.swing.JLabel MaxAccessoriesQttyTitle;
    private javax.swing.JLabel MaxAccessoriesQttyTitle1;
    private javax.swing.JLabel MaxBodyworksQtty;
    private javax.swing.JLabel MaxBodyworksQtty1;
    private javax.swing.JLabel MaxBodyworksQttyTitle;
    private javax.swing.JLabel MaxBodyworksQttyTitle1;
    private javax.swing.JLabel MaxChasisQtty;
    private javax.swing.JLabel MaxChasisQtty1;
    private javax.swing.JLabel MaxChasisQttyTitle;
    private javax.swing.JLabel MaxChasisQttyTitle1;
    private javax.swing.JLabel MaxMotorsQtty;
    private javax.swing.JLabel MaxMotorsQtty1;
    private javax.swing.JLabel MaxMotorsQttyTitles;
    private javax.swing.JLabel MaxMotorsQttyTitles1;
    private javax.swing.JLabel MaxWheelsQtty;
    private javax.swing.JLabel MaxWheelsQtty1;
    private javax.swing.JLabel MaxWheelsQttyTitle;
    private javax.swing.JLabel MaxWheelsQttyTitle1;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel MenuBar;
    private javax.swing.JLabel MotorsEmployeeQtty;
    private javax.swing.JLabel MotorsEmployeeQtty1;
    private javax.swing.JButton MotorsEmployeeQttyLess;
    private javax.swing.JButton MotorsEmployeeQttyLess1;
    private javax.swing.JButton MotorsEmployeeQttyPlus;
    private javax.swing.JButton MotorsEmployeeQttyPlus1;
    private javax.swing.JLabel MotorsEmployeeQttyTitle;
    private javax.swing.JLabel MotorsEmployeeQttyTitle1;
    private javax.swing.JLabel MotorsQtty;
    private javax.swing.JLabel MotorsQtty1;
    private javax.swing.JLabel MotorsQttyTitles;
    private javax.swing.JLabel MotorsQttyTitles1;
    private javax.swing.JLabel NetIncomeTittle1;
    private javax.swing.JLabel NetIncomeValue1;
    private javax.swing.JLabel OpManagerFaults;
    private javax.swing.JLabel OpManagerFaults1;
    private javax.swing.JLabel OpManagerFaultsTitle;
    private javax.swing.JLabel OpManagerFaultsTitle1;
    private javax.swing.JLabel OpManagerJob;
    private javax.swing.JLabel OpManagerJob1;
    private javax.swing.JLabel OpManagerJobTitle;
    private javax.swing.JLabel OpManagerJobTitle1;
    private javax.swing.JLabel OpManagerMoneyTaken;
    private javax.swing.JLabel OpManagerMoneyTaken1;
    private javax.swing.JLabel OpManagerMoneyTakenTitle;
    private javax.swing.JLabel OpManagerMoneyTakenTitle1;
    private javax.swing.JLabel OpManagerTitle;
    private javax.swing.JLabel OpManagerTitle1;
    private javax.swing.JLabel OperativeCosts1;
    private javax.swing.JLabel OperativeCostsValue1;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel PlantDirectorJob;
    private javax.swing.JLabel PlantDirectorJob1;
    private javax.swing.JLabel PlantDirectorJobTitle;
    private javax.swing.JLabel PlantDirectorJobTitle1;
    private javax.swing.JLabel PlantDirectorTitle;
    private javax.swing.JLabel PlantDirectorTitle1;
    private javax.swing.JLabel PlantTitle;
    private javax.swing.JLabel PlantTitle1;
    private javax.swing.JButton RunSim1;
    private javax.swing.JLabel StandardCarsAvailable1;
    private javax.swing.JLabel StandardCarsAvailableTitle1;
    private javax.swing.JLabel StandardCarsQtty;
    private javax.swing.JLabel StandardCarsQtty1;
    private javax.swing.JLabel StandardCarsQttyTitle;
    private javax.swing.JLabel StandardCarsQttyTitle1;
    private javax.swing.JButton StopSim1;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel TitleProject;
    private javax.swing.JLabel TotalDays1;
    private javax.swing.JLabel TotalDaysQtty1;
    private javax.swing.JLabel WarehouseTitle1;
    private javax.swing.JLabel WarehouseTitle2;
    private javax.swing.JLabel WheelsEmployeeQtty;
    private javax.swing.JLabel WheelsEmployeeQtty1;
    private javax.swing.JButton WheelsEmployeeQttyLess;
    private javax.swing.JButton WheelsEmployeeQttyLess1;
    private javax.swing.JButton WheelsEmployeeQttyPlus;
    private javax.swing.JButton WheelsEmployeeQttyPlus1;
    private javax.swing.JLabel WheelsEmployeeQttyTitle;
    private javax.swing.JLabel WheelsEmployeeQttyTitle1;
    private javax.swing.JLabel WheelsQtty;
    private javax.swing.JLabel WheelsQtty1;
    private javax.swing.JLabel WheelsQttyTitle;
    private javax.swing.JLabel WheelsQttyTitle1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
