package code.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    static {
        addTestData();
    }
    private static void addTestData() {
        Part testPart1 = new InHouse(1, "tire", 29.99, 5, 1, 5, 1);
        Inventory.addPart(testPart1);
        Part testPart2 = new InHouse(2, "seat", 5.99, 25, 1, 25, 2);
        Inventory.addPart(testPart2);
        Product testProduct1 = new Product(1, "bike", 199.99, 10, 1, 10);
        Inventory.addProduct(testProduct1);
        Product testProduct2 = new Product(2, "scooter", 99.99, 10, 1, 10);
        Inventory.addProduct(testProduct2);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static Boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }
}
/**
 * + lookupPart(partName:String):ObservableList<Part>
 * + lookupProduct(productName:String):ObservableList<Product>
 *
 * + updatePart(index:int, selectedPart:Part):void
 * + updateProduct(index:int, newProduct:Product):void
 */
