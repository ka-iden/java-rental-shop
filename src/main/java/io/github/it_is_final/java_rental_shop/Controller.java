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
    private TextArea receiptArea;

    @FXML
    private Label totalLabel;

    @FXML
    private CheckBox membershipCheckbox;

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
    protected void updateReceipt() {
        // Clear the receipt area
        receiptArea.clear();

        // Calculate the total cost
        double totalCost = 0.0;

        // Build the receipt content
        StringBuilder receiptContent = new StringBuilder();
        for (String item : cartItems) {
            receiptContent.append(item).append("\n");

            // Extract the price and quantity from the cart item string
            String[] parts = item.split("\\(x");
            if (parts.length == 2) {
                String pricePart = parts[0].substring(parts[0].lastIndexOf("$") + 1).trim();
                String quantityPart = parts[1].replace(")", "").trim();

                try {
                    double price = Double.parseDouble(pricePart);
                    int quantity = Integer.parseInt(quantityPart);
                    totalCost += price * quantity;
                } catch (NumberFormatException e) {
                    // Handle parsing errors gracefully
                    e.printStackTrace();
                }
            }
        }

        // Add the subtotal to the receipt content
        receiptContent.append("Subtotal: $").append(String.format("%.2f", totalCost)).append("\n");

        // Apply a 10% discount if the checkbox is checked
        if (membershipCheckbox.isSelected()) {
            totalCost *= 0.9;
            receiptContent.append("Congratulations, you have received a discount!\n"
                + "As a member of the RMIT Video Rental, you get 10% off!\n"
                + "Discount: -$").append(String.format("%.2f", totalCost * 0.1)).append("\n");
        }

        // Add the total cost to the receipt content
        receiptContent.append("Total Cost: $").append(String.format("%.2f", totalCost)).append("\n");
        // Update the receipt area
        receiptArea.setText(receiptContent.toString());

        // Update the total label
        totalLabel.setText(String.format("Total: $%.2f", totalCost));
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
