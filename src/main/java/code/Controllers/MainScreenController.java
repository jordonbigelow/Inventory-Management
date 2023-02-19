package code.Controllers;

import code.Models.Inventory;
import code.Models.Part;
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
    public void handleDeleteProductsButtonAction(ActionEvent actionEvent) throws IOException{
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        System.out.println(Inventory.getAllParts());
        // productsTable.setItems(Inventory.getAllProducts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}