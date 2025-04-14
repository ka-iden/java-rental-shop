package io.github.it_is_final.java_rental_shop;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private final Map<String, CartEntry> cart;
    public Cart() {
        cart = new HashMap<>();
    }

    public void addVideoToCart(Video v, int quantity) {
        if (!cart.containsKey(v.name())) {
            cart.put(v.name(), new CartEntry(v, quantity));
        } else {
            cart.get(v.name()).modifyQuantity(quantity);
        }
    }

    public void removeVideoFromCart(Video v) {
        cart.remove(v.name());
    }

    public BigDecimal getCartSubtotal() {
        return cart
                .values()
                .stream()
                .map((ce) -> ce.getVideo().price())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getCartTotal(boolean hasDiscount) {
        BigDecimal total = getCartSubtotal();
        int totalQuantity = cart
                .values()
                .stream()
                .map(CartEntry::getQuantity)
                .reduce(0, Integer::sum);
        BigDecimal discount = new BigDecimal("0.1");
        if (totalQuantity > 4 && total.compareTo(BigDecimal.valueOf(50)) > 0) {
            for (CartEntry ce : cart.values()) {
                total = total.subtract(
                        ce.getVideo().price()
                                .multiply(discount)
                                .multiply(
                                        BigDecimal.valueOf(ce.getQuantity())
                                )
                );
            }
        }
        if (hasDiscount) {
            total = total.multiply(BigDecimal.ONE.subtract(discount));
        }
        return total;
    }
}
