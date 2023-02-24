package code.Controllers;

import code.Models.*;
import javafx.collections.ObservableList;
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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ModifyPartController implements Initializable {
    private static ObservableList<Part> currentList = Inventory.getAllParts();
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

    public void handleOutsourcedRadioAction(ActionEvent actionEvent) {
        changeableText.setText("Company Name");
    }

    public void handleSaveButtonAction(ActionEvent actionEvent) throws IOException {
        if (inHouseRadio.isSelected()) {
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

            try {
                parseInt(changeableField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing/Wrong Data");
                alert.setContentText("Machine ID must be a number, cannot be blank.");
                alert.showAndWait();
                return;
            }

            InHouse inHousePart = new InHouse(
                    parseInt(idField.getText()),
                    nameField.getText(),
                    parseDouble(priceField.getText()),
                    parseInt(inventoryField.getText()),
                    parseInt(minimumField.getText()),
                    parseInt(maximumField.getText()),
                    parseInt(changeableField.getText())
            );
            Inventory.updatePart(getIndexOfPart(selectedPart), inHousePart);
        } else if (outsourcedRadio.isSelected()) {
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

            if (changeableField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Missing Data");
                alert.setContentText("Company name cannot be blank.");
                alert.showAndWait();
                return;
            }
            Outsourced outSourcedPart = new Outsourced(
                    parseInt(idField.getText()),
                    nameField.getText(),
                    parseDouble(priceField.getText()),
                    parseInt(inventoryField.getText()),
                    parseInt(minimumField.getText()),
                    parseInt(maximumField.getText()),
                    changeableField.getText()
            );
            Inventory.updatePart(getIndexOfPart(selectedPart), outSourcedPart);
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

    public static void setSelectedPart(Part part) {
        selectedPart = part;
    }

    public static int getIndexOfPart(Part part) {
        int index = 0;
        for (Part currentPart : currentList) {
            if (part.getId() == currentPart.getId()) {
                index = currentList.indexOf(currentPart);
            }
        }
        return index;
    }

    public void initialize (URL location, ResourceBundle resourceBundle) {
        if (selectedPart instanceof InHouse) {
            changeableText.setText("Machine Id");
            inHouseRadio.setSelected(true);

            idField.setText(Integer.toString(selectedPart.getId()));
            nameField.setText(selectedPart.getName());
            inventoryField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            minimumField.setText(Integer.toString(selectedPart.getMin()));
            maximumField.setText(Integer.toString(selectedPart.getMax()));
            changeableField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else {
            changeableText.setText("Company Name");
            outsourcedRadio.setSelected(true);

            idField.setText(Integer.toString(selectedPart.getId()));
            nameField.setText(selectedPart.getName());
            inventoryField.setText(Integer.toString(selectedPart.getStock()));
            priceField.setText(Double.toString(selectedPart.getPrice()));
            minimumField.setText(Integer.toString(selectedPart.getMin()));
            maximumField.setText(Integer.toString(selectedPart.getMax()));
            changeableField.setText(((Outsourced) selectedPart).getCompanyName());
        }

    }
}
