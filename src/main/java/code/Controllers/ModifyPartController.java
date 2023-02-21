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
    private static Product selectedProduct;
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

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene= new Scene(root1,966,361);
            stage.setTitle("Inventory CRM");
            stage.setScene(scene);
            stage.show();
        } else if (outsourcedRadio.isSelected()) {
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

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/code/MainScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene= new Scene(root1,966,361);
            stage.setTitle("Inventory CRM");
            stage.setScene(scene);
            stage.show();
        }
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
