package code.Controllers;

import code.Models.InHouse;
import code.Models.Outsourced;
import code.Models.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    private static Part selectedPart;
    public RadioButton inHouseRadio;
    public ToggleGroup tGroup;
    public RadioButton outsourcedRadio;
    public Label changeableText;
    public TextField idField;
    public TextField nameField;
    public TextField inventoryField;
    public TextField priceField;
    public TextField minimumField;
    public TextField maximumField;
    public TextField changeableField;
    public Button saveButton;
    public Button cancelButton;

    public void handleInHouseRadioAction(ActionEvent actionEvent) {
        changeableText.setText("Machine Id");
    }
    // these to in house and out sourced buttons need to be selected by the program
    // they need to import the data that is being passed and select one of the
    // options based on the data that was passed to the controller
    public void handleOutsourcedRadioAction(ActionEvent actionEvent) {
        changeableText.setText("Company Name");
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

    public static void setSelectedPart(Part part) {
        selectedPart = part;
    }

    public void initialize (URL location, ResourceBundle resourceBundle) {
        if (selectedPart instanceof InHouse) {
            changeableText.setText("Machine Id");
            inHouseRadio.setSelected(true);
            
            nameField.setText(selectedPart.getName());
            inventoryField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            minimumField.setText(Integer.toString(selectedPart.getMin()));
            maximumField.setText(Integer.toString(selectedPart.getMax()));
            changeableField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else {
            changeableText.setText("Company Name");
            outsourcedRadio.setSelected(true);

            nameField.setText(selectedPart.getName());
            inventoryField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            minimumField.setText(Integer.toString(selectedPart.getMin()));
            maximumField.setText(Integer.toString(selectedPart.getMax()));
            changeableField.setText(((Outsourced) selectedPart).getCompanyName());
        }

    }
}
