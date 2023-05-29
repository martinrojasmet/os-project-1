package Classes;

/**
 *
 * @author samer
 */
public class AccessoryVehicle {
    private int qtyChasisToProduce;
    private int qtyMotorToProduce;
    private int qtyWheelToProduce;
    private int qtyBodyworkToProduce;
    private int qtyAccessoryToProduce;
    private float price;

    public AccessoryVehicle(int qtyChasisToProduce, int qtyMotorToProduce, int qtyWheelToProduce, int qtyBodyworkToProduce, int qtyAccessoryToProduce, float price) {
        this.qtyChasisToProduce = qtyChasisToProduce;
        this.qtyMotorToProduce = qtyMotorToProduce;
        this.qtyWheelToProduce = qtyWheelToProduce;
        this.qtyBodyworkToProduce = qtyBodyworkToProduce;
        this.qtyAccessoryToProduce = qtyAccessoryToProduce;
        this.price = price;
    }
    
    // Getters and setters
    
    public int getQtyChasisToProduce() {
        return qtyChasisToProduce;
    }

    public void setQtyChasisToProduce(int qtyChasisToProduce) {
        this.qtyChasisToProduce = qtyChasisToProduce;
    }

    public int getQtyMotorToProduce() {
        return qtyMotorToProduce;
    }

    public void setQtyMotorToProduce(int qtyMotorToProduce) {
        this.qtyMotorToProduce = qtyMotorToProduce;
    }

    public int getQtyWheelToProduce() {
        return qtyWheelToProduce;
    }

    public void setQtyWheelToProduce(int qtyWheelToProduce) {
        this.qtyWheelToProduce = qtyWheelToProduce;
    }

    public int getQtyBodyworkToProduce() {
        return qtyBodyworkToProduce;
    }

    public void setQtyBodyworkToProduce(int qtyBodyworkToProduce) {
        this.qtyBodyworkToProduce = qtyBodyworkToProduce;
    }

    public int getQtyAccessoryToProduce() {
        return qtyAccessoryToProduce;
    }

    public void setQtyAccessoryToProduce(int qtyAccessoryToProduce) {
        this.qtyAccessoryToProduce = qtyAccessoryToProduce;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
       
}
