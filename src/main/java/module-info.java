module com.example.pabloperezmartinez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.models;
    opens com.example.pabloperezmartinez to javafx.fxml;
    opens com.example.dbhandler;
    exports com.example.pabloperezmartinez;
    exports com.example.models;
    exports com.example.dbhandler;
}
