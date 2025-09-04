package com.adso.pos.service;

import com.adso.pos.model.Cart;
import com.adso.pos.model.CartItem;
import com.adso.pos.model.Customer;
import com.adso.pos.model.Product;
import com.adso.pos.model.Sale;

// The PosController acts as the main entry point and orchestrator for the POS system logic.
// In a web application, this would be a servlet or a REST controller that handles requests.
// This class demonstrates how the other classes interact.
public class PosController {
    private Cart cart;
    private InventoryManager inventoryManager;

    public PosController() {
        this.cart = new Cart();
        this.inventoryManager = new InventoryManager();
    }

    /**
     * Adds a product to the current cart.
     * @param productId The ID of the product to add.
     * @param quantity The quantity of the product.
     */
    public void addItemToCart(int productId, int quantity) {
        Product product = inventoryManager.getProduct(productId);
        if (product != null && inventoryManager.checkStock(productId, quantity)) {
            cart.addItem(product, quantity);
            System.out.println("Added " + quantity + " of " + product.getName() + " to the cart.");
        } else {
            System.out.println("Failed to add item. Not enough stock or product not found.");
        }
    }

    /**
     * Processes a full transaction, converting the cart into a sale.
     * @param customer The customer for this transaction.
     * @return The newly created Sale object.
     */
    public Sale processSale(Customer customer) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cannot process sale: Cart is empty.");
            return null;
        }

        System.out.println("Processing sale for customer: " + customer.getName());
        
        // Loop through cart items to update inventory
        for (CartItem item : cart.getItems()) {
            inventoryManager.updateStock(item.getProduct().getId(), item.getQuantity());
        }

        // Create a unique sale ID (for this example, we'll use a simple timestamp)
        int saleId = (int) (System.currentTimeMillis() % 10000);
        
        Sale newSale = new Sale(saleId, customer, cart);

        // Clear the cart for the next transaction.
        cart = new Cart();

        System.out.println("Sale " + newSale.getSaleId() + " completed successfully!");
        System.out.println("Total Amount: $" + newSale.getTotalAmount());

        return newSale;
    }

    /**
     * A simple main method to demonstrate the functionality.
     */
    public static void main(String[] args) {
        PosController pos = new PosController();

        // Simulate a customer and a shopping session.
        Customer customer = new Customer(1, "John Doe", "john.doe@example.com");

        System.out.println("--- Starting a new transaction ---");
        pos.addItemToCart(1, 1); // Add a Laptop
        pos.addItemToCart(2, 2); // Add two Mouses
        pos.addItemToCart(3, 1); // Add a Keyboard

        // Process the sale.
        pos.processSale(customer);
        
        System.out.println("\n--- Attempting another transaction ---");
        // Try to add more than is in stock (Keyboard has 30, we'll try to add 35)
        pos.addItemToCart(3, 35);
        pos.processSale(customer);
    }
}