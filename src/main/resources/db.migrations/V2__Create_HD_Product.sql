CREATE TABLE product (
    product_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    product_name VARCHAR(255),
    product_description VARCHAR(500),
    product_color VARCHAR(100),
    product_size VARCHAR(50),
    product_price DECIMAL(10,2),
    PRIMARY KEY (product_id)
);
