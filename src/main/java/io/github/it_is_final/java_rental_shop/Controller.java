package io.github.it_is_final.java_rental_shop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private final ObservableList<String> cartItems = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> bookListView;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private Spinner<Integer> quantityField;

    @FXML
    private ImageView rmitLogoView;

    @FXML
    protected void initialize() {
        // Load books from XML file
        try {
            books = bookLoader.loadBooks(Objects.requireNonNull(getClass().getResourceAsStream("books.xml")));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load books from XML: " + e.getMessage());
        }

        // Populate the ListView with book titles
        onSearchButtonClick(); // Simulates an empty search, which lists all books

        // Bind cart items to the cartListView
        cartListView.setItems(cartItems);

        // Set the logo image
        Image rmitLogo = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream("rmit-logo.png")
                )
        );
        rmitLogoView.setImage(rmitLogo);

        quantityField.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1));
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
    protected void onAddToCartButtonClick() {
        String selectedBook = bookListView.getSelectionModel().getSelectedItem();
        int quantity = quantityField.getValue();

        if (selectedBook == null) {
            Alert alert = new Alert(AlertType.WARNING, "Please select a book to add to the cart.", ButtonType.OK);
            alert.setTitle("No Book Selected");
            alert.showAndWait();
            return;
        }

        if (quantity <= 0) {
            Alert alert = new Alert(AlertType.WARNING, "Please select a valid quantity.", ButtonType.OK);
            alert.setTitle("Invalid Quantity");
            alert.showAndWait();
            return;
        }

        // Add the selected book and quantity to the cart
        cartItems.add(selectedBook + " (x" + quantity + ")");
    }

    @FXML
    protected void onRemoveFromCartButtonClick() {
        String selectedCartItem = cartListView.getSelectionModel().getSelectedItem();

        if (selectedCartItem == null) {
            Alert alert = new Alert(AlertType.WARNING, "Please select an item to remove from the cart.", ButtonType.OK);
            alert.setTitle("No Item Selected");
            alert.showAndWait();
            return;
        }

        // Remove the selected item from the cart
        cartItems.remove(selectedCartItem);
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
