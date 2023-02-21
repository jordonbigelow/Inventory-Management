package code.Controllers;

import code.Models.*;
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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

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

    public static int getIndexOfProduct(Product product) {
        int index = 0;
        for (Product currentProduct : currentList) {
            if (product.getId() == currentProduct.getId()) {
                index = currentList.indexOf(currentProduct);
            }
        }
        return index;
    }

    public static void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    public static void setAssociatedPartsList(Product product) {
        currentAssociatedParts = product.getAllAssociatedParts();
    }

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

    public void handleSaveButtonAction(ActionEvent actionEvent) throws IOException {
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

    public void handleCancelButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,966,361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
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
        currentAssociatedParts.add(selectedPart);
    }
    public void handleRemoveButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            return;
        }
        currentAssociatedParts.remove(selectedPart);
    }
}
