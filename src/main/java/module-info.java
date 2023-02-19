module com.example.c482_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens code to javafx.fxml;
    exports code;
    exports code.Controllers;
    opens code.Controllers to javafx.fxml;
}