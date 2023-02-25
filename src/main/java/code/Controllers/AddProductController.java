package code.Controllers;

import code.Models.Inventory;
import code.Models.Part;
import code.Models.Product;
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

import static code.Models.Inventory.nextProductsId;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * This is the Add Product Controller class.
 * This class contains all the data and methods necessary for all the content in the add product window.
 */
public class AddProductController implements Initializable {
    private static ObservableList<Part> addAssociatedParts = FXCollections.observableArrayList();
    public TextField idField;
    public TextField nameField;
    public TextField inventoryField;
    public TextField priceField;
    public TextField minimumField;
    public TextField maximumField;
    public Button saveButton;
    public Button cancelButton;
    public TableView partsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn inventoryLevel;
    public TableColumn partCostPerUnit;
    public TextField partsSearchField;
    public Button addButton;
    public Button removeButton;
    public TableView associatedPartsTable;
    public TableColumn associatedPartId;
    public TableColumn associatedPartName;
    public TableColumn associatedInventoryLevel;
    public TableColumn associatedPartCost;

    /**
     * This is the handler for when the save button is clicked.
     * It creates a Product object storing all the text field data in the variables.
     * Data validation occurs before the insert.
     * It then inserts it into an observable array list.
     * @param actionEvent This is the action that calls the method.
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
                nextProductsId,
                nameField.getText(),
                parseDouble(priceField.getText()),
                parseInt(inventoryField.getText()),
                parseInt(minimumField.getText()),
                parseInt(maximumField.getText())
        );
        nextProductsId += 1;
        Inventory.addProduct(newProduct);

        for (Part part : addAssociatedParts) {
            if (!addAssociatedParts.isEmpty() == false) {
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
     * This is the handler for when then cancel button is clicked.
     * It opens the "Main Screen" window and closes this window.
     * @param actionEvent This is the action that called the method.
     * @throws IOException If an exception occurs, it is thrown.
     */
    public void handleCancelButtonAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,966,361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the initialize method.
     * This method is called when the window opens.
     * It generates the data for the parts table view and associated parts table view.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTable.setItems(addAssociatedParts);

        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This is the handler for when somebody uses the search field for the parts.
     * It searches through the found parts observable array.
     * If it finds a match it changes the content being displayed in the table view.
     * @param actionEvent this is the action that called the method.
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
     * When the add button is clicked, an associated part is added to an observable array list.
     * @param actionEvent This is the action that called the method.
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
        addAssociatedParts.add(selectedPart);
    }

    /**
     * This is the handler for the remove button below the associated parts table.
     * When it is clicked, a confirmation window pops up to confirm the deletion before it occurs.
     * @param actionEvent This is the action that called the method.
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
        if (result.get() == ButtonType.OK) {
            addAssociatedParts.remove(selectedPart);
        }
    }
}
