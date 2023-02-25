package code.Controllers;

import code.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * This is the class for the Modify Product window.
 * It contains all the data and methods necessary for the Modify Product window.
 */
public class ModifyProductController implements Initializable {
    private static ObservableList<Product> currentList = Inventory.getAllProducts();
    private static ObservableList<Part> currentAssociatedParts = FXCollections.observableArrayList();
    private static Product selectedProduct;
    public TextField idField;
    public TextField nameField;
    public TextField inventoryField;
    public TextField priceField;
    public TextField minimumField;
    public TextField maximumField;
    public Button saveButton;
    public Button cancelButton;
    public TextField partsSearchField;
    public TableView partsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn inventoryLevel;
    public TableColumn partCostPerUnit;
    public Button addButton;
    public TableView associatedPartsTable;
    public TableColumn associatedPartId;
    public TableColumn associatedPartName;
    public TableColumn associatedInventoryLevel;
    public TableColumn associatedPartCost;
    public Button removeButton;

    /**
     * This method returns the index in the observable array list for the product passed to it.
     * @param product the product being passed as an argument.
     * @return this returns the index of the passed product in the array.
     */
    public static int getIndexOfProduct(Product product) {
        int index = 0;
        for (Product currentProduct : currentList) {
            if (product.getId() == currentProduct.getId()) {
                index = currentList.indexOf(currentProduct);
            }
        }
        return index;
    }

    /**
     * This method sets the data for the selected product from the previous "Main screen" window.
     * @param product the product being passed to the method (the selected product from the table view).
     */
    public static void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    /**
     * This method sets the associated parts for the product object.
     * @param product the product being passed to the method (the selected product from the table view).
     */
    public static void setAssociatedPartsList(Product product) {
        currentAssociatedParts = product.getAllAssociatedParts();
    }

    /**
     * This is the initialize method.
     * It sets the data for the parts and associated parts tables.
     * @param url the passed url.
     * @param resourceBundle the passed resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.setText(Integer.toString(selectedProduct.getId()));
        nameField.setText(selectedProduct.getName());
        inventoryField.setText(Integer.toString(selectedProduct.getStock()));
        priceField.setText(Double.toString(selectedProduct.getPrice()));
        minimumField.setText(Integer.toString(selectedProduct.getMin()));
        maximumField.setText(Integer.toString(selectedProduct.getMax()));

        partsTable.setItems(Inventory.getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTable.setItems(currentAssociatedParts);

        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This is the handler for the save button.
     * When the button is clicked a modified product is set at the selected products index with any associated parts as well.
     * Data validation occurs before the object is created.
     * @param actionEvent The action that called the method.
     * @throws IOException If an exception occurs, it is thrown.
     */
    public void handleSaveButtonAction(ActionEvent actionEvent) throws IOException {
        if (nameField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data");
            alert.setContentText("Name cannot be blank.");
            alert.showAndWait();
            return;
        }

        try {
            parseInt(inventoryField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing/Wrong Data");
            alert.setContentText("Inventory must be a number, cannot be blank.");
            alert.showAndWait();
            return;
        }

        try {
            parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            // if given an int it will convert to a double with one digit after the decimal
            // may have to do this differently
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing/Wrong Data");
            alert.setContentText("Price must be a decimal number with two trailing digits (just like money), cannot be blank.");
            alert.showAndWait();
            return;
        }

        try {
            parseInt(minimumField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing/Wrong Data");
            alert.setContentText("Minimum must be a number, cannot be blank.");
            alert.showAndWait();
            return;
        }

        try {
            parseInt(maximumField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing/Wrong Data");
            alert.setContentText("Maximum must be a number, cannot be blank.");
            alert.showAndWait();
            return;
        }

        if (parseInt(minimumField.getText()) > parseInt(maximumField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Value");
            alert.setContentText("Minimum must not be greater than maximum.");
            alert.showAndWait();
            return;
        }

        if (parseInt(inventoryField.getText()) < parseInt(minimumField.getText()) ||
                parseInt(inventoryField.getText()) > parseInt(maximumField.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong Value");
            alert.setContentText("Inventory must not be greater than maximum or less than minimum.");
            alert.showAndWait();
            return;
        }
        Product newProduct = new Product(
                parseInt(idField.getText()),
                nameField.getText(),
                parseDouble(priceField.getText()),
                parseInt(inventoryField.getText()),
                parseInt(minimumField.getText()),
                parseInt(maximumField.getText())
        );
        Inventory.updateProduct(getIndexOfProduct(selectedProduct), newProduct);

        for (Part part : currentAssociatedParts) {
            if (!currentAssociatedParts.isEmpty() == false) {
                return;
            }
            newProduct.addAssociatedPart(part);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,966,361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler for the cancel button.
     * If the cancel button is clicked, the "Main Screen" is opened, and this window is closed.
     * @param actionEvent The action that called the method.
     * @throws IOException If an exception occurs, it is thrown.
     */
    public void handleCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,966,361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler method for the part lookup search field.
     * It will filter the table view based on the search results.
     * @param actionEvent the action that called the method.
     */
    public void handlePartLookup(ActionEvent actionEvent) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        String text = partsSearchField.getText();
        try {
            int id = parseInt(text);
            Part foundPart = Inventory.lookupPart(id);

            if (foundPart != null) {
                foundParts.add(foundPart);
                partsTable.setItems(foundParts);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Data");
                alert.setHeaderText("Can't find");
                alert.setContentText("Cannot find that part.");
                alert.showAndWait();
            }
            return;
        } catch (NumberFormatException e) {
            // Ignore error
        }
        if (!text.isBlank() || !text.isEmpty()) {
            ObservableList<Part> foundPartsList = Inventory.lookupPart(text);
            if (foundPartsList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Data");
                alert.setHeaderText("Can't find");
                alert.setContentText("Cannot find that part.");
                alert.showAndWait();
            } else {
                partsTable.setItems(foundPartsList);
            }
        } else {
            partsTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * This is the handler for the add button.
     * When the add button is clicked, a part object from the parts table is added to the associated parts table.
     * @param actionEvent the action that called the method.
     */
    public void handleAddButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Part");
            alert.setHeaderText("Add Association Error");
            alert.setContentText("Please select a Part.");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        currentAssociatedParts.add(selectedPart);
    }

    /**
     * This is the handler for the remove button.
     * When the remove associated parts button is clicked, the selected part is removed from the current associated parts observable array list.
     * @param actionEvent the action that called the method.
     */
    public void handleRemoveButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Part");
            alert.setHeaderText("Deletion Error");
            alert.setContentText("Please select a Part.");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Deletion Confirmation");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            currentAssociatedParts.remove(selectedPart);
        }
    }
}
