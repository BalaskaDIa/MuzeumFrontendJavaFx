module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;

    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml;
    exports hu.petrik.muzeumfrontendjavafx;
    exports hu.petrik.muzeumfrontendjavafx.controllers;
    opens hu.petrik.muzeumfrontendjavafx.controllers to javafx.fxml;
    exports hu.petrik.muzeumfrontendjavafx.api;
    opens hu.petrik.muzeumfrontendjavafx.api to javafx.fxml;
}