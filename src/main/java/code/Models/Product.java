package code.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the class for the Product object.
 * It contains all the data and methods needed for the object.
 */
public class Product {
    /**
     * This is the constructor.
     * @param id the id.
     * @param name the name.
     * @param price the price.
     * @param stock the inventory level.
     * @param min the minimum inventory level.
     * @param max the maximum inventory level.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * gets the id.
     * @return returns the id.
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id.
     * @param id the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the name.
     * @return returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name.
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the price.
     * @return returns the price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets the price.
     * @param price the price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets the inventory level.
     * @return returns stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets the inventory level
     * @param stock the stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * gets the minimum inventory level.
     * @return returns the min.
     */
    public int getMin() {
        return min;
    }

    /**
     * sets the minimum inventory level.
     * @param min the min.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * gets the maximum inventory level.
     * @return returns max.
     */
    public int getMax() {
        return max;
    }

    /**
     * sets the maximum inventory level.
     * @param max the max.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * adds an associated part to a product.
     * @param part the part.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * deletes the associated part tied to a product.
     * @param selectedAssociatedPart the selected associated part.
     */
    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * gets the associatedParts observable array list.
     * @return returns the associatedParts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
