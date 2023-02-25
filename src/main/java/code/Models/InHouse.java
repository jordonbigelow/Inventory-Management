package code.Models;

/**
 * This is the class for the InHouse object.
 * It contains data and methods that are specific to this subclass.
 * The part class contains most of the core data.
 */
public class InHouse extends Part{
    /**
     *  This is the machineId
     */
    private int machineId;

    /**
     * This constructs the InHouse Object
     * @param id the id.
     * @param name the name.
     * @param price the price.
     * @param stock the inventory level.
     * @param min the minimum inventory level.
     * @param max the maximum inventory level
     * @param machineId the machineID.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * gets the machineId
     * @return returns the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Sets the machineId
     * @param machineId the machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
