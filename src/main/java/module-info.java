module java.rental.shop {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens io.github.it_is_final.java_rental_shop to javafx.fxml;
    exports io.github.it_is_final.java_rental_shop;
}