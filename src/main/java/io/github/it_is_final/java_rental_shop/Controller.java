package io.github.it_is_final.java_rental_shop;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.Objects;

public class Controller {

    @FXML
    protected void onHelloButtonClick() {
        String wText = String.format(
            "Welcome to JavaFX %s! Running on Java %s.",
            System.getProperty("javafx.version"),
            System.getProperty("java.version")
         );
        Alert alert = new Alert(AlertType.INFORMATION, wText, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private ImageView rmitLogoView;

    @FXML
    protected void initialize() {
        Image rmitLogo = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("rmit-logo.png")
                )
        );
        rmitLogoView.setImage(rmitLogo);
    }

    @FXML
    private TextField quantityField;

    @FXML
    protected void checkQuantityInput() {
        String sQuantity = quantityField.textProperty().get();
        try {
            Integer.parseInt(sQuantity);
        } catch (NumberFormatException e) {
            quantityField.textProperty().set("");
        }
    };

}
