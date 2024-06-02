module org.app.projectpharmacy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.app.projectpharmacy to javafx.fxml;
    exports org.app.projectpharmacy;
    exports org.app.projectpharmacy.controller;
    opens org.app.projectpharmacy.controller to javafx.fxml;
    exports org.app.projectpharmacy.services;
    opens org.app.projectpharmacy.services to javafx.fxml;
}