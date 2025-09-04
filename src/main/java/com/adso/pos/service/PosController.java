package com.adso.pos.service;

import java.util.ArrayList;
import java.util.List;


// The PosController is the main class that orchestrates the Point of Sale process.
// It now manages a list of SaleItem objects directly for a transaction.

public class PosController {

    private final InventoryManager inventoryManager;
    private final SaleDAO saleDAO;
    private final CustomerDAO customerDAO;

    // A temporary list of items for the current transaction.
    private List<SaleItem> currentSaleItems;

    // 
    public PosController(InventoryManager inventoryManager, SaleDAO saleDAO, CustomerDAO customerDAO) {
        this.inventoryManager = inventoryManager;
        this.saleDAO = saleDAO;
        this.customerDAO = customerDAO;
        this.currentSaleItems = new ArrayList<>();
    }

    /**
     * Adds a product to the current transaction.
     * @param productId The ID of the product to add.
     * @param quantity The quantity of the product.
     * @return true if the product was added, false if it's out of stock or not found.
     */
    public boolean addItemToSale(int productId, int quantity) {
        Product product = inventoryManager.getProduct(productId);
        if (product != null && product.getStockQuantity() >= quantity) {
            currentSaleItems.add(new SaleItem(product, quantity));
            return true;
        }
        return false;
    }

    /**
     * Finalizes the sale, saves it to the database, and updates product stock.
     * @param customerId The ID of the customer for this sale.
     * @return true if the sale was successfully completed, false otherwise.
     */
    public boolean finalizeSale(int customerId) {
        if (currentSaleItems.isEmpty()) {
            System.out.println("Cannot finalize an empty sale.");
            return false;
        }

        // Retrieve the customer from the database.
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return false;
        }

        // Create the Sale object.
        Sale newSale = new Sale(customer, currentSaleItems);

        try {
            // Save the sale to the database.
            saleDAO.saveSale(newSale);

            // Update the stock for each product in the sale.
            for (SaleItem item : currentSaleItems) {
                inventoryManager.updateStock(item.getProduct().getId(), -item.getQuantity());
            }

            // Clear the temporary list for the next transaction.
            currentSaleItems.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Error finalizing sale: " + e.getMessage());
            // It might be necessary to roll back database changes here in a more robust system.
            return false;
        }
    }
}
