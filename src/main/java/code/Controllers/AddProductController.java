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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static code.Models.Inventory.nextProductsId;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddProductController implements Initializable {
    private static ObservableList<Part> currentList = Inventory.getAllParts();
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

    public void handleSaveButtonAction(ActionEvent actionEvent) throws IOException {
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

    public void handleCancelButtonAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,966,361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }

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

    public void handlePartLookup(ActionEvent actionEvent) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        String text = partsSearchField.getText();
        try {
            int id = parseInt(text);
            Part foundPart = Inventory.lookupPart(id);

            if (foundPart != null) {
                foundParts.add(foundPart);
                partsTable.setItems(foundParts);
            }
        } catch (NumberFormatException e) {
            if (!text.isBlank() || !text.isEmpty()) {
                ObservableList<Part> foundPartsList = Inventory.lookupPart(text);
                partsTable.setItems(foundPartsList);
            } else {
                partsTable.setItems(Inventory.getAllParts());
            }
        }
    }

    public void handleAddButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            return;
        }
        addAssociatedParts.add(selectedPart);
    }

    public void handleRemoveButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            return;
        }
        addAssociatedParts.remove(selectedPart);
    }
}
