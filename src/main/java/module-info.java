module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens karoshm.calc to javafx.graphics, javafx.fxml;
}
