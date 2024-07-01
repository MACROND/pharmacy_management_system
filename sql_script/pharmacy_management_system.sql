-- Create the pharmacy database
CREATE DATABASE pharmacy;

-- Use the pharmacy database
USE pharmacy;

-- Create the users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(225),
    lastName VARCHAR(225),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Create the suppliers table
CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    contact VARCHAR(50)
);

-- Create the drugs table
CREATE TABLE drugs (
    drug_id VARCHAR(225) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    supplier_id INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Create the sales table
CREATE TABLE sales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    drug_id VARCHAR(225),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    customer_name VARCHAR(225),
    customer_contact VARCHAR(50),
    FOREIGN KEY (drug_id) REFERENCES drugs (drug_id)
);

-- Create the stock table
CREATE TABLE stock (
    drug_id VARCHAR(225) PRIMARY KEY,
    name VARCHAR(225) NOT NULL,
    initial_quantity INT NOT NULL,
    amount_sold INT NOT NULL,
    quantity_left INT NOT NULL,
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(225) NOT NULL DEFAULT 'Moderate'
);

