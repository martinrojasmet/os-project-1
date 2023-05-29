package Classes;

/**
 *
 * @author samer
 */
public class StandardVehicle {
    private int qtyChasisToProduce;
    private int qtyMotorToProduce;
    private int qtyWheelToProduce;
    private int qtyBodyworkToProduce;
    private float price;

    public StandardVehicle(int qtyChasisToProduce, int qtyMotorToProduce, int qtyWheelToProduce, int qtyBodyworkToProduce, float price) {
        this.qtyChasisToProduce = qtyChasisToProduce;
        this.qtyMotorToProduce = qtyMotorToProduce;
        this.qtyWheelToProduce = qtyWheelToProduce;
        this.qtyBodyworkToProduce = qtyBodyworkToProduce;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
       
}
