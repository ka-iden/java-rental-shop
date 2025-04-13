package io.github.it_is_final.java_rental_shop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
    private Spinner quantityField;

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

        SpinnerValueFactory.IntegerSpinnerValueFactory quantityVf =
                (SpinnerValueFactory.IntegerSpinnerValueFactory)quantityField.getValueFactory();
        quantityVf.setMax(Integer.MAX_VALUE);
    }
}
