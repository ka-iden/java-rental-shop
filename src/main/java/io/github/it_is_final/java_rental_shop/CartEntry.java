package io.github.it_is_final.java_rental_shop;

public class CartEntry {
    private final Video video;
    private int quantity;
    public CartEntry(Video video, int quantity) {
        this.video = video;
        this.quantity = quantity;
    }

    public Video getVideo() {
        return video;
    }

    public int getQuantity() {
        return quantity;
    }

    public void modifyQuantity(int a) {
        quantity += a;
    }
}
