module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens karoshm.app.calc to javafx.graphics, javafx.fxml;
}
