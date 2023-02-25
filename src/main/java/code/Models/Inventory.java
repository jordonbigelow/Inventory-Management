package code.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static code.Controllers.ModifyPartController.getIndexOfPart;
import static code.Controllers.ModifyProductController.getIndexOfProduct;

/**
 * This class is for the all the parts and products in the program.
 * The data and methods within are for managing the parts and products in the inventory.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds a part that is passed to it into the "allParts" observable array list.
     * @param newPart the part to be added.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method add a product that is passed to it into the "allProducts" observable array list.
     * @param newProduct the product to be added.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method calls the addTestData method when the program loads.
     * It loads all the test data into the program.
     */
    // Test Data
    static {
        addTestData();
    }

    /**
     * This method creates Part and Product objects and adds them to the observable array list to be displayed in the "Main Screen" window.
     */
    // Test Data
    private static void addTestData() {
        Part testPart1 = new InHouse(1, "tire", 29.99, 5, 1, 5, 1);
        Inventory.addPart(testPart1);
        Part testPart2 = new InHouse(2, "seat", 5.99, 25, 1, 25, 2);
        Inventory.addPart(testPart2);
        Part testPart3 = new InHouse(3, "air tube", 15.99, 15, 1, 15, 3);
        Inventory.addPart(testPart3);
        Part testPart4 = new InHouse(4, "bell", 7.99, 10, 1, 10, 4);
        Inventory.addPart(testPart4);
        Part testPart5 = new Outsourced(5, "microchip", 15.99, 15, 1, 15, "testCompany");
        Inventory.addPart(testPart5);
        Part testPart6 = new Outsourced(6, "laptop", 799.99, 10, 1, 10, "testCompany2");
        Inventory.addPart(testPart6);
        Product testProduct1 = new Product(1, "bike", 199.99, 10, 1, 10);
        Inventory.addProduct(testProduct1);
        Product testProduct2 = new Product(2, "scooter", 99.99, 10, 1, 10);
        Inventory.addProduct(testProduct2);
        Product testProduct3 = new Product(3, "tricycle", 79.99, 10, 1, 10);
        Inventory.addProduct(testProduct3);
        Product testProduct4 = new Product(4, "skateboard", 49.99, 10, 1, 10);
        Inventory.addProduct(testProduct4);
    }

    /**
     * This method returns the allParts observable array list.
     * @return returns the array.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method returns the allProducts observable array list.
     * @return returns the array.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * This method removes a part from the observable array list.
     * @param selectedPart the part to be removed.
     * @return returns a boolean if the remove succeeds or fails.
     */
    public static Boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * This method removes a product from the observable array list.
     * @param selectedProduct the product to be removed.
     * @return returns a boolean if the remove succeeds or fails.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * This method returns a part that is being searched for by its ID.
     * @param partId the partId being searched for.
     * @return returns the found part or returns null if it's not found.
     */
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * This method returns a product that is being searched for by its ID.
     * @param productId the productId being searched for.
     * @return returns the found product, or returns null if it's not found.
     */
    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method returns an observable array list that includes found parts.
     * @param partName the partName being searched for.
     * @return returns the observable array list that contains all found parts.
     */
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

    /**
     * This method returns an observable array list that includes found products.
     * @param productName the productName being searched for.
     * @return returns the observable array list that contains all found products.
     */
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

    /**
     * This method is passed an index of a selected part, and the part itself to be inserted.
     * It then inserts the part at the selected index.
     * @param index the index of the selected part to be replaced.
     * @param selectedPart the part that is to replace the selected part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(getIndexOfPart(selectedPart), selectedPart);
    }

    /**
     * This method is passed an index of a selected product, and the product itself to be inserted.
     * @param index the index of the selected product to be replaced.
     * @param newProduct the part that is to replace the selected product.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(getIndexOfProduct(newProduct), newProduct);
    }
    public static int nextPartsId = allParts.size() + 1;
    public static int nextProductsId = allProducts.size() + 1;
}
