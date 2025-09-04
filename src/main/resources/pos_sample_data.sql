USE pos_db;

-- -----------------------------------------------------
-- Insert sample data into the `products` table.
-- -----------------------------------------------------
INSERT INTO `products` (`name`, `price`, `stock_quantity`) VALUES
('Laptop', 1200.00, 10),
('Mouse', 25.00, 50),
('Keyboard', 75.00, 30),
('Monitor', 300.00, 15),
('Webcam', 50.00, 20);

-- -----------------------------------------------------
-- Insert a sample customer into the `customers` table.
-- -----------------------------------------------------
INSERT INTO `customers` (`name`, `email`) VALUES
('Jane Doe', 'jane.doe@example.com'),
('John Smith', 'john.smith@example.com');