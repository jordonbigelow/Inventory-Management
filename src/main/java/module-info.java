module com.example.c482_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.c482_project to javafx.fxml;
    exports com.example.c482_project;
    exports com.example.c482_project.Controllers;
    opens com.example.c482_project.Controllers to javafx.fxml;
}