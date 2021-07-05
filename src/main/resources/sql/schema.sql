CREATE TABLE IF NOT EXISTS customers (
    id        VARCHAR(60) DEFAULT RANDOM_UUID(),
    cust_name      VARCHAR     NOT NULL,
    balance   BIGINT     NOT NULL
);