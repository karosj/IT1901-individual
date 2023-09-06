module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens karoshm.app to javafx.graphics, javafx.fxml;
}
