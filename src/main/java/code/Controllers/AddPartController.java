package code.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public TextField machineId;
    public TextField maximumField;
    public TextField priceField;
    public TextField inventoryField;
    public TextField nameField;
    public TextField idField;
    public TextField minimumField;
    public Button saveButton;
    public Button cancelButton;

    public void handleInHouseButtonAction(ActionEvent actionEvent) {
    }

    public void handleOutsourcedRadioAction(ActionEvent actionEvent) {
    }

    public void handleSaveButtonAction(ActionEvent actionEvent) {
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
}
