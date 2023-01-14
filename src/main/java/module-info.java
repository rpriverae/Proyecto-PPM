module com.example.pabloperezmartinez {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.models;
    opens com.example.pabloperezmartinez to javafx.fxml;
    exports com.example.pabloperezmartinez;
    exports com.example.models;
}
