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
    private List<SaleItem> items;

    // A constructor that takes a customer and the list of items to be sold.
    public Sale(int saleId, Customer customer, List<SaleItem> items) {
        this.saleId = saleId;
        this.customer = customer;
        this.saleDate = new Date(); // Sets the current date.
        this.items = new ArrayList<>(items);
        this.totalAmount = calculateTotal(items);
    }

    /**
     * Calculates the total amount for the sale from the list of items.
     * @param items The list of SaleItem objects.
     * @return The total amount for the sale.
     */
    private double calculateTotal(List<SaleItem> items) {
        double total = 0;
        for (SaleItem item : items) {
            total += item.getSubtotal();
        }
        return total;
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

    public List<SaleItem> getItems() {
        return items;
    }
}

