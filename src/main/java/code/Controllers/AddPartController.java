package code.Controllers;

import code.Models.InHouse;
import code.Models.Inventory;
import code.Models.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static code.Models.Inventory.nextPartsId;
import static code.Models.Inventory.nextProductsId;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddPartController {
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public TextField changeableField;
    public TextField maximumField;
    public TextField priceField;
    public TextField inventoryField;
    public TextField nameField;
    public TextField idField;
    public TextField minimumField;
    public Button saveButton;
    public Button cancelButton;
    public Label changeableText;

    public void handleInHouseRadioAction(ActionEvent actionEvent) {
        changeableText.setText("Machine Id");
    }

    public void handleOutsourcedRadioAction(ActionEvent actionEvent) {
        changeableText.setText("Company Name");
    }

    public void handleSaveButtonAction(ActionEvent actionEvent) throws IOException {
        if (inHouseRadio.isSelected()) {
            if (nameField.getText().isEmpty()) {
                System.out.println("name is blank");
                return;
            }

            try {
                parseInt(inventoryField.getText());
            } catch (NumberFormatException e) {
                System.out.println("inventory must be a number, cannot be blank");
                return;
            }

            try {
                parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                // if given an int it will convert to a double with one digit after the decimal
                // may have to do this differently
                System.out.println("number must be in decimal format with two digits on the end, cannot be blank");
                return;
            }

            try {
                parseInt(minimumField.getText());
            } catch (NumberFormatException e) {
                System.out.println("minimum must be a number, cannot be blank");
                return;
            }

            try {
                parseInt(maximumField.getText());
            } catch (NumberFormatException e) {
                System.out.println("maximum must be a number, cannot be blank");
                return;
            }

            if (parseInt(minimumField.getText()) > parseInt(maximumField.getText())) {
                System.out.println("minimum cannot be greater than maximum");
                return;
            }

            try {
                parseInt(changeableField.getText());
            } catch (NumberFormatException e) {
                System.out.println("machine id must be a number, cannot be blank");
                return;
            }

            InHouse inHousePart = new InHouse(
                    nextPartsId,
                    nameField.getText(),
                    parseDouble(priceField.getText()),
                    parseInt(inventoryField.getText()),
                    parseInt(minimumField.getText()),
                    parseInt(maximumField.getText()),
                    parseInt(changeableField.getText())
                    );
            Inventory.addPart(inHousePart);
        } else if (outsourcedRadio.isSelected()) {
            Outsourced outSourcedPart = new Outsourced(
                    nextPartsId,
                    nameField.getText(),
                    parseDouble(priceField.getText()),
                    parseInt(inventoryField.getText()),
                    parseInt(minimumField.getText()),
                    parseInt(maximumField.getText()),
                    changeableField.getText()
            );
            Inventory.addPart(outSourcedPart);
        }
        nextPartsId += 1;

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


}
