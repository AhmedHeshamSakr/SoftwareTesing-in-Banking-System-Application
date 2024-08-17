module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.java;
    requires org.controlsfx.controls;
    requires junit;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.DB_Management;
}