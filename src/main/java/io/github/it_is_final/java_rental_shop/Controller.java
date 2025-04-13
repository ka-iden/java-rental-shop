package io.github.it_is_final.java_rental_shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String wText = String.format(
                "Welcome to JavaFX %s! Running on Java %s.",
                System.getProperty("javafx.version"),
                System.getProperty("java.version")
        );
        welcomeText.setText(wText);
    }
}
