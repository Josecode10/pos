package com.adso.pos.model;

// The CartItem class represents a single product with a specific quantity in a shopping cart.
// It holds a reference to a Product object and the quantity the customer wants to buy.

public class CartItem {
    private Product product;
    private int quantity;

    // Constructor to create a new CartItem instance.
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters for the fields.
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal for this cart item.
     * @return The subtotal (price * quantity).
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
