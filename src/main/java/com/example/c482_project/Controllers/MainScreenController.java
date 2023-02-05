package com.example.c482_project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainScreenController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button exitButton;

    public void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }
}