package com.adso.pos.service;

import java.util.HashMap;
import java.util.Map;

import com.adso.pos.model.Product;


// The InventoryManager class handles all business logic related to product stock.
// It is responsible for checking availability and updating quantities.
public class InventoryManager {
    // A simple in-memory "database" to hold product stock.
    private Map<Integer, Product> products;

    public InventoryManager() {
        this.products = new HashMap<>();
        // Pre-populate with some sample products.
        products.put(1, new Product(1, "Laptop", 1200.00, 10));
        products.put(2, new Product(2, "Mouse", 25.00, 50));
        products.put(3, new Product(3, "Keyboard", 75.00, 30));
    }

    /**
     * Checks if a product is available in the required quantity.
     * @param productId The ID of the product.
     * @param quantity The quantity to check.
     * @return true if there is enough stock, false otherwise.
     */
    public boolean checkStock(int productId, int quantity) {
        Product product = products.get(productId);
        return product != null && product.getStockQuantity() >= quantity;
    }

    /**
     * Decrements the stock of a product after a sale.
     * @param productId The ID of the product.
     * @param quantity The quantity to remove from stock.
     */
    public void updateStock(int productId, int quantity) {
        if (checkStock(productId, quantity)) {
            Product product = products.get(productId);
            product.setStockQuantity(product.getStockQuantity() - quantity);
            System.out.println("Inventory updated for product ID " + productId + ".");
        } else {
            System.out.println("Error: Not enough stock for product ID " + productId + ".");
        }
    }

    /**
     * Gets a product by its ID.
     * @param productId The ID of the product.
     * @return The Product object, or null if not found.
     */
    public Product getProduct(int productId) {
        return products.get(productId);
    }
}