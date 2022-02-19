module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml;
    exports hu.petrik.muzeumfrontendjavafx;
}