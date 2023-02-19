module com.example.c482_project {
    requires javafx.controls;
    requires javafx.fxml;

    opens code to javafx.fxml;
    opens code.Controllers to javafx.fxml;
    opens code.Models to javafx.base;

    exports code;
    exports code.Controllers;
}
