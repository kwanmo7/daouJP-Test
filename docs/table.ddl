CREATE TABLE record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time DATETIME NOT NULL,
    subscribers INT,
    dropouts INT,
    payment_amount INT,
    amount_used INT,
    sales_amount INT
);

CREATE TABLE api_key (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    api_key VARCHAR(255) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
