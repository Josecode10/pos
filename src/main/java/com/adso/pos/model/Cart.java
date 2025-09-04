package com.adso.pos.model;


import java.util.ArrayList;
import java.util.List;


// The Cart class represents a customer's temporary shopping cart.
// It contains a list of CartItem objects and methods for managing the cart.
public class Cart {
    private List<CartItem> items;

    // Constructor to initialize an empty cart.
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart or updates its quantity if it already exists.
     * @param product The product to add.
     * @param quantity The quantity to add.
     */
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    /**
     * Removes an item from the cart.
     * @param productId The ID of the product to remove.
     */
    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    /**
     * Gets the total amount for all items in the cart.
     * @return The total amount.
     */
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // Getter for the list of items.
    public List<CartItem> getItems() {
        return items;
    }
}
