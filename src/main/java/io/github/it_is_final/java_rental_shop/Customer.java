package io.github.it_is_final.java_rental_shop;

import java.math.BigDecimal;
import java.util.*;

public class Customer {
    private final List<Video> cart;
    public Customer() {
        cart = new ArrayList<>();
    }

    public BigDecimal getCartTotal() {
        return cart
                .stream()
                .map(Video::price)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }
}
