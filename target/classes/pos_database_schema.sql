-- Create the main database for the Point of Sale system.
-- The IF NOT EXISTS clause prevents an error if the database already exists.
CREATE DATABASE IF NOT EXISTS pos_db;

-- Use the newly created database for all subsequent operations.
USE pos_db;

-- -----------------------------------------------------
-- Table `products`
-- This table stores information about each item available for sale.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10, 2) NOT NULL,
  `stock_quantity` INT NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `customers`
-- This table stores information about the customers.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
);

-- -----------------------------------------------------
-- Table `sales`
-- This table represents a single, completed transaction.
-- It has a foreign key to the `customers` table.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `sale_date` DATETIME NOT NULL,
  `total_amount` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sales_customer_idx` (`customer_id` ASC),
  -- Gives a name to the FOREIGN KEY relationship.
  -- it makes the statement more readable, and maintainable.
  CONSTRAINT `fk_sales_customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `sale_items`
-- This table stores the individual items from a sale.
-- It has foreign keys to both `sales` and `products` tables.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sale_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sale_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `subtotal` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sale_items_sale_idx` (`sale_id` ASC),
  INDEX `fk_sale_items_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_sale_items_sale`
    FOREIGN KEY (`sale_id`)
    REFERENCES `sales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_items_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);