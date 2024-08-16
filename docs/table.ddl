CREATE TABLE record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time DATETIME NOT NULL,
    subscribers INT,
    dropouts INT,
    payment_amount INT,
    amount_used INT,
    sales_amount INT
);
