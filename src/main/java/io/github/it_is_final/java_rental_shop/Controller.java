package io.github.it_is_final.java_rental_shop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.Map;
import java.util.Objects;

public class Controller {

    private final BookLoader bookLoader = new BookLoader();
    private Map<String, Book> books;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> bookListView;

    @FXML
    private Spinner<Integer> quantityField;

    @FXML
    private ImageView rmitLogoView;

    @FXML
    protected void initialize() {
        // Load books from XML file
        try {
            books = bookLoader.loadBooks(Objects.requireNonNull(getClass().getResourceAsStream("books.xml")));
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to load books from XML: " + e.getMessage());
        }

        // Populate the ListView with book titles
        onSearchButtonClick(); // Simulates an empty search, which lists all books

        // Set the logo image
        Image rmitLogo = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("rmit-logo.png")
                )
        );
        rmitLogoView.setImage(rmitLogo);

        quantityField.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchTerm = searchField.getText().toLowerCase().trim();
        bookListView.getItems().clear();

        if (searchTerm.isEmpty()) {
            // If no search term, list all books
            books.values().forEach(book -> bookListView.getItems().add(book.toString()));
        } else {
            // Filter books by search term
            books.values().stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchTerm))
                    .forEach(book -> bookListView.getItems().add(book.toString()));
        }
    }

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
}
