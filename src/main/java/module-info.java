module com.example.motfleche {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens Motfleche to javafx.fxml;
    exports Motfleche;
}