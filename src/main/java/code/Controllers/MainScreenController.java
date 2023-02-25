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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static code.Models.Inventory.*;
import static java.lang.Integer.parseInt;

/**
 * This is the class for the Main Screen.
 * All the methods and data in here are related to the tables, fields, and buttons.
 */
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

    /**
     * This method closes the application completely.
     */
    public void handleExitButtonAction() {
        System.exit(0);
    }

    /**
     * This is the method that handles the add part button click.
     * When this button is clicked. It generates the "Add Part" window.
     * RUNTIME ERROR: Location not set.
     * I fixed this error by correcting the path to the "AddPart.fxml".
     * @param event this is the event that calls this method.
     * @throws IOException If the window cannot open an exception is created and reported.
     */
    public void handleAddPartButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/AddPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1,600,400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler for the modify button under the parts table view.
     * When the modify button is clicked, the selected object data will be passed to the "Modify Part" window, and that window is opened.
     * @param event the action that called the method.
     * @throws IOException if an exception occurs, it is thrown.
     */
    @FXML
    public void handleModifyPartButtonAction(ActionEvent event) throws IOException {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Part");
            alert.setHeaderText("Modify Error");
            alert.setContentText("Please select a Part.");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        ModifyPartController.setSelectedPart(selectedPart);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/ModifyPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 600, 400);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler for the add button below the products table view.
     * When the add button is clicked, the "Add Product" window is generated and opened, and the "Main Screen" window is closed.
     * @param actionEvent the action that called the method.
     * @throws IOException if an exception occurs, it is thrown.
     */
    public void handleAddProductsButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/AddProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 850, 550);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler for the modify button under the products table view.
     * When the modify button is clicked, the selected product data is sent to the "Modify Product" window, and the window is opened, and this window is closed.
     * @param actionEvent The action that called this method.
     * @throws IOException If an exception occurs, it is thrown.
     */
    public void handleModifyProductsButtonAction(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Product");
            alert.setHeaderText("Modify Error");
            alert.setContentText("Please select a Product.");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        ModifyProductController.setSelectedProduct(selectedProduct);
        ModifyProductController.setAssociatedPartsList(selectedProduct);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/ModifyProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1, 850, 550);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the handler for the delete button under the parts table view.
     * When the delete button is clicked, a confirmation window appears, if the user clicks ok, the selected part is deleted from the observable array list.
     * @param actionEvent the action that called this method.
     */
    public void handleDeletePartButtonAction(ActionEvent actionEvent) {
        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        // TODO
        // https://code.makery.ch/blog/javafx-dialogs-official/

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
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * This is the handler for the delete button under the products table view.
     * When the delete button is clicked, the method checks if the product has associated parts,
     * if it doesn't, the part is deleted from the observable array list.
     * @param actionEvent The action that called the method.
     * @throws IOException If an exception occurs, it is thrown.
     */
    public void handleDeleteProductsButtonAction(ActionEvent actionEvent) throws IOException {
        Product selectedProduct = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Select a Product");
            alert.setHeaderText("Deletion Error");
            alert.setContentText("Please select a Product.");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }
        if (selectedProduct.getAllAssociatedParts().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Deletion Confirmation");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Inventory.deleteProduct(selectedProduct);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Cannot Delete Product");
            alert.setContentText("You cannot delete a product with an associated part. Please remove the associated Part in the Modify Product window and try again.");
            alert.showAndWait();
        }
    }
    /**
     * This is the initialize method.
     * It populates the parts table view, and product table view.
     * @param url the url.
     * @param resourceBundle the resource bundle.
     */
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

    /**
     * This is the handler for the search field above the parts table view.
     * When this handler is called the table view is updated with the found data, if it doesn't find any, an information window shows up.
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
     * This is the handler for the search field above the product table view.
     * When this method is called, is populates the products table view with the found results.
     * If it finds nothing, an information window appears.
     * @param actionEvent the action that called this method.
     */
    public void handleProductLookup(ActionEvent actionEvent) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        String text = productsSearchField.getText();
        try {
            int id = parseInt(text);
            Product foundProduct = Inventory.lookupProduct(id);

            if (foundProduct != null) {
                foundProducts.add(foundProduct);
                productsTable.setItems(foundProducts);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Data");
                alert.setHeaderText("Can't find");
                alert.setContentText("Cannot find that product.");
                alert.showAndWait();
            }
            return;
        } catch (NumberFormatException e) {
            // Ignore exception
        }
        if (!text.isBlank() || !text.isEmpty()) {
            ObservableList<Product> foundProductsList = Inventory.lookupProduct(text);
            if (foundProductsList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Data");
                alert.setHeaderText("Can't find");
                alert.setContentText("Cannot find that product.");
                alert.showAndWait();
            } else {
                productsTable.setItems(foundProductsList);
            }
        } else {
            productsTable.setItems(Inventory.getAllProducts());
        }
    }
}