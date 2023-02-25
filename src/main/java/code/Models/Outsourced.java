package code.Models;

/**
 * This is the class for the Outsourced object.
 * It contains all the data and methods unique to this subclass.
 */
public class Outsourced extends Part{
    private String companyName;

    /**
     * This is the constructor.
     * @param id the id.
     * @param name the name.
     * @param price the price.
     * @param stock the inventory level.
     * @param min the minimum inventory level.
     * @param max the maximum inventory level.
     * @param companyName the company name.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * gets the company name.
     * @return returns the company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * sets the company name.
     * @param companyName the company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
