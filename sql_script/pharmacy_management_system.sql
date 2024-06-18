-- NOTE: You can run the quaries one after the other to create the database.


-- Create the pharmacy database
CREATE DATABASE pharmacy;

-- Use the pharmacy database
USE pharmacy;

-- Create the users table
CREATE TABLE users (
    user_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Create the suppliers table
CREATE TABLE suppliers (
    supplier_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    contact VARCHAR(50)
);

-- Create the drugs table
CREATE TABLE drugs (
    drug_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    supplier_id VARCHAR(255),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Create the customers table
CREATE TABLE customers (
    customer_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(50)
);

-- Create the sales table
CREATE TABLE sales (
    sale_id VARCHAR(255) PRIMARY KEY,
    drug_id VARCHAR(255),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    FOREIGN KEY (drug_id) REFERENCES drugs(drug_id)
);

-- Create the purchases table
CREATE TABLE purchases (
    purchase_id VARCHAR(255) PRIMARY KEY,
    drug_id VARCHAR(255),
    customer_id VARCHAR(255),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (drug_id) REFERENCES drugs(drug_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
