module io.github.it_is_final.java_rental_shop {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.xml;

    opens io.github.it_is_final.java_rental_shop to javafx.fxml;
    exports io.github.it_is_final.java_rental_shop;
}