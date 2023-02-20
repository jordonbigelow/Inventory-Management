package code.Controllers;

import code.Models.Inventory;
import code.Models.Part;
import code.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import static code.Models.Inventory.*;
import static java.lang.Integer.parseInt;

public class MainScreenController implements Initializable {
    @FXML
    public TextField partsSearchField;
    @FXML
    public Button exitButton;
    @FXML
    public Button productsDeleteButton;
    @FXML
    public Button productsAddButton;
    @FXML
    public Button productsModifyButton;
    @FXML
    public TextField productsSearchField;
    @FXML
    public TableView productsTable;
    @FXML
    public TableColumn productId;
    @FXML
    public TableColumn productName;
    @FXML
    public TableColumn inventoryList;
    @FXML
    public TableColumn productCostPerUnit;
    @FXML
    public Button partsDeleteButton;
    @FXML
    public Button partsModifyButton;
    @FXML
    public Button partsAddButton;
    @FXML
    public TableView partsTable;
    @FXML
    public TableColumn partId;
    @FXML
    public TableColumn partName;
    @FXML
    public TableColumn inventoryLevel;
    @FXML
    public TableColumn partCostPerUnit;


    public void handleExitButtonAction() {
        System.exit(0);
    }
    @FXML
    public void handleAddPartButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/AddPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root1,600,400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleModifyPartButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/ModifyPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void handleDeletePartButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            return;
        }
        // decrementing here needs to be looked at differently
        // I need a better solution at generating ids
        // this is in a todo
        nextPartsId -= 1;
        Inventory.deletePart(selectedPart);
    }
    public void handleAddProductsButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/AddProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void handleModifyProductsButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/ModifyProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Modify Product");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void handleDeleteProductsButtonAction(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            return;
        }
        Inventory.deleteProduct(selectedProduct);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryList.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
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

    public void handleProductLookup(ActionEvent actionEvent) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        String text = productsSearchField.getText();
        try {
            int id = parseInt(text);
            Product foundProduct = Inventory.lookupProduct(id);

            if (foundProduct != null) {
                foundProducts.add(foundProduct);
                productsTable.setItems(foundProducts);
            }
        } catch (NumberFormatException e) {
            if (!text.isBlank() || !text.isEmpty()) {
                ObservableList<Product> foundProductsList = Inventory.lookupProduct(text);
                productsTable.setItems(foundProductsList);
            } else {
                productsTable.setItems(Inventory.getAllProducts());
            }
        }
    }
}