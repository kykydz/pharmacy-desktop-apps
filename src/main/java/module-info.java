module org.app.projectpharmacy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.app.projectpharmacy to javafx.fxml;
    exports org.app.projectpharmacy;
    exports org.app.projectpharmacy.controller;
    opens org.app.projectpharmacy.controller to javafx.fxml;
}