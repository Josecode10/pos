package com.adso.pos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

// The Sale class represents a permanent, completed transaction.
// It holds a snapshot of a completed cart at the time of purchase.
public class Sale {
    private int saleId;
    private Customer customer;
    private Date saleDate;
    private double totalAmount;
    private List<CartItem> items;

    // Constructor to create a new Sale instance.
    public Sale(int saleId, Customer customer, Cart cart) {
        this.saleId = saleId;
        this.customer = customer;
        this.saleDate = new Date(); // Sets the current date.
        this.totalAmount = cart.getTotalAmount();
        // A deep copy of the cart items to save the state at the time of sale.
        this.items = new ArrayList<>(cart.getItems());
    }

    // Getters for the fields.
    public int getSaleId() {
        return saleId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
