package com.adso.pos.service;

import java.util.HashMap;
import java.util.Map;

import com.adso.pos.model.Product;


// The InventoryManager class handles all business logic related to product stock.
// It is responsible for checking availability and updating quantities.
public class InventoryManager {

    private final ProductDAO productDAO;

    public InventoryManager(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Retrieves a Product from the database by its ID.
     * @param productId The ID of the product to retrieve.
     * @return The Product object, or null if not found.
     */
    public Product getProduct(int productId) {
        return productDAO.getProductById(productId);
    }

    /**
     * Updates the stock of a product in the database.
     * @param productId The ID of the product to update.
     * @param quantity The amount to add or subtract from the current stock.
     * Use a negative number to reduce stock.
     * @return true if the stock was updated successfully, false otherwise.
     */
    public boolean updateStock(int productId, int quantity) {
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            int newStock = product.getStockQuantity() + quantity;
            if (newStock >= 0) {
                // The DAO now handles the update directly.
                return productDAO.updateStock(productId, newStock);
            }
        }
        return false;
    }
}
